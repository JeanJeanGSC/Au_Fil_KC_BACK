package org.au_fil_kc.back.controller;

import org.au_fil_kc.back.entities.Adresse;
import org.au_fil_kc.back.services.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adresses")
public class AdresseController {
    private final AdresseService adresseService;

    @Autowired
    public AdresseController(AdresseService adresseService) { this.adresseService = adresseService; }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Adresse createAdresse(@RequestBody Adresse adresse) { return adresseService.createAdresse(adresse); }

    @GetMapping
    public List<Adresse> getAllAdresses() { return adresseService.findAll(); }

    @GetMapping("/{id}")
    public Optional<Adresse> getAdresseById(@PathVariable String id) { return adresseService.findById(id); }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/mod{id}")
    public Adresse updateAdresse(@PathVariable String id, @RequestBody Adresse adresse) {
        return adresseService.updateAdresse(id, adresse);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/d{id}")
    public void deleteAdresse(@PathVariable String id) { adresseService.deleteAdresseById(id); }
}
