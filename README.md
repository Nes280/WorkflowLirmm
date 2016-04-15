
## Lib utilisées

* Java 8
* Payara41

## Créer le user et la DB dans la base de données PSQL

Ouvrir postgresql en ligne de commande et exécuter les 2 lignes suivantes :

````sql
-- Role: sentiment_analysis_webpage_user

-- DROP ROLE sentiment_analysis_webpage_user;

CREATE ROLE sentiment_analysis_webpage_user WITH LOGIN PASSWORD 'admin' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

-- Database: sentiment_analysis_webpage_users_db

-- DROP DATABASE sentiment_analysis_webpage_users_db;

CREATE DATABASE sentiment_analysis_webpage_users_db WITH OWNER = sentiment_analysis_webpage_user ENCODING = 'UTF8' TABLESPACE = pg_default CONNECTION LIMIT = -1;


-- Il vaudrait mieux changer le nom du schema, pour qu'il soit plus parlant
-- DROP SCHEMA lirmm;

CREATE SCHEMA lirmm;


-- DROP TABLE lirmm.User

-- Table créée à partir du reverse engineering du code Java, attendre les tables envoyées par Niels

CREATE TABLE lirmm.User(
   Id                   serial PRIMARY KEY     NOT NULL,
   Fname                VARCHAR(70)    NOT NULL,
   Lname                VARCHAR(70)    NOT NULL,
   Mail                 VARCHAR(70)    NOT NULL,
   Password             VARCHAR(70)    NOT NULL,
   Mod                  BOOLEAN    NOT NULL,
   IsUpload             BOOLEAN    NOT NULL,
   IsTraining           BOOLEAN    NOT NULL
);

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

