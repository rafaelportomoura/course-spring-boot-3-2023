FROM maven:3.9.3-eclipse-temurin-17 AS buildstage

RUN $JAVA_HOME/bin/jlink \
  --add-modules ALL-MODULE-PATH \
  --strip-debug \
  --no-man-pages \
  --no-header-files \
  --compress=2 \
  --output /javaruntime

WORKDIR /opt/app

COPY ./src ./src
COPY ./pom.xml ./pom.xml
RUN mvn clean package -Dmaven.test.skip=true

FROM debian:buster-slim
WORKDIR /opt/app

ENV JAVA_HOME=/opt/java/openjdk
ENV PATH "${JAVA_HOME}/bin:${PATH}"
ENV ARTIFACT_ID "springboot"
ENV VERSION "0.0.1-SNAPSHOT"
ENV ARTIFACT_ID_VERSION "${ARTIFACT_ID}-${VERSION}"
ENV JAR_FILE "${ARTIFACT_ID_VERSION}.jar"

COPY --from=buildstage /javaruntime $JAVA_HOME
COPY --from=buildstage /opt/app/target/${JAR_FILE} .

RUN echo "java -jar $JAR_FILE" >start.sh
CMD [ "/bin/sh", "start.sh" ]

