#!/usr/bin/env bash
set -euo pipefail

# 1. Compile the shaded plugin JAR
./gradlew :plugins:claimstone:shadowJar

# 2. Copy it into the plugins volume so Paper sees it
mkdir -p data/paper/plugins
cp plugins/claimstone/build/libs/claimstone-*-all.jar data/paper/plugins/claimstone.jar

# 3. Start (or recreate) the containers
docker compose -f docker/compose.yml --env-file .env.dev up --build -d
echo "✅ Sevenday dev stack is running.
➡  Join via localhost:25565 (Velocity) or localhost:25566 (Paper direct)."