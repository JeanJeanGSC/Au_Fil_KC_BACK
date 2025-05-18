-- V2__insert_dummy_data.sql

-- Insertion dans produit
INSERT INTO produit (id, nom, taille, description, entretien, prix_vente, prix_prod, prix_rabais, inventaire, en_solde)
VALUES
    ('prod1', 'T-shirt Classique', 'M', 'T-shirt en coton bio', 'Laver à 30°C', 25.00, 10.00, 20.00, 100, TRUE),
    ('prod2', 'Sweat à capuche', 'L', 'Sweat confortable en laine', 'Lavage à sec recommandé', 50.00, 20.00, 45.00, 50, FALSE);

-- Insertion dans photo_produit (avec URLs Lorem Picsum)
INSERT INTO photo_produit (id, produit_id, url, ordre)
VALUES
    ('photo1', 'prod1', 'https://picsum.photos/seed/prod1a/600/400', 1),
    ('photo2', 'prod1', 'https://picsum.photos/seed/prod1b/600/400', 2),
    ('photo3', 'prod2', 'https://picsum.photos/seed/prod2a/600/400', 1);

-- Insertion dans service
INSERT INTO service (id, nom, description, prix)
VALUES
    ('serv1', 'Repassage', 'Repassage professionnel de vos vêtements', 5.00),
    ('serv2', 'Livraison express', 'Livraison en 24h', 10.00);

-- Insertion dans adresse
INSERT INTO adresse (id, adresse, ville, code_postal, pays)
VALUES
    ('ADR000001', '444 route de Saint Nexans', 'Conne-de-Labarde', '24560', 'France'),
    ('ADR000002', '491 boulevard Saint-Joseph', 'Paris', '93012', 'France');
