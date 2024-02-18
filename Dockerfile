FROM openjdk:17-jdk-alpine
COPY ./build/libs/EasySalesAssistant-0.0.1-SNAPSHOT.jar ./EasySalesAssistantApplication.jar
ENTRYPOINT [ "java","-jar","EasySalesAssistantApplication.jar" ]