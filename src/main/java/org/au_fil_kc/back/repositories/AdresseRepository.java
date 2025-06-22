package org.au_fil_kc.back.repositories;

import org.au_fil_kc.back.entities.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdresseRepository extends JpaRepository<Adresse, String> {
}
