#FROM tomcat:8.0
#COPY ./Delivery/target/delivery.war /usr/local/tomcat/webapps/
#COPY ./Delivery/target/delivery-classes.jar /usr/local/tomcat/webapps/
#COPY ./Logistic/target/logistic.war /usr/local/tomcat/webapps/
#COPY ./Logistic/target/logistic-classes.jar /usr/local/tomcat/webapps/
#
#EXPOSE 8080
#
##RUN mvn tomee:run
#CMD ["catalina.sh", "run"]

FROM maven:3.5.2-jdk-8-alpine

# Create app directory
RUN mkdir -p /usr/src/livrair
WORKDIR /usr/src/livrair

COPY . .

RUN cd Delivery

# Running the client using the starting shell script
ENTRYPOINT ["mvn", "package", "tomee:run"]
