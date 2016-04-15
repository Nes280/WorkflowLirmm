
## Lib utilisées

* Java 8
* Payara41

## Créer le user et la DB dans la base de données PSQL

Ouvrir postgresql en ligne de commande et exécuter les 2 lignes suivantes :

````sql
-- Role: sentiment_analysis_webpage_user

-- DROP ROLE sentiment_analysis_webpage_user;

CREATE ROLE sentiment_analysis_webpage_user LOGIN ENCRYPTED PASSWORD 'md5007a5473772b32fa3bbfd1b06f407d4c' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

-- Database: sentiment_analysis_webpage_users_db

-- DROP DATABASE sentiment_analysis_webpage_users_db;

CREATE DATABASE sentiment_analysis_webpage_users_db WITH OWNER = sentiment_analysis_webpage_user ENCODING = 'UTF8' TABLESPACE = pg_default LC_COLLATE = 'C' LC_CTYPE = 'C' CONNECTION LIMIT = -1;
```
