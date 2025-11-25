-- 1. Supprimer les données existantes (les dummy data serv1, serv2...)
DELETE FROM service;

-- 2. Ajouter la colonne pour les phrases d'accroche
ALTER TABLE service ADD COLUMN sous_titre TEXT;