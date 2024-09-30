FROM amd64/eclipse-temurin:17-jdk

COPY ./build/libs/arabook-0.0.1-SNAPSHOT.jar AraBookServer.jar

ARG SPRING_PROFILES_ACTIVE

ENV SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE

CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "AraBookServer.jar"]