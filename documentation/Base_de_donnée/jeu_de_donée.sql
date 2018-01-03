INSERT INTO role(libelle_role) VALUES ('Admin');
INSERT INTO role(libelle_role) VALUES ('User');
INSERT INTO role(libelle_role) VALUES ('Anonyme');

INSERT INTO utilisateur( nom, prenom, adresse, email, passw, dateinscription, photo, id_role) VALUES ('BAR', 'Khady', '12 Rue soeur Appoline', 'barkhady@gmail.com' ,'IF+dAQrzxvlZlTLlxPvlPPRGmat+6A61g2+Cr+B2cmRinr+hqneiCjSOBcdJbXF1', NOW(),'', 1);
INSERT INTO utilisateur( nom, prenom, adresse, email, passw, dateinscription, photo, id_role) VALUES ('Ndiaye', 'Mame Mbaye', '12 Rue soeur Appoline','mame@gmail.com', 'WTAC1V5ZeuKhdCG0QJhL48Lp6kRD+lLTf6KgQ39qrig/89ZA0Fi2csQXwRcMBgBC', NOW(),'', 2);
INSERT INTO utilisateur( nom, prenom, adresse, email, passw, dateinscription, photo, id_role) VALUES ('FALL', 'Fatou', '12 Rue soeur Appoline','fall@gmail.com', '4ycH8JeGJU07Kh59O2nqoojkNGdL/kNiY9WjQkAf8PzELjWi1vd5/lL5aSWco1a/', NOW(),'', 2);
INSERT INTO utilisateur( nom, prenom, adresse, email, passw, dateinscription, photo, id_role) VALUES ('Henry', 'Nohemy', '12 Rue soeur Appoline','henry@gmail.com', '1c/z7La6zHNCugg319GdM85pKEsxs6Z0XInVKHENRnz7PLeYk0Ry1I2buKLn7pZ3', NOW(),'', 3);

INSERT INTO cotation(type_escalade, libelle_cotation) VALUES ( 'Naturel', '2b');
INSERT INTO cotation(type_escalade, libelle_cotation) VALUES ( 'Artificiel', '3b');
INSERT INTO cotation(type_escalade, libelle_cotation) VALUES ( 'Naturel', '5a');
INSERT INTO cotation(type_escalade, libelle_cotation) VALUES ( '', '4b');


INSERT INTO exposition(libelle_expo) VALUES ('Nord-Sud');
INSERT INTO exposition(libelle_expo) VALUES ('Nord-Ouest');
INSERT INTO exposition(libelle_expo) VALUES ('Nord-Est');
INSERT INTO exposition(libelle_expo) VALUES ('Est-Sud');

INSERT INTO pays(nom_pays) VALUES ('France');
INSERT INTO pays(nom_pays) VALUES ('Sénégal');
INSERT INTO pays(nom_pays) VALUES ('Belgique');
INSERT INTO pays(nom_pays) VALUES ('Italie');
INSERT INTO pays(nom_pays) VALUES ('Maroc');

INSERT INTO classement(libelle_class) VALUES ('Falaises');
INSERT INTO classement(libelle_class) VALUES ('Blocs');

INSERT INTO article(date, titre, contenu, id_user, photoa) VALUES ( NOW(), 'Journée de folie', 'Redierit regina et licet Pompiliani olim temporis auctoritate tamen et et centuriae olim et ut nulla auctoritate canities temporis ut et circumspectum cum circumspectum olim Pompiliani sint sint verecundum pacataeque.', 1, 'article1.jpg');
INSERT INTO article(date, titre, contenu, id_user, photoa) VALUES ( NOW(), 'Trop cool', 'Redierit regina et licet Pompiliani olim temporis auctoritate tamen et et centuriae olim et ut nulla auctoritate canities temporis ut et circumspectum cum circumspectum olim Pompiliani sint sint verecundum pacataeque.', 2, 'article2.jpg');
INSERT INTO article(date, titre, contenu, id_user, photoa) VALUES ( NOW(), 'Journée de folie', 'Redierit regina et licet Pompiliani olim temporis auctoritate tamen et et centuriae olim et ut nulla auctoritate canities temporis ut et circumspectum cum circumspectum olim Pompiliani sint sint verecundum pacataeque.', 3, 'article3.jpg');


