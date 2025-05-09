package org.au_fil_kc.back.repositories;

import org.au_fil_kc.back.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProduitRepositrory extends JpaRepository<Produit, String> {
//    @Query(value = "SELECT * FROM produit WHERE enSolde = true")
//    List<Produit> findAllOnSale();
}
