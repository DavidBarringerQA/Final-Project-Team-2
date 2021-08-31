DROP TABLE if EXISTS album CASCADE;
DROP TABLE if EXISTS artist CASCADE; 
DROP TABLE if EXISTS genre CASCADE;
DROP TABLE if EXISTS playlist CASCADE;
DROP TABLE if EXISTS track CASCADE;
CREATE TABLE album (id bigint GENERATED BY DEFAULT AS identity, cover varchar(255), NAME varchar(100) NOT null, artist_id bigint, genre_id bigint, PRIMARY KEY (id));
CREATE TABLE artist (id bigint GENERATED BY DEFAULT AS identity, NAME varchar(100) NOT null, PRIMARY KEY (id));
CREATE TABLE genre (id bigint GENERATED BY DEFAULT AS identity, description varchar(250) NOT null, NAME varchar(100) NOT null, PRIMARY KEY (id));
CREATE TABLE playlist (id bigint GENERATED BY DEFAULT AS identity, artwork varchar(1000) NOT null, description varchar(500) NOT null, NAME varchar(100) NOT null, PRIMARY KEY (id));
CREATE TABLE track (id bigint GENERATED BY DEFAULT AS identity, duration integer NOT null, lyrics varchar(255), NAME varchar(100) NOT null, album_id bigint, playlist_id bigint, PRIMARY KEY (id));
ALTER TABLE album ADD CONSTRAINT UK_7io8ua6qgnb7yjn96tvrakvsk UNIQUE (NAME);
ALTER TABLE artist ADD CONSTRAINT UK_r03a96lqhsb7djq2tn4rq60vn UNIQUE (NAME);
ALTER TABLE genre ADD CONSTRAINT UK_dy2vbbbf0mb52fy9tks9anm3k UNIQUE (description);
ALTER TABLE genre ADD CONSTRAINT UK_ctffrbu4484ft8dlsa5vmqdka UNIQUE (NAME);
ALTER TABLE playlist ADD CONSTRAINT UK_swyw77f2jscjvdd29t0s2jvfv UNIQUE (artwork);
ALTER TABLE playlist ADD CONSTRAINT UK_rqydw3x69vu1xbk7m6flxi0bk UNIQUE (description);
ALTER TABLE playlist ADD CONSTRAINT UK_gmx0jjome08oqihtks37w0lea UNIQUE (NAME);
ALTER TABLE track ADD CONSTRAINT UK_33mfukvakfr3t9ri1og4jhg4l UNIQUE (NAME);
ALTER TABLE album ADD CONSTRAINT FKmwc4fyyxb6tfi0qba26gcf8s1 FOREIGN KEY (artist_id) REFERENCES artist;
ALTER TABLE album ADD CONSTRAINT FKmhihrmrx2f1mhqtrbagwru45g FOREIGN KEY (genre_id) REFERENCES genre;
ALTER TABLE track ADD CONSTRAINT FKaxm9pbgk7ptorfyk3o6911q05 FOREIGN KEY (album_id) REFERENCES album;
ALTER TABLE track ADD CONSTRAINT FKnebkq7qmpex9wivvxbfqtlkl4 FOREIGN KEY (playlist_id) REFERENCES playlist;