INSERT INTO topo(nom, description, nbr_page, id_user, image) VALUES ('Bavella', 'Periculo undique vulgasset longius permovissent periculo fama vulgasset adsiduae abscessere.', 40, 1, 'topo1.jpg');
INSERT INTO topo(nom, description, nbr_page, id_user, image) VALUES ('Antalya', 'Periculo undique vulgasset longius permovissent periculo fama vulgasset adsiduae abscessere.', 50, 1, 'topo2.jpg');
INSERT INTO topo(nom, description, nbr_page, id_user, image) VALUES ('Ailefroide', 'Periculo undique vulgasset longius permovissent periculo fama vulgasset adsiduae abscessere.', 20, 2, 'topo3.jpg');
INSERT INTO topo(nom, description, nbr_page, id_user, image) VALUES ('Fauzan', 'Periculo undique vulgasset longius permovissent periculo fama vulgasset adsiduae abscessere.', 40, 3, 'topo4.jpg');
INSERT INTO topo(nom, description, nbr_page, id_user, image) VALUES ('Flaine', 'Periculo undique vulgasset longius permovissent periculo fama vulgasset adsiduae abscessere.', 100, 1, 'topo6.jpg');
INSERT INTO topo(nom, description, nbr_page, id_user, image) VALUES ('Cimaï', 'Periculo undique vulgasset longius permovissent periculo fama vulgasset adsiduae abscessere.', 75, 4, 'topo7.jpg');


INSERT INTO commentaire(libelle_com, date_com, id_user, id_topo) VALUES ('Antequam placuerat cum eique fieret ponderibus destitutus et clandestinis proximis commentis acciri ponderibus pertinacius eique nocturnis qua aliis difficillimum per.', NOW(), 1, 1);
INSERT INTO commentaire(libelle_com, date_com, id_user, id_topo) VALUES ('Antequam placuerat cum eique fieret ponderibus destitutus et clandestinis proximis commentis acciri ponderibus pertinacius eique nocturnis qua aliis difficillimum per.', NOW(), 2, 1);
INSERT INTO commentaire(libelle_com, date_com, id_user, id_topo) VALUES ('Antequam placuerat cum eique fieret ponderibus destitutus et clandestinis proximis commentis acciri ponderibus pertinacius eique nocturnis qua aliis difficillimum per.', NOW(), 2, 1);
INSERT INTO commentaire(libelle_com, date_com, id_user, id_topo) VALUES ('Antequam placuerat cum eique fieret ponderibus destitutus et clandestinis proximis commentis acciri ponderibus pertinacius eique nocturnis qua aliis difficillimum per.', NOW(), 3, 2);
INSERT INTO commentaire(libelle_com, date_com, id_user, id_topo) VALUES ('Antequam placuerat cum eique fieret ponderibus destitutus et clandestinis proximis commentis acciri ponderibus pertinacius eique nocturnis qua aliis difficillimum per.', NOW(), 4, 3);
INSERT INTO commentaire(libelle_com, date_com, id_user, id_topo) VALUES ('Antequam placuerat cum eique fieret ponderibus destitutus et clandestinis proximis commentis acciri ponderibus pertinacius eique nocturnis qua aliis difficillimum per.', NOW(), 4, 4);


INSERT INTO reservation(date_resa, id_topo, id_user) VALUES ('"2018-01-18"', 1, 1);
INSERT INTO reservation(date_resa, id_topo, id_user) VALUES ('"2018-05-18"', 2, 2);
INSERT INTO reservation(date_resa, id_topo, id_user) VALUES ('"2018-06-20"', 3, 3);


