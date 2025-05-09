package org.au_fil_kc.back.services;

import org.au_fil_kc.back.entities.Produit;
import org.au_fil_kc.back.repositories.ProduitRepositrory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProduitService {
    private final ProduitRepositrory produitRepositrory;
    public ProduitService(ProduitRepositrory produitRepositrory) {
        this.produitRepositrory = produitRepositrory;
    }

    public List<Produit> getAllProduits() {
        return produitRepositrory.findAll();
    }

    public Produit getProduitById(String id) {
        return produitRepositrory.getById(id);
    }

//    public List<Produit> getProsuitOnSale(Produit produit) {
//        return produitRepositrory.findAllOnSale();
//    }

}
