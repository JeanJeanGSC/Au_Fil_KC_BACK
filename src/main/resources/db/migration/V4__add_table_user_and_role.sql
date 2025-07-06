-- Crée la table pour stocker les informations des utilisateurs
CREATE TABLE utilisateur (
    id VARCHAR(15) PRIMARY KEY,
    prenom VARCHAR(255),
    nom VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(20) NOT NULL
);

CREATE TABLE utilisateur_roles (
   utilisateur_id VARCHAR(15) NOT NULL,
   role VARCHAR(10) NOT NULL,
   CONSTRAINT utilisateur_roles_pkey PRIMARY KEY (utilisateur_id, role),
   CONSTRAINT fk_utilisateur_roles_on_utilisateur FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id)
);