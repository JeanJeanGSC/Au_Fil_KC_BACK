package org.au_fil_kc.back.repositories;

import org.au_fil_kc.back.entities.Prestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PrestationRepository extends JpaRepository<Prestation, String> {

}
