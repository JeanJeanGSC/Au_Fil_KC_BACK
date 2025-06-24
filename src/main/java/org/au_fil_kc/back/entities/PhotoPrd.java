package org.au_fil_kc.back.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "photo_produit")
public class PhotoProd extends Photo {
    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    public Produit getProduit() {
        return produit;
    }
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
