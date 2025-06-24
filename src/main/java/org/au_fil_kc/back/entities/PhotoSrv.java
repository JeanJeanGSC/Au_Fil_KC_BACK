package org.au_fil_kc.back.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "photo_services")
public class PhotoServ extends Photo {
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Services service;

    public Services getService() {
        return service;
    }
    public void setService(Services service) {
        this.service = service;
    }
}
