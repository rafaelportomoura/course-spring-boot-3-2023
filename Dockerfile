FROM eclipse-temurin:17.0.7_7-jdk AS jdk
RUN $JAVA_HOME/bin/jlink \
  --add-modules java.base \
  --strip-debug \
  --no-man-pages \
  --no-header-files \
  --compress=2 \
  --output /javaruntime

FROM maven:3.9.3-eclipse-temurin-17 as buildstage
WORKDIR /opt/app
COPY ./src ./src
COPY ./pom.xml ./pom.xml
RUN mvn package -Dmaven.test.skip=true


FROM debian:buster-slim
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH "${JAVA_HOME}/bin:${PATH}"
ENV ARTIFACT_ID "springboot"
ENV VERSION "0.0.1-SNAPSHOT"
ENV ARTIFACT_ID_VERSION "${ARTIFACT_ID}-${VERSION}"
ENV JAR_FILE "${ARTIFACT_ID_VERSION}.jar"
COPY --from=jdk /javaruntime $JAVA_HOME
COPY --from=buildstage /opt/app/target/${JAR_FILE} /opt/app/${JAR_FILE}
WORKDIR /opt/app
ENTRYPOINT [ "/bin/sh -c" ]
CMD [ "java", "-jar", "/opt/app/${JAR_FILE}" ]

