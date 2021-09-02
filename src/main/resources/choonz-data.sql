INSERT INTO GENRE VALUES (1, 'Rock is cool', 'Rock');
INSERT INTO GENRE VALUES (2, 'Pop is fun', 'Pop');
INSERT INTO GENRE VALUES (3, 'Country is happy', 'Country');
INSERT INTO GENRE VALUES (4, 'Rap is powerful', 'Rap');

INSERT INTO PLAYLIST (`id`, `name`, `description`, `artwork`) VALUES (1, 'Relaxing', 'Chill songs for people who can’t chill ', 'Relaxing');
INSERT INTO PLAYLIST (`id`, `name`, `description`, `artwork`) VALUES (2, 'Morning', 'Songs to sing in the shower to', 'Morning');
INSERT INTO PLAYLIST (`id`, `name`, `description`, `artwork`) VALUES (3, 'Summertime', 'Feel Good Songs', 'Summertime');
INSERT INTO PLAYLIST (`id`, `name`, `description`, `artwork`) VALUES (4, 'Workout', 'listen to this playlist for a Non-Stop Workout. Music is energy!', 'Workout');
INSERT INTO PLAYLIST (`id`, `name`, `description`, `artwork`) VALUES (5, 'Happy', 'listen to this playlist for a happy feeling... Be positive!', 'Happy');

INSERT INTO `artist` (`id`, `name`) VALUES (1, 'Arctic Monkeys');
INSERT INTO `artist` (`id`, `name`) VALUES (2, 'Dave');
INSERT INTO `artist` (`id`, `name`) VALUES (3, 'The Rolling Stones');
INSERT INTO `artist` (`id`, `name`) VALUES (4, 'Travis Scott');
INSERT INTO `artist` (`id`, `name`) VALUES (5, 'Summer Walker');

INSERT INTO `album` (`id`, `name`, `cover`, `artist_id`, `genre_id`) VALUES (1, 'We are alone in this together', 'We are alone in this together', 2 , 2);
INSERT INTO `album` (`id`, `name`, `cover`, `artist_id`, `genre_id`) VALUES (2, 'Astroworld', 'Astroworld', 4, 4);
INSERT INTO `album` (`id`, `name`, `cover`, `artist_id`, `genre_id`) VALUES (3, 'Over it', 'Over it', 5 , 2);
INSERT INTO `album` (`id`, `name`, `cover`, `artist_id`, `genre_id`) VALUES (4, 'AM', 'AM', 1, 1);
INSERT INTO `album` (`id`, `name`, `cover`, `artist_id`, `genre_id`) VALUES (5, 'Tattoo You', 'Tattoo You', 3 , 3);

