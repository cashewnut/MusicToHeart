FROM java:8-jre

ADD ./target/server-1.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/server-1.0.jar"]

EXPOSE 14567