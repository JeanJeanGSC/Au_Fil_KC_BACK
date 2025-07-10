package org.au_fil_kc.back.services;

import org.au_fil_kc.back.entities.PhotoPrd;
import org.au_fil_kc.back.entities.Produit;
import org.au_fil_kc.back.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public ProduitService(ProduitRepository produitRepository, PhotoService photoService) {
        this.produitRepository = produitRepository;
        this.photoService = photoService;
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit createProduit(Produit produit) {
            produit.setId(gererateNewId());
            produit.getPhotos().forEach(photoP -> {
                photoP.setId(photoService.generateNewId("P"));
                photoP.setProduit(produit);
            });
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
                .map(existingProduit -> {
                    // 1. Mettre à jour les champs simples du produit
                    existingProduit.setNom(updatedProduit.getNom());
                    existingProduit.setTaille(updatedProduit.getTaille());
                    existingProduit.setDescription(updatedProduit.getDescription());
                    existingProduit.setEntretien(updatedProduit.getEntretien());
                    existingProduit.setPrixVente(updatedProduit.getPrixVente());
                    existingProduit.setPrixProd(updatedProduit.getPrixProd());
                    existingProduit.setPrixRabais(updatedProduit.getPrixRabais());
                    existingProduit.setEnSolde(updatedProduit.isEnSolde());
                    existingProduit.setInventaire(updatedProduit.getInventaire());

                    // 2. Gérer la liste des photos
                    // Clonons l'ancienne liste pour la manipulation et la comparaison
                    List<PhotoPrd> oldPhotos = existingProduit.getPhotos();
                    List<PhotoPrd> newPhotos = updatedProduit.getPhotos() != null ? updatedProduit.getPhotos() : List.of();

                    // Créer une liste pour les photos à ajouter/mettre à jour sur le produit existant
                    // On ne manipule pas oldPhotos directement pour éviter ConcurrentModificationException
                    List<PhotoPrd> photosToPersist = newPhotos.stream()
                            .map(newPhoto -> {
                                // Tenter de trouver la photo existante par ID
                                return oldPhotos.stream()
                                        .filter(oldPhoto -> oldPhoto.getId() != null && oldPhoto.getId().equals(newPhoto.getId()))
                                        .findFirst()
                                        .map(foundPhoto -> {
                                            // Photo existante: mettre à jour ses propriétés
                                            foundPhoto.setUrl(newPhoto.getUrl());
                                            foundPhoto.setOrdre(newPhoto.getOrdre());
                                            foundPhoto.setProduit(existingProduit);
                                            return foundPhoto;
                                        })
                                        .orElseGet(() -> {
                                            newPhoto.setId(photoService.generateNewId("P"));
                                            newPhoto.setProduit(existingProduit);
                                            return newPhoto;
                                        });
                            })
                            .collect(Collectors.toList());

                    // Mettre à jour la liste des photos du produit existant
                    existingProduit.getPhotos().clear(); // Supprime toutes les références existantes
                    existingProduit.getPhotos().addAll(photosToPersist); // Ajoute les nouvelles/mises à jour
                    return produitRepository.save(existingProduit);
                })
                .orElseThrow(() -> new RuntimeException("Produit " + updatedProduit.getId() + " non trouvé!"));
    }

    private synchronized String gererateNewId() {
        String id = "P" + idNumber++ + "RD";
        return id;
    }
}
