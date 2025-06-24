-- Création de la table photo_service
CREATE TABLE photo_service (
    id VARCHAR(15) PRIMARY KEY,
    service_id VARCHAR(15) NOT NULL,
    url TEXT NOT NULL,
    ordre INTEGER DEFAULT 0,
    FOREIGN KEY (service_id) REFERENCES service(id) ON DELETE CASCADE
);

-- Insertion dans service
INSERT INTO photo_service (id, service_id, url, ordre)
VALUES
    ('phS1', 'serv1', 'https://picsum.photos/seed/serv1a/600/400', 1),
    ('phS2', 'serv1', 'https://picsum.photos/seed/serv1b/600/400', 2),
    ('phS3', 'serv2', 'https://picsum.photos/seed/serv2a/600/400', 1);