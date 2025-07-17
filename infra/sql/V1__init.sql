-- Creates tables Flyway will manage
CREATE TABLE players (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid         CHAR(36) NOT NULL UNIQUE,
    name         VARCHAR(16) NOT NULL,
    coins        BIGINT     NOT NULL DEFAULT 0,
    joined_at    DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_seen    DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE countries (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    biome_key     VARCHAR(64) UNIQUE NOT NULL,  -- e.g. minecraft:savanna
    owner_uuid    CHAR(36) NOT NULL,
    beacon_color  VARCHAR(16) NOT NULL,
    radius        INT        NOT NULL DEFAULT 64,
    health        INT        NOT NULL DEFAULT 100,
    created_at    DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE memberships (
    player_uuid  CHAR(36) NOT NULL,
    country_id   BIGINT   NOT NULL,
    role         ENUM('OWNER','ELDER','MEMBER') NOT NULL,
    PRIMARY KEY (player_uuid, country_id)
);