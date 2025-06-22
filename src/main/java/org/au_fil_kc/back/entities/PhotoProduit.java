package org.au_fil_kc.back.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "photo_produit")
public class PhotoProduit {
    @Id
    private String id;

    @Column(nullable = false)
    private String url;

    private int ordre;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public int getOrdre() {
        return ordre;
    }
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public Produit getProduit() {
        return produit;
    }
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
