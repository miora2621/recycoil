-- Personne


-- Matière première
INSERT INTO matiere_premiere (libelle, viscosite)
VALUES
    ('Huile moteur usagée - Grade 10W-30', 50),
    ('Huile moteur usagée - Grade 5W-40', 45),
    ('Huile moteur usagée - Grade 20W-50', 60),
    ('Huile moteur usagée - Grade 15W-40', 55),
    ('Huile moteur usagée - Grade 10W-40', 48);

INSERT INTO stock_matiere_premiere (id_matiere_premiere, quantite, date_stock_matiere_premiere, mouvement)
VALUES
    (1, 2500, '2024-05-01', 1),
    (2, 2000, '2024-05-05', 1),
    (3, 2300, '2024-05-10', 1),
    (4, 1800, '2024-05-15', 1),
    (5, 2100, '2024-05-20', 1),
    (1, 2800, '2024-06-01', 1),
    (2, 2400, '2024-06-05', 1),
    (3, 2600, '2024-06-10', 1),
    (4, 2100, '2024-06-15', 1),
    (5, 2400, '2024-06-20', 1),
    (1, 3000, '2024-07-01', 1),
    (2, 2800, '2024-07-05', 1),
    (3, 2900, '2024-07-10', 1),
    (4, 2400, '2024-07-15', 1),
    (5, 2700, '2024-07-20', 1);

-- Produit
INSERT INTO produit (id_matiere_premiere, libelle, prix_unitaire)
VALUES
    (1, 'Huile moteur recyclée - Grade 10W-30', 3.99),
    (2, 'Huile moteur recyclée - Grade 5W-40', 4.25),
    (3, 'Huile moteur recyclée - Grade 20W-50', 3.75),
    (4, 'Huile moteur recyclée - Grade 15W-40', 4.10),
    (5, 'Huile moteur recyclée - Grade 10W-40', 4.05);

-- Production
INSERT INTO production (id_produit,date_production,quantite)
VALUES
    (1, '2024-05-18',90),
    (2, '2024-05-21',70),
    (3, '2024-05-23',30),
    (4, '2024-05-25',10),
    (5, '2024-05-27',50),
    (1, '2024-06-18',50),
    (2, '2024-06-21',50),
    (3, '2024-06-23',50),
    (4, '2024-06-25',60),
    (5, '2024-06-27',50),
    (1, '2024-07-18',50),
    (2, '2024-07-21',40),
    (3, '2024-07-23',50),
    (4, '2024-07-25',30),
    (5, '2024-07-27',20);


-- Vente
INSERT INTO vente (id_produit, quantite, date_vente, prix_unitaire, total)
VALUES
    (1, 150, '2024-05-19', 3.99, 597.00),
    (2, 120, '2024-05-22', 4.25, 510.00),
    (3, 180, '2024-05-24', 3.75, 675.00),
    (4, 100, '2024-05-26', 4.10, 410.00),
    (5, 130, '2024-05-28', 4.05, 526.50),
    (1, 200, '2024-06-19', 3.99, 798.00),
    (2, 180, '2024-06-22', 4.25, 765.00),
    (3, 220, '2024-06-24', 3.75, 825.00),
    (4, 150, '2024-06-26', 4.10, 615.00),
    (5, 190, '2024-06-28', 4.05, 769.50),
    (1, 250, '2024-07-19', 3.99, 997.50),
    (2, 220, '2024-07-22', 4.25, 935.00),
    (3, 260, '2024-07-24', 3.75, 975.00),
    (4, 180, '2024-07-26', 4.10, 738.00),
    (5, 230, '2024-07-28', 4.05, 930.75);

-- Dépense
INSERT INTO depense (raison, montant, date_depense)
VALUES
('Maintenance des équipements de recyclage', 3000.00, '2024-05-10'),
('Salaires du personnel', 17000.00, '2024-06-05'),
('Facture d''électricité', 2400.00, '2024-06-15'),
('Achat de consommables', 1600.00, '2024-06-20'),
('Formation du personnel', 2000.00, '2024-07-05'),
('Frais de communication', 1000.00, '2024-07-12'),
('Entretien des machines de recyclage', 2000.00, '2024-07-18'),
('Assurance des installations', 1500.00, '2024-05-01'),
('Taxes et impôts', 3000.00, '2024-06-01'),
('Licence et autorisations', 800.00, '2024-07-01'),
('Frais de comptabilité', 1200.00, '2024-05-15'),
('Frais de publicité et marketing', 2500.00, '2024-06-15'),
('Fournitures de bureau', 900.00, '2024-07-15'),
('Frais de formation continue', 1500.00, '2024-05-20'),
('Frais de déplacement', 1800.00, '2024-06-20'),
('Frais d''analyse des huiles', 1000.00, '2024-07-20');

