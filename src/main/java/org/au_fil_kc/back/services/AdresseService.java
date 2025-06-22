package org.au_fil_kc.back.services;

import org.au_fil_kc.back.entities.Adresse;
import org.au_fil_kc.back.repositories.AdresseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdresseService {
    private final AdresseRepository adresseRepository;

    public AdresseService(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    public List<Adresse> findAll() {
            return adresseRepository.findAll();
    }

    public Optional<Adresse> findById(String id) {
        return adresseRepository.findById(id);
    }

    public Adresse saveAdresse(Adresse adresse) { return adresseRepository.save(adresse); }

    public void deleteAdresseById(String id) { adresseRepository.deleteById(id); }
}
