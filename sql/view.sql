----Production-------------------------
CREATE OR REPLACE VIEW view_production AS
SELECT  
        production.id,
        produit.libelle,
        production.date_production,
        production.quantite
FROM production 
Join produit  on production.id_produit = produit.id_produit;
---------------------------------------
CREATE OR REPLACE VIEW view_sum_quantite_production_with_date AS
SELECT 
    production.id,
    produit.libelle as libelle,
    production.date_production,
    SUM(production.quantite) AS quantite
FROM production 
JOIN produit ON production.id_produit = produit.id_produit
GROUP BY production.id,produit.libelle,  production.date_production
ORDER BY production.date_production ASC;
--benefice -------------------------------------------------------------------------------------------
CREATE OR REPLACE VIEW v_total_vente_du_mois as
SELECT 
    EXTRACT(YEAR FROM date_vente) as annee , 
    EXTRACT (MONTH FROM date_vente) as mois ,SUM(total) as total_vente 
FROM vente
GROUP BY 
    EXTRACT(YEAR FROM date_vente) , EXTRACT (MONTH FROM date_vente);
---------------------------------------------------------------------------------------------------- 
CREATE OR REPLACE VIEW v_total_depense_du_mois as 
SELECT 
    EXTRACT(YEAR FROM date_depense) as annee , 
    EXTRACT (MONTH FROM date_depense) as mois ,SUM(montant) as total_depense 
FROM depense 
GROUP BY 
    EXTRACT(YEAR FROM date_depense) , EXTRACT (MONTH FROM date_depense);
------------------------------------------------------------------------------------------
CREATE OR REPLACE VIEW v_benefice as 
SELECT 
    v_total_depense_du_mois.annee , v_total_depense_du_mois.mois,
    v_total_vente_du_mois.total_vente - v_total_depense_du_mois.total_depense as benefice 
FROM v_total_depense_du_mois 
JOIN v_total_vente_du_mois 
on v_total_depense_du_mois.annee = v_total_vente_du_mois.annee 
AND v_total_depense_du_mois.mois = v_total_vente_du_mois.mois;
----- stock produit--------------------------------------------------------------------------------------
CREATE OR REPLACE VIEW v_stock_produit as 
SELECT produit.libelle as nom, SUM(production.quantite) - sum(vente.quantite) as stock 
FROM produit 
JOIN production 
on produit.id_produit = production.id_produit 
JOIN vente 
on production.id_produit = vente.id_produit 
GROUP BY nom;
-- conge--------------------------------------------------------------------------------------------------
CREATE OR REPLACE VIEW v_conge as 
(SELECT id_conge,conge.id_personnel as id_personnel,personnel.nom as nom_personnel,date_debut_conge,duree_conge,(date_debut_conge + duree_conge) as date_fin_conge 
FROM conge 
left JOIN personnel 
on personnel.id_personnel=conge.id_personnel);
--Absence en une date------------------------------------------------------------------------------------------
CREATE OR REPLACE VIEW v_presence_journaliere AS
    SELECT personnel.id_Personnel FROM personnel 
    JOIN presence ON personnel.id_Personnel = presence.id_Personnel
    WHERE Date_Presence = '2024-05-25' AND personnel.date_embauche<'2024-05-25'
        UNION ALL
    SELECT personnel.id_Personnel FROM personnel JOIN Absence ON personnel.id_Personnel = Absence.id_Personnel
    WHERE Date_Absence = '2024-05-25' AND personnel.date_embauche<'2024-05-25'
        UNION ALL
    SELECT id_personnel FROM v_conge where date_debut_conge >= '2024-05-25' AND date_fin_conge <= '2024-05-25';
-----------------------------------------------------------------------------------------------------------
CREATE OR REPLACE VIEW v_absence_journaliere as
    SELECT 
        personnel.id_Personnel,personnel.nom, P.poste 
    FROM 
        personnel JOIN Poste P on personnel.id_Poste = P.id_Poste Left JOIN v_presence_journaliere on v_presence_journaliere.id_Personnel=personnel.id_Personnel
    WHERE  
        v_presence_journaliere.id_Personnel is  null;
----------------------------------------------------------

--Retard en une journée

CREATE OR REPLACE VIEW v_retard AS
    SELECT 
        personnel.id_Personnel,
        personnel.nom, 
        P.poste, 
        presence.Date_Presence, 
        presence.Heure_Debut
    FROM 
        personnel
    JOIN 
        Poste P ON personnel.id_Poste = P.id_Poste
    JOIN 
        presence ON personnel.id_Personnel = presence.id_Personnel
    WHERE 
        presence.Date_Presence = CURRENT_DATE 
        AND presence.Heure_Debut > '09:00:00'; -- Assuming 9 AM is the start time
-----------------------------------------------------------stock
CREATE VIEW stock_disponible AS
SELECT 
    p.id_produit,
    p.libelle,
    COALESCE(prod.total_produit, 0) - COALESCE(vend.total_vendu, 0) AS stock_disponible
FROM 
    produit p
LEFT JOIN 
    (SELECT id_produit, SUM(quantite) AS total_produit
     FROM production
     GROUP BY id_produit) prod
ON 
    p.id_produit = prod.id_produit
LEFT JOIN 
    (SELECT id_produit, SUM(quantite) AS total_vendu
     FROM vente
     GROUP BY id_produit) vend
ON 
    p.id_produit = vend.id_produit;


----statistique de vente des produit-------
CREATE OR REPLACE VIEW v_totat_production_deux_mois AS
SELECT 
    produit.libelle as libelle,
    EXTRACT(YEAR FROM date_production) as annee , 
    EXTRACT (MONTH FROM date_production) as mois ,
    SUM(production.quantite) AS quantite
FROM production 
JOIN produit ON production.id_produit = produit.id_produit
GROUP BY produit.libelle, EXTRACT(YEAR FROM date_production), 
    EXTRACT (MONTH FROM date_production);