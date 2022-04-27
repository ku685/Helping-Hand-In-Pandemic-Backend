FROM openjdk:1.8
EXPOSE 8090
ADD target/ourhero-project-backend.jar ourhero-project-backend.jar
ENTRYPOINT ["java","-jar","/ourhero-project-backend.jar" ]