INSERT INTO `track` (`name`, `duration`, `lyrics`, `album_id`, `playlist_id`) VALUES ('Law Of Attraction','211','You keep talkin, Ive been silent Now, whats the point to watch when you cant read between? The lines you crossed, Im sick of fightin, yeah you keep trying, that dont mean a thing', 1, 3);
INSERT INTO `track` (`name`, `duration`, `lyrics`, `album_id`) VALUES ('Clash','190','Jordan 4s or Jordan 1s, Rolexes, got more than one My AP cost thirty-one, millimeters forty-one', 1);
INSERT INTO `track` (`name`, `duration`, `lyrics`, `album_id`) VALUES ('Twenty To One','153','Twenty-to-one look at the time, its twenty-to-one Im on the ride with the guys, and were lookin for him If we catch him its twenty to one', 1);
INSERT INTO `track` (`name`, `duration`, `lyrics`, `album_id`, `playlist_id`) VALUES ('In The Fire','203','I know I got religion I belong to the laws and crew, You see we fought when we get happy Thats the way we Christian do', 1, 4);
INSERT INTO `track` (`name`, `duration`, `lyrics`, `album_id`) VALUES ('Both Sides Of A Smile','435','You give a woman a brick and youll get a house from it, The first time splittin up always the hardest thing, Loves a film and Im just flickin through the parts Im in', 1);
INSERT INTO `track` (`name`, `duration`, `lyrics`, `album_id`, `playlist_id`) VALUES ('Butterfly Effect','300','For this life, I cannot change, Hidden Hills, deep off in the main, M&Ms, sweet like candy cane, Drop the top, pop it, let it bang',2, 4);
INSERT INTO `track` (`name`, `duration`, `lyrics`, `album_id`) VALUES ('Coffee Bean','280','Bad-bad news, Im just bad-bad news Good thing, the two, Bonnie and Clyde The money and who?', 2);
INSERT INTO `track` (`name`, `duration`, `lyrics`, `album_id`, `playlist_id`) VALUES ('Sicko Mode','340','Sun is down, freezin cold, Thats how we already know winters here, My dawg would prolly do it for a Louis beltThats just all he know, he dont know nothin else', 2, 3);
INSERT INTO `track` (`name`, `duration`, `lyrics`, `album_id`, `playlist_id`) VALUES ('Stargazing','220','I was hot as heck out in the heat, Then a storm came in and saved my life, Head up to the sky, down on my knees (Straight up)Out of nowhere, you came here to save the night', 2, 4);
INSERT INTO `track` (`name`, `duration`, `lyrics`, `album_id`) VALUES ('Astrothunder','190','Seem like the life I feel, Seem like the life it feels a little distant, yeah, Seems like the life I need Seems, like the life I needs a little distant, yeah', 2);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`, `playlist_id`) VALUES ('Playing Games','232','If you claim you want me, it aint no thang, You actin kinda shady, you aint been callin me, "Baby"Boy, you can go and stop playing games, playing games', 3, 1);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`) VALUES ('Nobody Else','132','Tell me what you see in me?Gotta be, something different, You must be made for me, patience is everything', 3);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`, `playlist_id`) VALUES ('Anna Mae','290','Whats temporary? Me or you?You want me here to stay (stay)But your situation tell me that its a waste of time, But never mind all that', 3, 1);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`) VALUES ('Fun Girl','174','I remember what you told me, Said I wasnt made right, Said I wasnt cut right, Thats why Im so lonely', 3);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`, `playlist_id`) VALUES ('Come Thru','210','Got me takin a step on the wild side, Cuttin all ties with them other guys, Way you, way you look in my eyes', 3, 2);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`, `playlist_id`) VALUES ('Do I wanna Know?','250','Crawlin back to youEver thought of callin when Youve had a few?Cause I always do', 4,2);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`) VALUES ('R U Mine?','344',' I cant help myself, All I wanna ever say is, "Are you mine?" Well, are you mine?Are you mine?', 4);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`, `playlist_id`) VALUES ('Arabella','201','Shes got a Barbarella silver swimsuit, And when she needs to shelter from reality She takes a dip in my daydreams', 4, 3);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`, `playlist_id`) VALUES ('One For The Road','300','One for the road, So we all get back to yours and you sit and talk to me on the floor Theres no need to show me round baby I feel like Ive been here before', 4, 1);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`) VALUES ('Fireside','240','I cant explain but I wanna try, Theres this image of you and I, And it goes dancing by, In the morning and in the night time', 4);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`) VALUES ('Start Me Up','110','If you start it up, Kick on the starter give it all you got, you got, you got I cant compete with the riders in the other heats', 5);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`, `playlist_id`) VALUES ('Hang Fire','250','In the sweet old country where I come from, Nobody ever works, Yeah nothing gets done, We hang fire, we hang fire', 5, 3);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`) VALUES ('Slave','315','Dont wanna be your slave Twenty-four hours a dayHey, why dont you go down to the supermarket?Get something to eat, steal something off the shelves, Pass by the liquor store, be back about quarter to twelve', 5);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`, `playlist_id`) VALUES ('Tops','130','Hey baby, Every man is the same come on, Ill make you a star, Ill take you a million miles from all this put you on a pedestal, Come on, come on', 5,4);
INSERT INTO `track` (`name`,`duration`,`lyrics`,`album_id`) VALUES ('No Use In Crying','340','Aint no use in cryingm, Stay away from me, Aint no use in crying, Stay away from me', 5);

SELECT * FROM album;
SELECT * FROM artist;
SELECT * FROM playlist;
SELECT * FROM track;
SELECT * FROM genre;