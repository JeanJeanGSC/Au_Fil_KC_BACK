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
    private static long idNumber = 1L;
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

    public Adresse createAdresse(Adresse adresse) {
        adresse.setId(gererateNewId());
        return adresseRepository.save(adresse);
    }

    public Adresse updateAdresse(String id, Adresse updatedAdresse) {
        return adresseRepository.findById(id)
                .map(existingAdresse -> {
                    existingAdresse.setAdresse(updatedAdresse.getAdresse());
                    existingAdresse.setCodePostal(updatedAdresse.getCodePostal());
                    existingAdresse.setVille(updatedAdresse.getVille());
                    existingAdresse.setPays(updatedAdresse.getPays());
                    return adresseRepository.save(existingAdresse);
                })
                .orElseThrow(() -> new RuntimeException("Adresse " + updatedAdresse.getId() + " non trouvé!"));
    }

    public void deleteAdresseById(String id) { adresseRepository.deleteById(id); }

    private synchronized String gererateNewId() {
        return "A" + idNumber++ + "DR";
    }
}
