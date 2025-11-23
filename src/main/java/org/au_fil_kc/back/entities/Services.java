package org.au_fil_kc.back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service")
public class Services {
    @Id
    private String id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String description;

    // TODO: prix inutile a supprimer ?
    @Column(nullable = false)
    private double prix; //Prix a l'heure ou pour le service entier ?
    
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PhotoSrv> photos = new ArrayList<>();

    /// ---------------------- CONSTRUCTEUR ------------------------------------
    public Services() { }
    public Services(String id, String nom, String description, double prix, List<PhotoSrv> photos) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.photos = photos;
    }

    /// ---------------------- GETTER & SETTER ------------------------------------
    public String getId() {
        return id;
    }
    public void setId(String id) { this.id = id; }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }

    public List<PhotoSrv> getPhotos() { return photos; }
    public void setPhotos(List<PhotoSrv> photos) {
        this.photos = photos;
    }

    /// ---------------------- TO STRING ------------------------------------
    @Override
    public String toString() {
        return "Service{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }



    // TODO est-ce qu'on garde add et remove ?
    // Méthodes utilitaires pour une gestion propre de la bidirectionnalité (recommandé)
    public void addPhoto(PhotoSrv photo) {
        if (this.photos == null) {
            this.photos = new ArrayList<>();
        }
        this.photos.add(photo);
        photo.setService(this);
    }

    public void removePhoto(PhotoSrv photo) {
        if (this.photos != null) {
            this.photos.remove(photo);
            photo.setService(null);
        }
    }
}
