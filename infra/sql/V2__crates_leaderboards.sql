CREATE TABLE crates (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    player_id   BIGINT NOT NULL,
    crate_type  VARCHAR(32) NOT NULL,
    opened_at   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (player_id) REFERENCES players(id)
);

CREATE TABLE leaderboard_stats (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    player_id    BIGINT NOT NULL,
    week_start   DATE   NOT NULL,
    kills        INT    NOT NULL DEFAULT 0,
    deaths       INT    NOT NULL DEFAULT 0,
    playtime     BIGINT NOT NULL DEFAULT 0,
    balance      BIGINT NOT NULL DEFAULT 0,
    won_weeks    INT    NOT NULL DEFAULT 0,
    UNIQUE (player_id, week_start),
    FOREIGN KEY (player_id) REFERENCES players(id)
);