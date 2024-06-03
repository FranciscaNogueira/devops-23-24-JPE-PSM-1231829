# syntax=docker/dockerfile:1

FROM tomcat:10.1.24-jre21
ADD ./build/libs/react-and-pring-data-rest-basic-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]