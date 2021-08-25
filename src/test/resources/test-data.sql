INSERT INTO `genre` (`name`, `description`) VALUES ('TestGenre', 'TestDescription');
INSERT INTO `artist` (`name`) VALUES ('TestArtist');
INSERT INTO `playlist` (`name`, `description`, `artwork`) VALUES ('TestPlaylist', 'TestDescription', 'TestArtwork');
INSERT INTO `album` (`name`, `artist_id`, `genre_id`, `cover`) VALUES ('TestAlbum', 1, 1, 'TestCover');