INSERT INTO ville(id_pays, nom_ville, cp) VALUES (1, 'Paris', 75000);
INSERT INTO ville(id_pays, nom_ville, cp) VALUES (1, 'Toulouse', 31000);
INSERT INTO ville(id_pays, nom_ville, cp) VALUES (2, 'Dakar', 44000);
INSERT INTO ville(id_pays, nom_ville, cp) VALUES (2, 'Thies', 44000);
INSERT INTO ville(id_pays, nom_ville, cp) VALUES (3, 'Marrakech', 50000);

INSERT INTO site(nom_site, id_pays, image, id_class) VALUES ('Ablon', 2, 'site1.jpg', 1);
INSERT INTO site(nom_site, id_pays, image, id_class) VALUES ('Cap Dramont', 2, 'site2.jpg', 1);
INSERT INTO site(nom_site, id_pays, image, id_class) VALUES ('Mamelles', 3, 'site3.jpg', 2);
INSERT INTO site(nom_site, id_pays, image, id_class) VALUES ('Ngor', 3, 'site4.jpg', 1);
INSERT INTO site(nom_site, id_pays, image, id_class) VALUES ('Cantobre', 4, 'site5.jpg', 2);
INSERT INTO site(nom_site, id_pays, image, id_class) VALUES ('Badami', 5, 'site6.jpg', 1);

INSERT INTO secteur(id_site, nom_secteur) VALUES (1, 'Dunes');
INSERT INTO secteur(id_site, nom_secteur) VALUES (1, 'Sdf');
INSERT INTO secteur(id_site, nom_secteur) VALUES (2, 'Fidus');
INSERT INTO secteur(id_site, nom_secteur) VALUES (1, 'Iblis');
INSERT INTO secteur(id_site, nom_secteur) VALUES (3, 'Climis');
INSERT INTO secteur(id_site, nom_secteur) VALUES (4, 'Timis');
INSERT INTO secteur(id_site, nom_secteur) VALUES (5, 'Dimiss');
INSERT INTO secteur(id_site, nom_secteur) VALUES (6, 'Ablis');
INSERT INTO secteur(id_site, nom_secteur) VALUES (1, 'Tatos');
INSERT INTO secteur(id_site, nom_secteur) VALUES (3, 'Ringi');



INSERT INTO topo_site(id_site, id_topo) VALUES (1, 1);
INSERT INTO topo_site(id_site, id_topo) VALUES (1, 2);
INSERT INTO topo_site(id_site, id_topo) VALUES (5, 3);
INSERT INTO topo_site(id_site, id_topo) VALUES (2, 4);
INSERT INTO topo_site(id_site, id_topo) VALUES (3, 4);
INSERT INTO topo_site(id_site, id_topo) VALUES (5, 5);
INSERT INTO topo_site(id_site, id_topo) VALUES (5, 6);
INSERT INTO topo_site(id_site, id_topo) VALUES (6, 6);



--1.0302, 2.mame123, 3.lafa123, 4.no123


INSERT INTO voie(id_secteur, id_cotation, nom_voie, altitude, nbr_longueur, id_expo) VALUES ( 1, 1, 'Bellimo', 50, 20, 1);
INSERT INTO voie(id_secteur, id_cotation, nom_voie, altitude, nbr_longueur, id_expo) VALUES ( 1, 2, 'Esdrados', 20, 50, 1);
INSERT INTO voie(id_secteur, id_cotation, nom_voie, altitude, nbr_longueur, id_expo) VALUES ( 1, 3, 'Nabrellera', 30, 20, 2);
INSERT INTO voie(id_secteur, id_cotation, nom_voie, altitude, nbr_longueur, id_expo) VALUES ( 1, 1, 'Nadrassa', 50, 60, 3);
INSERT INTO voie(id_secteur, id_cotation, nom_voie, altitude, nbr_longueur, id_expo) VALUES ( 1, 4, 'Ibliss', 10, 30, 2);
INSERT INTO voie(id_secteur, id_cotation, nom_voie, altitude, nbr_longueur, id_expo) VALUES ( 1, 2, 'Sabrosades', 50, 25, 4);
INSERT INTO voie(id_secteur, id_cotation, nom_voie, altitude, nbr_longueur, id_expo) VALUES ( 1, 3, 'Esmaderas', 60, 30, 1);