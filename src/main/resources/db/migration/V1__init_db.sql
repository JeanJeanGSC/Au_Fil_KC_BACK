-- Création de la table produit
CREATE TABLE produit (
    id VARCHAR(15) PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    taille VARCHAR(15) NOT NULL DEFAULT 'Universel',
    description TEXT NOT NULL,
    entretien TEXT NOT NULL,
    prix_vente NUMERIC(8,2) NOT NULL,
    prix_prod NUMERIC(8,2),
    prix_rabais NUMERIC(8,2),
    inventaire INTEGER,
    en_solde BOOLEAN DEFAULT FALSE
);

-- Création de la table photo_produit (1 produit -> plusieurs photos)
CREATE TABLE photo_produit (
    id VARCHAR(15) PRIMARY KEY,
    produit_id VARCHAR(15) NOT NULL,
    url TEXT NOT NULL,
    ordre INTEGER DEFAULT 0,
    FOREIGN KEY (produit_id) REFERENCES produit(id) ON DELETE CASCADE
);

-- Création de la table service
CREATE TABLE service (
    id VARCHAR(15) PRIMARY KEY,
    nom VARCHAR(255),
    description TEXT,
    prix NUMERIC(8,2)
);

-- Création de la table adresse
CREATE TABLE adresse (
    id VARCHAR(15) PRIMARY KEY,
    adresse VARCHAR(150) NOT NULL,
    ville VARCHAR(150) NOT NULL,
    code_postal VARCHAR(10) NOT NULL,
    pays VARCHAR(100) DEFAULT 'France'
);
