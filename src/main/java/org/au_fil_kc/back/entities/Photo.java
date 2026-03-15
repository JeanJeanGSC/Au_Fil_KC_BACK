package org.au_fil_kc.back.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Photo {
    @Id
    private String id;

    @Column(nullable = false)
    private String url;

    private String description;

    private int ordre;

    /// ---------------------- GETTER & SETTER ------------------------------------
    public String getId() {
        return id;
    }
    public void setId(String id) { this.id = id; }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() { return description; }
    public void setDescription(String desc) {this.description = desc;}

    public int getOrdre() {
        return ordre;
    }
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }
}
