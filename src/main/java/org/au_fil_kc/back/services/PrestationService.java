package org.au_fil_kc.back.services;

import org.au_fil_kc.back.entities.PhotoSrv;
import org.au_fil_kc.back.entities.Prestation;
import org.au_fil_kc.back.repositories.PrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PrestationService {
    private static long idNumber = 1L;
    private final PrestationRepository prestationRepository;
    private final PhotoService photoService;

    @Autowired
    public PrestationService(PrestationRepository prestationRepository, PhotoService photoService) {
        this.prestationRepository = prestationRepository;
        this.photoService = photoService;
    }

    public List<Prestation> getPrestaByType(String typePrefix) {
        return prestationRepository.findAll().stream()
                .filter(service -> service.getId() != null && service.getId().startsWith(typePrefix))
                .collect(Collectors.toList());
    }

    public List<Prestation> getAllPresta() { return prestationRepository.findAll(); }

    public Prestation createService(Prestation service, String type) {
        service.setId(generateNewId(type));
        service.getPhotos().forEach(photoS -> {
            photoS.setId(photoService.generateNewId("S"));
            photoS.setService(service);
        });
        return prestationRepository.save(service);
    }

    public Optional<Prestation> getServicesById(String id) {
        return prestationRepository.findById(id);
    }

    public void deleteServicesById(String id) {
        prestationRepository.deleteById(id);
    }

    public Prestation updateService(Prestation updatedService) {
        return prestationRepository.findById(updatedService.getId())
                .map(existingService -> {
                    existingService.setNom(updatedService.getNom());
                    existingService.setDescription(updatedService.getDescription());
                    // TODO : Ajuster pour autoriser un champ prix vide
                    existingService.setPrix(updatedService.getPrix());

                    // 2. Gérer la liste des photos
                    // Clonons l'ancienne liste pour la manipulation et la comparaison
                    List<PhotoSrv> oldPhotos = existingService.getPhotos();
                    List<PhotoSrv> newPhotos = updatedService.getPhotos() != null ? updatedService.getPhotos() : List.of();

                    // Créer une liste pour les photos à ajouter/mettre à jour sur le service existant
                    // On ne manipule pas oldPhotos directement pour éviter ConcurrentModificationException
                    List<PhotoSrv> photosToPersist = newPhotos.stream()
                            .map(newPhoto -> {
                                // Tenter de trouver la photo existante par ID
                                return oldPhotos.stream()
                                        .filter(oldPhoto -> oldPhoto.getId() != null && oldPhoto.getId().equals(newPhoto.getId()))
                                        .findFirst()
                                        .map(foundPhoto -> {
                                            // Photo existante : mettre à jour ses propriétés
                                            foundPhoto.setUrl(newPhoto.getUrl());
                                            foundPhoto.setOrdre(newPhoto.getOrdre());
                                            foundPhoto.setService(existingService);
                                            return foundPhoto;
                                        })
                                        .orElseGet(() -> {
                                            newPhoto.setId(photoService.generateNewId("P"));
                                            newPhoto.setService(existingService);
                                            return newPhoto;
                                        });
                            })
                            .collect(Collectors.toList());

                    // Mettre à jour la liste des photos du produit existant
                    existingService.getPhotos().clear(); // Supprime toutes les références existantes
                    existingService.getPhotos().addAll(photosToPersist); // Ajoute les nouvelles/mises à jour



                    return prestationRepository.save(existingService);
                })
                .orElseThrow(() -> new RuntimeException("Service " + updatedService.getId() + "  non trouvé !"));
    }

    private synchronized String generateNewId(String type) {
        return type.equals("S") ? "S" + idNumber++ + "RV" : "C" + idNumber++ + "RS";
    }

}
