package org.au_fil_kc.back.services;

import org.au_fil_kc.back.entities.Produit;
import org.au_fil_kc.back.repositories.ProduitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProduitService {
    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) { this.produitRepository = produitRepository; }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Optional<Produit> getProduitById(String id) {
        return produitRepository.findById(id);
    }

    public List<Produit> getProduitOnSale() {
        return produitRepository.findAllOnSale();
    }

    public void deleteProduitById(String id) {
        produitRepository.deleteById(id);
    }

//    public Produit updateProduit(String id, Produit updatedProduit) {
//        return produitRepository.findById(id)
//                .map(p -> {
//                    p.setNom(updatedProduit.getNom());
//                    p.setTaille(updatedProduit.getTaille());
//                    p.setDescription(updatedProduit.getDescription());
//                    p.setEntretien(updatedProduit.getEntretien());
//                    p.setPrixVente(updatedProduit.getPrixVente());
//                    p.setPrixProd(updatedProduit.getPrixProd());
//                    p.setPrixRabais(updatedProduit.getPrixRabais());
//                    p.setEnSolde(updatedProduit.isEnSolde());
//                    p.setInventaire(updatedProduit.getInventaire());
//                    return produitRepository.save(p);
//                })
//                .orElseThrow(() -> new RuntimeException("Produit " + id +" non trouvé !"));
//    }

}
