

--matiere premiere
CREATE TABLE matiere_premiere(
    id_matiere_premiere SERIAL PRIMARY KEY,
    libelle VARCHAR(150),
    viscosite int NOT NULL
);

CREATE TABLE stock_matiere_premiere(
    id_stock_matiere_premiere SERIAL PRIMARY KEY,
    id_matiere_premiere INT,
    quantite INT CHECK(quantite > 0),
    date_stock_matiere_premiere DATE NOT NULL,
    mouvement int CHECK(mouvement=0 OR mouvement=1),
    FOREIGN KEY (id_matiere_premiere) REFERENCES matiere_premiere(id_matiere_premiere) ON DELETE CASCADE ON UPDATE CASCADE
);

--produit
CREATE TABLE produit(
    id_produit SERIAL PRIMARY KEY,
    id_matiere_premiere INT,
    libelle VARCHAR(150),
    prix_unitaire DECIMAL(12,2),
    FOREIGN KEY (id_matiere_premiere) REFERENCES matiere_premiere(id_matiere_premiere) ON DELETE CASCADE ON UPDATE CASCADE
);

--production
create table production(
    id serial primary key,
    id_produit INT,
    date_production DATE NOT NULL,
    quantite int,
    FOREIGN KEY(id_produit)REFERENCES produit(id_produit) ON DELETE CASCADE ON UPDATE CASCADE
);



--vente
CREATE TABLE vente(
    id_vente SERIAL PRIMARY KEY,
    id_produit INT,
    quantite INT CHECK (quantite > 0),
    date_vente DATE NOT NULL,
    prix_unitaire DOUBLE PRECISION CHECK(prix_unitaire >= 0),
    total DOUBLE PRECISION CHECK(total >= 0),
    FOREIGN KEY (id_produit) REFERENCES produit(id_produit) ON DELETE CASCADE ON UPDATE CASCADE
);

--depense
CREATE TABLE depense(
    id_depense SERIAL PRIMARY KEY,
    raison VARCHAR(150),
    montant DECIMAL(12,2) CHECK(montant > 0),
    date_depense DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS Poste (
  id_Poste SERIAL PRIMARY KEY,
  poste VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS personnel (
  id_Personnel SERIAL PRIMARY KEY,
  id_Poste INT NOT NULL,
  nom VARCHAR(50) NOT NULL,
  prenom VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  date_embauche timestamp NOT NULL,
  salaire DECIMAL(10,2) NOT NULL,
  addresse VARCHAR(50) NOT NULL,
  numero_telephone VARCHAR(20),
  date_naissance timestamp
);
-- front-office
create table personne(
    id_personne serial primary key,
    id_personnel int REFERENCES personnel(id_personnel),
    email varchar(250),
    mot_de_passe varchar(250),
    -- tsotra sa admin
    etat varchar(10)
);

create Table if NOT EXISTS motif(
   id_Motif serial primary key  ,
   motif varchar(255) not NULL
);

CREATE TABLE IF NOT EXISTS presence (
  id_Presence serial PRIMARY KEY,
  Date_Presence timestamp,
  Heure_Debut TIME NOT NULL ,
  Heure_Fin TIME ,
  id_Personnel INT,
  FOREIGN KEY (id_Personnel) REFERENCES personnel(id_Personnel)
);

CREATE TABLE IF NOT EXISTS Absence (
  id_Absence SERIAL PRIMARY KEY,
  id_Personnel INT NOT NULL,
  Date_Absence DATE NOT NULL,
  id_Motif INT NOT NULL,
  FOREIGN KEY (id_Personnel) REFERENCES personnel(id_Personnel),
  FOREIGN KEY (id_Motif) REFERENCES motif(id_Motif)
);

create table conge
(
    id_conge serial primary key,
    id_personnel int references personnel(id_personnel),
    date_debut_conge Date not null,
    duree_conge int not null
);
