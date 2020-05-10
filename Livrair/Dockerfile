#FROM tomcat:8.0
#COPY ./Logistic/target/logistic.war /usr/local/tomcat/webapps/
#COPY ./Logistic/target/logistic-classes.jar /usr/local/tomcat/webapps/
#COPY ./Transport/target/transport.war /usr/local/tomcat/webapps/
#COPY ./Transport/target/transport-classes.jar /usr/local/tomcat/webapps/
#
#EXPOSE 8080
#
##RUN mvn tomee:run
#CMD ["catalina.sh", "run"]

#FROM maven:3.5.2-jdk-8-alpine
#
## Create app directory
#RUN mkdir -p /usr/src/livrair
#WORKDIR /usr/src/livrair
#
#COPY . .
#
#RUN cd Logistic
#
## Running the client using the starting shell script
#ENTRYPOINT ["mvn", "package", "tomee:run"]

FROM tomee:8-jdk-7.0.1-plus

# Working inside the local TomEE system
WORKDIR /usr/local/tomee/

# Loading the executable server inside the image
#COPY ./Logistic/target/logistic.war ./webapps/.
COPY ./Scheduler/target/scheduler.war ./webapps/.

# Creating the tomee/eemot user to access to the web admin console
COPY ./resources/tomcat-user.xml ./conf/tomcat-users.xml

# Allow one to access to the manager from outside the container
COPY ./resources/manager-context.xml ./webapps/manager/META-INF/context.xml

# Copy persistence (use load instead)
# COPY resources/hsqldb.script ./data/hsqldb/hsqldb.script


# Copy the startup script
COPY ./resources/start-livrair.sh .
RUN ["chmod", "u+x", "./start-livrair.sh"]

# Creating an health check verification to check TomEE status
HEALTHCHECK --interval=5s CMD curl --fail http://localhost:8080/ || exit 1

# exposing the 8080 port to support external connections
EXPOSE 8080

# Starting the service
ENTRYPOINT ["./start-livrair.sh"]
