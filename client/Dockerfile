FROM openjdk:8

# Create app directory
RUN mkdir -p /usr/src/livrair_client
WORKDIR /usr/src/livrair_client

COPY target/livrair-client-1.0-SNAPSHOT-jar-with-dependencies.jar livrair-client.jar

# Running the client using the starting shell script
ENTRYPOINT ["java", "-jar", "./livrair-client.jar"]

# Default arguments to be forwarded to the entry point
CMD ["localhost", "8080"]
