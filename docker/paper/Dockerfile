FROM eclipse-temurin:21-jre
ARG PAPER_VERSION=1.21

WORKDIR /server
RUN curl -fsSL \
  https://api.papermc.io/v2/projects/paper/versions/${PAPER_VERSION}/builds/latest/downloads/paper-${PAPER_VERSION}.jar \
  -o paper.jar

ENTRYPOINT ["java","-jar","paper.jar","--nogui"]