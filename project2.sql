
create database if not exists ud2projecto;
use ud2projecto;
 create table if not exists fish (
 id int not null primary key,
 name varchar(100) not null,
 shadow  varchar(100) not null,
 Price int not null,
 pricecj int not null,
 Catch varchar(500)
 
 
 ) engine innodb;
create table if not exists fossil (
name varchar(100) not null primary key,
price int not null,
Museum varchar(500) not null


) engine innodb;
create table if not exists bug (
id int not null primary key,
name varchar(100) not null,
price int not null,
priceflick int not null,
catch varchar(500) not null
) engine innodb;

insert into  fish (id,name,shadow,Price,pricecj,Catch) 
values 
(1,'bitterling','Smallest(1)',900,1350,'I caught a bitterling! Its mad at me, but only a little.'),
(2,'pale_chub','Smallest(1)',200,300,'I caught a pale chub! That name seems a bit judgy...'),
(3,'crucian_carp','Small(2)',160,240,'I caught a crucian carp! My skills are sharp'),
(4,'dace','Medium(3)',240,360,'I caught a dace! Hope I have some space!'),
(5,'carp','Medium(4)',300,450,'I caught a carp! If I catch another they can carpool!'),
(6,'koi','Medium(4)',4000,6000,'I caught a koi! I dont know why its so shy... or such a bad speller...'),
(7,'goldfish','Smallest(1)',1300,1950,'I caught a goldfish! Its worth its weight in fish!'),
(8,'pop-eyed_goldfish','Smallest(1)',1300,1950,'I got a pop-eyed goldfish! It looks so...surprised!'),
(9,'ranchu_goldfish','Small(2)',4500,6750,'I caught a ranchu goldfish! But I prefer balsamicu goldfish!'),
(10,'killifish','Smallest (1)',300,450,'I caught a killifish! The streams are safe again.');

insert into bug (id,name,price,priceflick,catch)
values
(1,'common_butterfly',160,240,'I caught a common butterfly! They often flutter by!'),
(2,'yellow_butterfly',160,240,'I caught a common butterfly! They often flutter by!I caught a yellow butterfly! Shouldnt all BUTTERflies be yellow?'),
(3,'tiger_butterfly',240,360,"I caught a tiger butterfly! I've earned my stripes!"),
(4,'peacock_butterfly',2500,3750,"I caught a peacock butterfly! Now it's my turn to strut my stuff!"),
(5,'common_bluebottle',300,450,"I caught a common bluebottle! I'll put it in a rare green jar!"),
(6,'paper_kite_butterfly',1000,1500,"I caught a paper kite butterfly! Do I read it, fly it, or spreadit on toast?"),
(7,'great_purple_emperor',3000,4500,"I caught a great purple emperor! Its purple reign is over now!"),
(8,'monarch_butterfly',140,210,'I caught a monarch butterfly! Guess the butterflies are a democracy now!'),
(9,'emperor_butterfly',4000,6000,"I caught an emperor butterfly! It's not your average monarch!"),
(10,'agrias_butterfly',3000,4500,'I caught an agrias butterfly! I wonder if it finds me disagrias-able?');

insert into fossil (name,price,Museum) values
('acanthostega',2000,"The acanthostega! Said to be one of the earliest amphibians, it existed well before dinosaurs. Because they lived as fish not long before, they still had gills and very webbed \"hands.\". To toss away the life they knew and venture onto unknown lands... they must have been very brave! Hmm... Does it still count as bravery if you have no understanding of what you're doing?"),
('amber',1200,"Amber is formed from the sap of ancient trees that hardened over time. Because of its beauty, it has often been traded and used as jewelry throughout history. However, individual specimens may contain ancient plants or insects trapped inside them! These are valuable resources for learning about ancient eras, such as when the dinosaurs roamed... And this is why they are sometimes displayed in certain...ahem... exceptional museums! Like mine."),
('ammonite',1100,"Ammonites were creatures that lived before and all the way through the age of dinosaurs! Because different species lived at different times, their shells are sometimes used as \"index fossils.\". In other words, these creatures act as markers in time, helping to identify the age of other formations! Who knows what other secrets lie hidden in those spiral shells?"),
('ankylo_skull',3500,"Oho! Ankylosaurus was the herbivore hero, the grazing gladiator, the vegetarian barbarian of antiquity! Between its club-like tail, heavy armor, and honest-to-goodness SPIKES, it was a formidable beast! Can you keep a secret? I have even heard recent theories that it actively ATTACKED predators. Can you imagine such behavior in an herbivore? It simply beggars the imagination!"),
('ankylo_tail',2500,"Oho! Ankylosaurus was the herbivore hero, the grazing gladiator, the vegetarian barbarian of antiquity! Between its club-like tail, heavy armor, and honest-to-goodness SPIKES, it was a formidable beast! Can you keep a secret? I have even heard recent theories that it actively ATTACKED predators. Can you imagine such behavior in an herbivore? It simply beggars the imagination!"),
("ankylo_torso",3000,"Oho! Ankylosaurus was the herbivore hero, the grazing gladiator, the vegetarian barbarian of antiquity! Between its club-like tail, heavy armor, and honest-to-goodness SPIKES, it was a formidable beast! Can you keep a secret? I have even heard recent theories that it actively ATTACKED predators. Can you imagine such behavior in an herbivore? It simply beggars the imagination!"),
("anomalocaris",2000,"Anomalocaris lived in the water long before the dinosaurs and are known for their, er, \"distinctive\" look. Flat bodies over three feet in length, bulging eyes like a...dragonfly, antennae like shrimp tails... They looked so peculiar that people originally thought they were multiple fossils stacked on top of each other! As a delightful side note, \"anomalocaris\" means \"abnormal shrimp\". Obviously this animal has a certain reputation in the scientific community!"),
("archaeopteryx",1300,"Archaeopteryx's feathers led many people to believe it was the progenitor of the birds, eh wot... Sadly, further evidence indicates it's likely not a direct ancestor—more an evolutionary \"uncle\", if you will. Every time a specimen is found, new theories pop up. And new relatives come to roost in the family tree!"),
("archelon_skull",4000,"Ah, yes. Archelon. It was a sort of huge sea turtle. The largest thus far found, if you want to know. They were very sizable—some 13 feet long, with a shell the size of a small car... If you're into that sort of thing. They likely ate seaweed, shrimp, octopus, and possibly ammonites, given the era involved. It seems CERTAIN giant turtles had to be prima donnas and eat some of the oldest life forms on earth!"),
("archelon_tail",3500,"Ah, yes. Archelon. It was a sort of huge sea turtle. The largest thus far found, if you want to know. They were very sizable—some 13 feet long, with a shell the size of a small car... If you're into that sort of thing. They likely ate seaweed, shrimp, octopus, and possibly ammonites, given the era involved. It seems CERTAIN giant turtles had to be prima donnas and eat some of the oldest life forms on earth!");

