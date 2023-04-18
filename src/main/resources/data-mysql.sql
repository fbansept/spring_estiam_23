INSERT INTO pays(nom) VALUES ("France"),("Allemagne"),("Espagne");

INSERT INTO diplome(nom) VALUES ("Fullstack"),("DevOps"),("DevWeb");

INSERT INTO utilisateur(prenom,nom,pays_id) VALUES ("Franck","BANSEPT",1),("Tom","Sawyer",1),("john","Doe",3);

INSERT INTO diplome_utilisateur(utilisateur_id,diplome_id)
VALUES (1,1),(1,2),(1,3),(2,3),(3,1);