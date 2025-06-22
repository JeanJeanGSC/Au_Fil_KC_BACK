package org.au_fil_kc.back.controller;

import org.au_fil_kc.back.entities.Adresse;
import org.au_fil_kc.back.services.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Adresse createAdresse(@RequestBody Adresse adresse) { return adresseService.saveAdresse(adresse); }

    @GetMapping
    public List<Adresse> getAllAdresses() { return adresseService.findAll(); }

    @GetMapping("/{id}")
    public Optional<Adresse> getAdresseById(@PathVariable String id) { return adresseService.findById(id); }

    @DeleteMapping("/{id}")
    public void deleteAdresse(@PathVariable String id) { adresseService.deleteAdresseById(id); }
}