INSERT INTO Poste (poste) VALUES
    ('Directeur'),
    ('Responsable Production'),
    ('Responsable Logistique'),
    ('Responsable Commercial'),
    ('Comptable'),
    ('Technicien de maintenance'),
    ('Opérateur de production'),
    ('Magasinier'),
    ('Vendeur'),
    ('Assistant administratif');

INSERT INTO personnel (id_Poste, nom, prenom, email, date_embauche, salaire, addresse, numero_telephone, date_naissance)
VALUES
    (1, 'Rajerison', 'Jean', 'rajerison@recycle-huile.mg', '2020-03-01', 2000.00, '123 Rue des Fleurs', '0321456789', '1985-07-15'),
    (2, 'Rakoto', 'Marie', 'rakoto@recycle-huile.mg', '2021-06-15', 2200.00, '456 Rue des Arbres', '0321789456', '1988-11-30'),
    (3, 'Ranaivo', 'Lova', 'ranaivo@recycle-huile.mg', '2019-09-01', 1900.00, '789 Rue des Oiseaux', '0321987654', '1982-04-22'),
    (4, 'Razafy', 'Tiana', 'razafy@recycle-huile.mg', '2022-02-01', 2100.00, '159 Rue des Plantes', '0321654987', '1990-08-10'),
    (5, 'Randriamanana', 'Aina', 'randriamanana@recycle-huile.mg', '2018-11-15', 2300.00, '357 Rue des Fleuves', '0321852963', '1983-12-05'),
    (6, 'Radrinana', 'Aina', 'randriana@recycle-huile.mg', '2018-11-15', 2300.00, '357 Rue des Fleuves', '0321852963', '1983-12-05');


INSERT INTO personne (email,id_personnel, mot_de_passe, etat)
VALUES
    ('rajerison@recycle-huile.mg',1,'password123', 'tsotra'),
    ('rakoto@recycle-huile.mg',2, 'secret456', 'admin'),
    ('ranaivo@recycle-huile.mg',3, 'password789', 'tsotra'),
    ('razafy@recycle-huile.mg',4, 'pass123', 'tsotra'),
    ('randriamanana@recycle-huile.mg',5, 'secret789', 'admin');


INSERT INTO motif (motif) VALUES
    ('Vacances annuelles'),
    ('Conge maladie'),
    ('Conge maternite/paternite'),
    ('Conge sans solde'),
    ('Conge parental'),
    ('Formation professionnelle'),
    ('Conge de mariage'),
    ('Conge de deces'),
    ('Conge sabbatique'),
    ('Conge de recuperation'),
    ('Conge pour déménagement'),
    ('Conge de retraite'),
    ('Conge pour raison personnelle'),
    ('Conge pour raison familiale'),
    ('Conge pour voyage'),
    ('Conge de paternité'),
    ('Conge de maternité'),
    ('Conge pour événement spécial'),
    ('Conge pour démarches administratives'),
    ('Conge pour visite médicale'),
    ('Conge pour convenance personnelle'),
    ('Conge pour education'),
    ('Conge pour voyage d affaires'),
    ('Conge pour volontariat'),
    ('Conge de convalescence'),
    ('Conge pour celebration'),
    ('Conge de bénévolat'),
    ('Conge pour séminaire'),
    ('Conge pour tutorat');
-- Présence
INSERT INTO presence (Date_Presence, Heure_Debut, Heure_Fin, id_Personnel)
VALUES
    ('2024-05-23', '08:30:00', '17:30:00', 1),
    ('2024-05-23', '09:00:00', '18:00:00', 2),
    ('2024-05-23', '08:45:00', '17:45:00', 3),
    ('2024-05-23', '09:15:00', '18:15:00', 4),
    ('2024-05-23', '08:00:00', '17:00:00', 5),
    ('2024-06-23', '09:00:00', '18:00:00', 1),
    ('2024-06-23', '08:30:00', '17:30:00', 2),
    ('2024-06-23', '09:15:00', '18:15:00', 3),
    ('2024-06-23', '08:45:00', '17:45:00', 4),
    ('2024-06-23', '08:00:00', '17:00:00', 5),
    ('2024-07-23', '08:30:00', '17:30:00', 1),
    ('2024-07-23', '09:00:00', '18:00:00', 2),
    ('2024-07-23', '08:45:00', '17:45:00', 3),
    ('2024-07-23', '09:15:00', '18:15:00', 4),
    ('2024-07-23', '08:00:00', '17:00:00', 5);

-- Absence
INSERT INTO Absence (id_Personnel, Date_Absence, id_Motif)
VALUES
    (1, '2024-05-24', 2),
    (3, '2024-06-01', 1),
    (4, '2024-06-15', 3),
    (2, '2024-07-01', 4),
    (5, '2024-07-15', 6);

INSERT INTO conge (id_personnel, date_debut_conge, duree_conge) VALUES
(1, '2024-05-23', 5),
(2, '2024-06-23', 10),
(3, '2024-07-23', 7),
(4, '2024-07-30', 14),
(5, '2024-08-23', 3);




