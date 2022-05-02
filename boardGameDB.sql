CREATE DATABASE IF NOT EXISTS board_games;

USE board_games;

DROP TABLE IF EXISTS board_games;

CREATE TABLE board_games (
	id int(11) NOT NULL AUTO_INCREMENT,
        name varchar(50) NOT NULL,
        min_player_count int(3) NOT NULL,
        max_player_count int(3) NOT NULL,
        PRIMARY KEY(id)
);
