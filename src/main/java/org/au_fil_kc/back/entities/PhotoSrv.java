package org.au_fil_kc.back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "photo_service")
public class PhotoSrv extends Photo {
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    @JsonIgnore
    private Prestation service;

    public Prestation getService() {
        return service;
    }
    public void setService(Prestation service) {
        this.service = service;
    }
}
