INSERT INTO users (first_name, last_name, email, password, account_verified)
VALUES ('Teodora', 'Nencheva', 'Teodora.n@gmail.com', '8872e57546cbfea60f01b1270370d1337adc11b933ca26d6e7d4dc4619c7c51c239d529bc044b6b4', true);

insert into authors (first_name, last_name, biography, photo_url)
values ('William Somerset', 'Maugham', 'William Somerset Maugham, better known as W. Somerset Maugham, was born on January 25, 1874, Paris, France. He was an English novelist, playwright, and short-story writer whose work is characterized by a clear unadorned style, cosmopolitan settings, and a shrewd understanding of human nature.',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Maugham_retouched.jpg/640px-Maugham_retouched.jpg'),
       ('John', 'Ernst Steinbeck Jr', 'John Steinbeck was a Nobel and Pulitzer Prize-winning American novelist and the author of Of Mice and Men, The Grapes of Wrath and East of Eden. Steinbeck dropped out of college and worked as a manual laborer before achieving success as a writer. His works often dealt with social and economic issues.',
        'https://www.newstatesman.com/wp-content/uploads/sites/2/2021/06/gettyimages-2674327-scaled.jpg'),
       ('Victor', 'Hugo', 'Victor Hugo was a French poet and novelist who, after training as a lawyer, embarked on the literary career. He became one of the most important French Romantic poets, novelists and dramatists of his time, having assembled a massive body of work while living in Paris, Brussels and the Channel Islands.',
        'https://www.biography.com/.image/t_share/MTgwMDE5MjYwMDQ5ODU5OTI4/gettyimages-89861094.jpg'),
       ('Terry', 'Pratchett', 'Terry Pratchett was born in 1948 and had his first story published when he was just thirteen. After leaving school at seventeen to become a journalist he continued writing, publishing his first novel, The Carpet People, in 1971 and going on to produce the phenomenally successful Discworld series.',
        'https://cdn.britannica.com/22/182022-050-FA6F34B9/Terry-Pratchett-2011.jpg'),
       ('Isaac', 'Asimov', 'Born on January 2, 1920 in Russia, Isaac Asimov was an American writer specializing in the genre of science fiction. He is considered one of the three great masters of science fiction and remained a significant figure of science fiction for over five decades.',
        'http://t0.gstatic.com/licensed-image?q=tbn:ANd9GcQw62nc7zxAvUDG3LW5DWGX9iJJwpbWcgf-UkLbcc7pXRSGYabmLOLLL4qG6vRoghbi'),
       ('Astrid', 'Lindgren', 'Astrid Lindgren was a famous Swedish author best known for her children’s book series. Raised in a farm near Vimmerby, she had a very happy childhood, playing with her siblings, working at the farm side by side with maids, farmhands, and temporary workers. Stories were also an integral part of her childhood; she listened to her first tale at the age of four when the daughter of a farmhand read out to her about the giant ‘Bam-Bam’ and the fairy ‘Viribunda’, igniting in her a love for them. She eventually became a writer, creating her first story, that of ‘Pippi Longstocking,’ at her daughter’s request. Eventually, she produced 34 chapter books and 41 picture books, which together have sold over 165 million copies and have been translated into numerous languages.',
        'https://images.ctfassets.net/i01duvb6kq77/77bf3963d305d2fcd5450f0d26718e25/c8de906ec138342e2e5680766b844e73/Lindgren_1960.jpg');

insert into genres (name)
values ('Novel'),
       ('Fantasy'),
       ('Science Fiction'),
       ('For Children'),
       ('Poetry');

insert into books (title, author_id, genre_id, price, year_of_publication, summary, image_url)
values ('Of Human Bondage', 1, 1, 20, '1915', 'Of Human Bondage is the story of a young man''s struggle to find the meaning of life in a world that is cruel. Philip Carey has a club foot, making him the subject of cruelty at school and ridicule in the adult world. Philip allows this treatment to warp his personality, making him introspective and solitary.',
        'https://images-na.ssl-images-amazon.com/images/I/417Tou7jR8L._SX348_BO1,204,203,200_.jpg'),
       ('The Moon and Sixpence', 1, 1, 10, '1919', 'The Moon and Sixpence follows the life of one Charles Strickland, a bourgeois city gent whose dull exterior conceals the soul of a genius. Compulsive and impassioned, he abandons his home, wife, and children to devote himself slavishly to painting.',
        'https://kbimages1-a.akamaihd.net/ecd793ac-ee5f-4ef3-93d1-362484b6ef54/1200/1200/False/the-moon-and-sixpence-2.jpg'),
       ('The Razor''s Edge', 1, 1, 10, '1944', 'Somerset Maugham. It tells the story of Larry Darrell, an American pilot traumatized by his experiences in World War I, who sets off in search of some transcendent meaning in his life. The story begins through the eyes of Larry''s friends and acquaintances as they witness his personality change after the war.',
        'https://pictures.abebooks.com/isbn/9780385043793-us.jpg'),
       ('East Of Eden', 2, 1, 20, '1952', 'Set in the rich farmland of California''s Salinas Valley, this sprawling and often brutal novel follows the intertwined destinies of two families—the Trasks and the Hamiltons—whose generations helplessly reenact the fall of Adam and Eve and the poisonous rivalry of Cain and Abel.',
        'https://m.media-amazon.com/images/I/510g2SGySaL.jpg'),
       ('The Grapes of Wrath', 2, 1, 10, '1939', 'The Grapes of Wrath won a Pulitzer Prize and a National Book Award and was made into a notable film in 1940. The novel is about the migration of a dispossessed family from the Oklahoma Dust Bowl to California and describes their subsequent exploitation by a ruthless system of agricultural economics.',
        'https://upload.wikimedia.org/wikipedia/commons/a/ad/The_Grapes_of_Wrath_%281939_1st_ed_cover%29.jpg'),
       ('The Winter of Our Discontent', 2, 1, 10, '1961', 'The story concerns mainly Ethan Allen Hawley of New Bay Town, New York, a former member of Long Island''s aristocratic class. Ethan''s late father lost the family fortune, and thus Ethan works as a grocery store clerk in the store his family once owned.',
        'https://upload.wikimedia.org/wikipedia/en/7/73/Winter_discontent.JPG'),
       ('Notre Dame de Paris', 3, 1, 20, '1831', 'It is the story of Quasimodo, the deformed bell-ringer of the Notre Dame Cathedral, who falls in love with the beautiful gypsy Esmeralda. When Esmeralda is condemned as a witch by Claude Frollo, the tormented archdeacon who lusts after her, Quasimodo attempts to save her; but his intentions are misunderstood.',
        'https://m.media-amazon.com/images/I/51mm-qw36VL.jpg'),
       ('Les Misérables', 3, 1, 10, '1862', 'Les Misérables centres on the character Jean Valjean, an ex-convict in 19th-century France. The story spans many years as it tells of Valjean''s release from prison and reformation as an industrialist while being constantly pursued by the morally strict inspector Javert.',
        'https://kbimages1-a.akamaihd.net/a6bdd3f5-ba60-4ad3-8f6b-5f1427021961/1200/1200/False/les-miserables-305.jpg'),
       ('Selected Poems of Victor Hugo', 3, 5, 15, '1975', 'Although best known as the author of Notre Dame de Paris and Les Misérables, Victor Hugo was primarily a poet—one of the most important and prolific in French history. Despite his renown, however, there are few comprehensive collections of his verse available and even fewer translated editions.',
        'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQ90IM47zvLkLsW3xqg65ATk-9gW6cyAyk9feSDXFfB_1VVKTEC'),
       ('The Colour of Magic', 4, 2, 10, '1983', 'The Colour of Magic is a collection of four stories set on Discworld, a flat planet that is carried by four huge elephants that stand on the back of the giant turtle Great A''Tuin. The stories pivot on the hapless failed wizard Rincewind.',
        'https://bookoholic.net/userfiles/productthumbs/thumb_662.jpg'),
       ('Mort', 4, 2, 10, '1987', 'Mort is a satirical comedy about how Death takes young Mort from the fields, teaches him to collect souls, and takes a holiday to experience human pleasures. In Death''s absence, Mort prevents an assassination and needs strong magic to repair the rift in history.',
        'https://upload.wikimedia.org/wikipedia/en/9/9e/Mort-cover.jpg'),
       ('Good Omens', 4, 2, 10, '1990', 'The book is a comedy about the birth of the son of Satan and the coming of the End Times. There are attempts by the angel Aziraphale and the demon Crowley to sabotage the coming of the end times, having grown accustomed to their comfortable surroundings in England.',
        'https://images-na.ssl-images-amazon.com/images/I/51L3K-CJW7L._SX319_BO1,204,203,200_.jpg'),
       ('Foundation', 5, 3, 10, '1951', 'First published in 1951, Isaac Asimov''s space saga Foundation tells the story of a secretive science institute that tries to save a galactic empire from the worst effects of its coming collapse. Four of the five interrelated stories comprising the novel appeared in Astounding Science Fiction between 1942 and 1944.',
        'https://www.ciela.com/media/catalog/product/f/o/foundation-book-1-isaac-asimov-9780008520038_1.jpg'),
       ('I, Robot', 5, 3, 10, '1950', '"I, Robot" is the title of a 1950 novel by the famous science fiction author Isaac Asimov. The novel consists of a collection of short stories all bound together by the pretense that they are being told for an interview with the elderly, successful robopsychologist Susan Calvin in the year 2052. The stories were originally separate, however and appeared in two different American magazines called: Super Science Stories and Astounding Science Fiction between 1940 and 1950.',
        'https://upload.wikimedia.org/wikipedia/en/d/d5/I_robot.jpg'),
       ('The Last Question', 5, 3, 10, '1956', 'The story centers around Multivac, a self-adjusting and self-correcting computer. Multivac had been fed data for decades, assessing data and answering questions, allowing man to reach beyond the planetary confines of Earth. However, in the year 2061, Multivac began to understand deeper fundamentals of humanity.',
        'https://insp.re/uploads/covers/books/book-gr-10-212-last-question-and-other-stories-eabe2.jpg'),
       ('Pippi Longstocking', 6, 4, 10, '1945', 'Pippi Longstocking, a child of incredible strength, moves by herself to a East-European town with her monkey, horse, and a bag full of gold coins. She befriends two children, Annika and Tommy, shocks the adults of the town, defends herself from bandits, and has a reunion with her seafaring father.',
        'https://cdn.ozone.bg/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/p/i/6f082ff485f3d83c7ac4b90578f75217/pipi-dalgoto-chorapche-luksozno-izdanie-s-tvardi-koritsi-30.jpg'),
       ('Emil of Lonneberga', 6, 4, 10, '1963', 'Even if five-year-old Emil looks angelic, everybody knows he''s wild and stubborn, and gets up to more mischief than there are days in the year. He''s a clever and curious scatterbrain with an amazing gift for turning his God-fearing and respectable community, in the county of Småland, upside down.',
        'https://upload.wikimedia.org/wikipedia/en/thumb/5/5b/EmilIL%C3%B6nneberga.jpg/220px-EmilIL%C3%B6nneberga.jpg'),
       ('Karlsson on the Roof', 6, 4, 10, '1955', 'Karlson on the roof is a really funny book about a boy called Smidge who finds there is a funny little man living on his roof. The man on the roof is very cheeky and small. He also has a propeller on his back that allows him to fly around. They get up to lots of mischief and adventures together.',
        'https://www.ciela.com/media/catalog/product/cache/332cf88b637d37883ec9cea105be873e/k/a/karlson-kojto-jivee-na-pokriva1.jpg');

