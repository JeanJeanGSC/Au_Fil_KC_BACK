package org.au_fil_kc.back.services;

import org.au_fil_kc.back.entities.PhotoProduit;
import org.au_fil_kc.back.services.PhotoService;
import org.au_fil_kc.back.entities.Produit;
import org.au_fil_kc.back.repositories.ProduitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProduitService {
    private static long idNumber = 1L;
    private final ProduitRepository produitRepository;
    private final PhotoService photoService;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
        this.photoService = new PhotoService();
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit createProduit(Produit produit) {
        produit.getPhotos().stream().map(photoProduit -> {
            PhotoProduit photo = new PhotoProduit();
            photo.setId(photoService.generateNewId());
            photo.setUrl(photoProduit.getUrl());
            photo.setProduit(produit);
            return photo;
        }).collect(Collectors.toList());
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

    public Produit updateProduit(Produit updatedProduit) {
        return produitRepository.findById(updatedProduit.getId())
                .map(p -> {
                    p.setNom(updatedProduit.getNom());
                    p.setTaille(updatedProduit.getTaille());
                    p.setDescription(updatedProduit.getDescription());
                    p.setEntretien(updatedProduit.getEntretien());
                    p.setPrixVente(updatedProduit.getPrixVente());
                    p.setPrixProd(updatedProduit.getPrixProd());
                    p.setPrixRabais(updatedProduit.getPrixRabais());
                    p.setEnSolde(updatedProduit.isEnSolde());
                    p.setInventaire(updatedProduit.getInventaire());
                    return produitRepository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Produit non trouvé !"));
    }

    public synchronized String gererateNewId() {
        String id = "P" + idNumber++ + "RD";
        return id;
    }
}
