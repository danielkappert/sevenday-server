FROM eclipse-temurin:21-jre
ARG VELOCITY_VERSION=3.3.1

WORKDIR /server
RUN curl -fsSL \
  https://api.papermc.io/v2/projects/velocity/versions/${VELOCITY_VERSION}/builds/latest/downloads/velocity-${VELOCITY_VERSION}.jar \
  -o velocity.jar

# Copy plugins later when we mount a volume
ENTRYPOINT ["java","-jar","velocity.jar","--accept-license"]