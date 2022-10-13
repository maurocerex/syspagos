FROM mcr.microsoft.com/java/jre:11-zulu-centos
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/pagos-0.0.1-SNAPSHOT.jar /app/base.jar
ENTRYPOINT ["java", "-jar", "/app/base.jar"]