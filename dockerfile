FROM openjdk:17
# LABEL org.opencontainers.image.source=https://github.com/FedAGcom/Co_Back

# copy the packaged jar file into our docker image
COPY target/demo-0.0.1-SNAPSHOT.jar /demo.jar

# set the startup command to execute the jar file
CMD ["java", "-jar", "/demo.jar"]