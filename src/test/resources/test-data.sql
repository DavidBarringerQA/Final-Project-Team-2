INSERT INTO `genre` (`name`, `description`) VALUES ('TestGenre', 'TestDescription');
INSERT INTO `artist` (`name`) VALUES ('TestArtist');
INSERT INTO `playlist` (`name`, `description`, `artwork`) VALUES ('TestPlaylist', 'TestDescription', 'TestArtwork');
INSERT INTO `album` (`name`, `artist_id`, `genre_id`, `cover`) VALUES ('TestAlbum', 1, 1, 'TestCover');
INSERT INTO `track` (`name`, `duration`, `lyrics`, `album_id`, `playlist_id`) VALUES ('TestTrack', 100, 'TestLyrics', 1, 1);
INSERT INTO `choonz_user` (`username`, `password`, `role`) VALUES ('user', '$2a$10$IgkbDVtPV7I5gtFbfHmf7uzZ4VHs5btghmil/N.YupTf7ZsDnpvwy', 'ROLE_USER');
