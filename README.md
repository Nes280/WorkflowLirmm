
## Lib utilisées

* Java 8
* Payara41

## Deployed on Advanse

### How to update the war

* `git pull` the project
* `mvn clean package` to package the poroject in a war with maven
* Go to http://advanse.lirmm.fr:4848, log with credentials, go to application and deploy the new war
* Go to http://advanse.lirmm.fr:8081/sentiment-analysis-webpage/index

### To make it work

Here what you have to check to make sure the application works:

* Postgres DB well defined and created. Setup address in https://github.com/Nes280/sentiment-analysis-webpage/blob/master/src/main/java/fr/lirmm/db/BaseDeDonnee.java#L28 (For advanse it is 193.49.110.38:5432)
* resources files added to the Payara docker container
* `asadmin start-domain payaradomain` have been run in the Payara docker container
* Connection pool created in Payara

To set it right refer to the following sections

### Payara on Docker

`docker ps` to check if the container is running (the payaraserver image with the name "furious_turing")

* If not running:

```
docker start furious_turing

# Attach to the container to start payara:
docker exec -i -t furious_turing bash

# In container run the following to start payara
./asadmin start-domain payaradomain
```

* If not created:

Exposing ports on localhost: 4848 and 8081 (for traditional 8080)

```
docker run -t -p 4848:4848 -p 8081:8080 -p 8181:8181 -d payaradocker/payaraserver:4.1.1.161 /bin/bash
```

* Get to the application

    * Webpage: http://advanse.lirmm.fr:8081/sentiment-analysis-webpage-0.1/index

    * Admin: https://advanse.lirmm.fr:4848
Login: admin / Password: glassfish

* Check the logs
`cat /opt/payara41/glassfish/domains/payaradomain/logs/server.log`

### Postgres DB

Postgres DB on Advanse server postgres, at adress: 193.49.110.38

`sudo -u postgres psql postgres`

```
\c sentiment_analysis_webpage_users_db
SHOW search_path;  (should be "lirmm")
\dt

# Check if something in User table
select * FROM lirmm."User";
```


## Créer le user et la DB dans la base de données PSQL

Ouvrir postgresql en ligne de commande avec `sudo -u postgres psql postgres`

```sql
-- Role: sentiment_analysis_webpage_user

-- DROP ROLE sentiment_analysis_webpage_user;

CREATE ROLE sentiment_analysis_webpage_user WITH LOGIN PASSWORD 'admin' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

-- Database: sentiment_analysis_webpage_users_db

-- DROP DATABASE sentiment_analysis_webpage_users_db;

CREATE DATABASE sentiment_analysis_webpage_users_db WITH OWNER = sentiment_analysis_webpage_user ENCODING = 'UTF8' TABLESPACE = pg_default CONNECTION LIMIT = -1;


-- Il vaudrait mieux changer le nom du schema, pour qu'il soit plus parlant
-- DROP SCHEMA lirmm;

CREATE SCHEMA "lirmm" AUTHORIZATION "sentiment_analysis_webpage_user";

SET search_path= lirmm;

SHOW search_path;

-- Table: lirmm."User"

-- DROP TABLE lirmm."User";

CREATE TABLE lirmm."User"
(
  "Id" serial NOT NULL,
  "Fname" character varying(50),
  "Lname" character varying(50),
  "Mail" character varying(50),
  "Password" character varying(50),
  "Mod" boolean,
  "IsUpload" boolean,
  "IsTraining" boolean,
  CONSTRAINT "PK" PRIMARY KEY ("Id"),
  CONSTRAINT mail UNIQUE ("Mail")
)
WITH (
  OIDS=FALSE
);


ALTER TABLE lirmm."User" OWNER TO sentiment_analysis_webpage_user;



-- Table: lirmm."File"

-- DROP TABLE lirmm."File";

CREATE TABLE lirmm."File"
(
  "Id_file" serial NOT NULL,
  "Id_user" serial NOT NULL,
  "Name" character varying(50),
  "Info" character varying(100),
  "Date_create" date,
  "Date_update" date,
  CONSTRAINT "PK-file" PRIMARY KEY ("Id_file"),
  CONSTRAINT "FK-file-user" FOREIGN KEY ("Id_user")
      REFERENCES lirmm."User" ("Id") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);



ALTER TABLE lirmm."File" OWNER TO sentiment_analysis_webpage_user;


```

### Créer le JDBC Connection Pool dans Payara

* Ouvrir l'interface admin de Payara ( https://advanse.lirmm.fr:4848 )
* Resources > JDBC > JDBC Connection Pools
* Cliquer sur "New..."
* Remplir les champs avec les données suivantes :
    * Pool Name: SentimentAnalysisWebpagePool
    * Resource Type: javax.sql.DataSource
    * Database Driver Vendor: Postgresql
    * Cliquer sur Next

* Remplir certains Additional Properties:
    * User: sentiment_analysis_webpage_user
    * Password: admin
    * ServerName: 193.49.110.38 ou localhost
    * PortNumber: 5432
    * DatabaseName: sentiment_analysis_webpage_users_db


### Créer le JDBC Resources dans Payara

* Resources > JDBC > JDBC Resources
* JNDI Name: jdbc/sentimentanalysiswebpage
* Pool Name: SentimentAnalysisWebpagePool


### Add resources files to the Payara container

* Copy the files to the container

```
cd resources_on_server
docker cp ressources furious_turing:/opt/payara41/glassfish/domains/payaradomain/config
docker cp models furious_turing:/opt/payara41/glassfish/domains/payaradomain/config
docker cp TreeTagger furious_turing:/opt/payara41/glassfish/domains/payaradomain/config
```

* Then change owner and permission for files added in the containers (it needs to be payara)

```
docker exec -i -t --user root furious_turing bash
cd /opt/payara41/glassfish/domains/payaradomain/config

chown -R payara:payara *
chmod -R 755 ressources/
chmod -R 755 models/
chmod -R 755 TreeTagger/
```
