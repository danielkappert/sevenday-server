FROM eclipse-temurin:21-jre

# ---------- build-time arguments ----------
ARG MC_VERSION=1.21.7          # <- bump when Mojang bumps
ARG USER_AGENT="sevendaybuild/0.1 (dev@sevendayserver.com)"
# -----------------------------------------

WORKDIR /opt/paper

# --- grab curl + jq (5 MiB total) ---
RUN apt-get update \
 && apt-get install -y --no-install-recommends curl jq ca-certificates \
 && rm -rf /var/lib/apt/lists/*

# --- figure out the latest *stable* build id ---
RUN set -eux; \
    BUILD_ID="$(curl -sSL -H "User-Agent: ${USER_AGENT}" \
        "https://fill.papermc.io/v3/projects/paper/versions/${MC_VERSION}/builds" \
        | jq -r 'map(select(.channel=="STABLE"))[0].id')"; \
    echo "Using Paper build ${BUILD_ID} (MC ${MC_VERSION})"; \
# --- download the jar ---
    curl -fSL -H "User-Agent: ${USER_AGENT}" \
      "https://api.papermc.io/v2/projects/paper/versions/${MC_VERSION}/builds/${BUILD_ID}/downloads/paper-${MC_VERSION}-${BUILD_ID}.jar" \
      -o paper.jar

WORKDIR /server
VOLUME ["/server"]

RUN echo "eula=true" > eula.txt

EXPOSE 25565
ENTRYPOINT ["java","-jar","/opt/paper/paper.jar","--nogui"]