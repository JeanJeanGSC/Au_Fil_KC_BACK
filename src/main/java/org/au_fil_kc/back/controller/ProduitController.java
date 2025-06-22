package org.au_fil_kc.back.controller;

import org.au_fil_kc.back.entities.Produit;
import org.au_fil_kc.back.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produits")
public class ProduitController {
    private final ProduitService produitService;

    @Autowired
    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
        return produitService.saveProduit(produit);
    }

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/solde")
    public List<Produit> getProduitOnSale() {
        return produitService.getProduitOnSale();
    }


    @GetMapping("/{id}")
    public Optional<Produit> getProduitById(@PathVariable String id) {
        return produitService.getProduitById(id);
    }

//    @PutMapping("/{id}")
//    public Produit updateProduit(@PathVariable String id, @RequestBody Produit produit) {
//        return produitService.updateProduit(id, produit);
//    }

    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable String id) {
        produitService.deleteProduitById(id);
    }
}
