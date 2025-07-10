package org.au_fil_kc.back.services;

import org.au_fil_kc.back.entities.PhotoSrv;
import org.au_fil_kc.back.entities.Services;
import org.au_fil_kc.back.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceService {
    private static long idNumber = 1L;
    private final ServiceRepository serviceRepository;
    private final PhotoService photoService;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository , PhotoService photoService) {
        this.serviceRepository = serviceRepository;
        this.photoService = photoService;
    }

    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    public Services createService(Services service) {
        service.setId(generateNewId());
        service.getPhotos().forEach(photoS -> {
            photoS.setId(photoService.generateNewId("S"));
            photoS.setService(service);
        });
        return serviceRepository.save(service);
    }

    public Optional<Services> getServicesById(String id) {
        return serviceRepository.findById(id);
    }

    public void deleteServicesById(String id) {
        serviceRepository.deleteById(id);
    }

    public Services updateService(Services updatedService) {
        return serviceRepository.findById(updatedService.getId())
                .map(existingService -> {
                    existingService.setNom(updatedService.getNom());
                    existingService.setDescription(updatedService.getDescription());
                    existingService.setPrix(updatedService.getPrix());

                    // 2. Gérer la liste des photos
                    // Clonons l'ancienne liste pour la manipulation et la comparaison
                    List<PhotoSrv> oldPhotos = existingService.getPhotos();
                    List<PhotoSrv> newPhotos = updatedService.getPhotos() != null ? updatedService.getPhotos() : List.of();

                    // Créer une liste pour les photos à ajouter/mettre à jour sur le produit existant
                    // On ne manipule pas oldPhotos directement pour éviter ConcurrentModificationException
                    List<PhotoSrv> photosToPersist = newPhotos.stream()
                            .map(newPhoto -> {
                                // Tenter de trouver la photo existante par ID
                                return oldPhotos.stream()
                                        .filter(oldPhoto -> oldPhoto.getId() != null && oldPhoto.getId().equals(newPhoto.getId()))
                                        .findFirst()
                                        .map(foundPhoto -> {
                                            // Photo existante: mettre à jour ses propriétés
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



                    return serviceRepository.save(existingService);
                })
                .orElseThrow(() -> new RuntimeException("Service " + updatedService.getId() + "  non trouvé !"));
    }

    private synchronized String generateNewId() {
        return "S" + idNumber++ + "RV";
    }

}
