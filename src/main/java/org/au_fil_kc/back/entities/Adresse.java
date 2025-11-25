package org.au_fil_kc.back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "adresse")
public class Adresse {
    @Id
    private String id;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String ville;

    @Column(name = "code_postal", nullable = false)
    private String codePostal;

    @Column(nullable = false)
    private String pays;

//    @OneToMany(mappedBy = "adresse", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
//    private List<Utilisateur> user; //faire une classe user avec un role ROLE_CLIENT ou ROLE_ADMIN

    public Adresse() { }
    public Adresse(String pays, String codePostal, String ville, String adresse, String id) {
        this.pays = pays;
        this.codePostal = codePostal;
        this.ville = ville;
        this.adresse = adresse;
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) { this.id = id; }

    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getPays() {
        return pays;
    }
    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id='" + id + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }
}
