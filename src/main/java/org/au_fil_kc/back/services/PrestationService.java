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
        if (service.getId() != null) {
            service.getPhotos().forEach(photoS -> {
                photoS.setId(photoService.generateNewId(type));
                photoS.setService(service);
            });
        }
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
                    existingService.setSousTitre(updatedService.getSousTitre());
                    existingService.setDescription(updatedService.getDescription());
                    existingService.setPrix(updatedService.getPrix());

                    // Copy de l'ancienne liste pour la manipulation et la comparaison
                    List<PhotoSrv> oldPhotos = existingService.getPhotos();
                    List<PhotoSrv> newPhotos = updatedService.getPhotos() != null ? updatedService.getPhotos() : List.of();

                    // Créer une liste pour les photos à ajouter/mettre à jour sur le service existant
                    List<PhotoSrv> photosToPersist = newPhotos.stream()
                            .map(newPhoto -> {
                                return oldPhotos.stream()
                                        .filter(oldPhoto -> oldPhoto.getId() != null && oldPhoto.getId().equals(newPhoto.getId()))
                                        .findFirst()
                                        .map(foundPhoto -> {
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
                            .toList();

                    // Mettre à jour la liste des photos du produit existant
                    existingService.getPhotos().clear();
                    existingService.getPhotos().addAll(photosToPersist);


                    return prestationRepository.save(existingService);
                })
                .orElseThrow(() -> new RuntimeException("Service " + updatedService.getId() + "  non trouvé !"));
    }

    private synchronized String generateNewId(String type) {
        String prefix = type.equals("S") ? "S" : "C";
        String suffix = type.equals("S") ? "RV" : "RS";

        // Récupère tous les IDs existants de ce type
        List<String> existingIds = prestationRepository.findAll().stream()
                .map(Prestation::getId)
                .filter(id -> id != null && id.startsWith(prefix) && id.endsWith(suffix))
                .toList();

        // Trouve le chiffre le plus haut
        long maxId = existingIds.stream()
                .mapToLong(id -> {
                    try {
                        // Extraction du chiffre entre le préfixe et le suffixe
                        String numberPart = id.substring(prefix.length(), id.length() - suffix.length());
                        return Long.parseLong(numberPart);
                    } catch (NumberFormatException e) {
                        return 0L;
                    }
                })
                .max()
                .orElse(0L); // Si aucun service, on commence à 0

        return prefix + (maxId + 1) + suffix;
    }

}
