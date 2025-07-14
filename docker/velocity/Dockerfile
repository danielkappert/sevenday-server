# ─── docker/velocity/Dockerfile ────────────────────────────────────────────────
FROM eclipse-temurin:21-jre

# ---------- build-time arguments ----------
ARG VELOCITY_VERSION=3.4.0-SNAPSHOT
ARG USER_AGENT="sevendaybuild/0.1 (dev@sevendayserver.com)"
# -----------------------------------------

WORKDIR /opt/velocity

# --- grab curl + jq (5 MiB total) ---
RUN apt-get update \
 && apt-get install -y --no-install-recommends curl jq ca-certificates \
 && rm -rf /var/lib/apt/lists/*

# --- figure out the latest *stable* build id ---
WORKDIR /opt/velocity

RUN curl -fsSL -H "User-Agent: ${USER_AGENT}" \
     "https://fill.papermc.io/v3/projects/velocity/versions/${VELOCITY_VERSION}/builds/latest" \
  | jq -r '.downloads["server:default"].url' \
  | xargs -I{} curl -fSL -H "User-Agent: ${USER_AGENT}" {} -o velocity.jar

# Velocity will create /server on first run; keep it separate from /opt/velocity
VOLUME ["/server"]

ENTRYPOINT ["java","-jar","/opt/velocity/velocity.jar"]