
#!/bin/bash
mvn clean package

# For Docker tomcat:
docker cp target/sentiment-analysis-webpage.war furious_turing:/usr/local/tomcat/webapps/ROOT.war
/opt/payara41/glassfish/domains/payaradomain/autodeploy
