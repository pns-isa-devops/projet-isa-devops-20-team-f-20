FROM tomcat:8.0

COPY ./Delivery/target/delivery.war /usr/local/tomcat/webapps/
COPY ./Delivery/target/delivery-classes.jar /usr/local/tomcat/webapps/
COPY ./Logistic/target/logistic.war /usr/local/tomcat/webapps/
COPY ./Logistic/target/logistic-classes.jar /usr/local/tomcat/webapps/

EXPOSE 8080

#RUN mvn tomee:run
CMD ["catalina.sh", "run"]
