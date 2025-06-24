package org.au_fil_kc.back.services;

import org.au_fil_kc.back.entities.Services;
import org.au_fil_kc.back.repositories.ServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceService {
    private static long idNumber = 1L;
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) { this.serviceRepository = serviceRepository;}

    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    public Services createServices(Services service) {
        return serviceRepository.save(service);
    }

    public Optional<Services> getServicesById(String id) {
        return serviceRepository.findById(id);
    }

    public void deleteServicesById(String id) {
        serviceRepository.deleteById(id);
    }

    public Services updateProduit(String id, Services updatedServices) {
        return serviceRepository.findById(updatedServices.getId())
                .map(s -> {
                    s.setNom(updatedServices.getNom());
                    s.setDescription(updatedServices.getDescription());
                    s.setPrix(updatedServices.getPrix());
                    return serviceRepository.save(s);
                })
                .orElseThrow(() -> new RuntimeException("Service non trouvé !"));
    }

    public synchronized String generateNewId() {
        String id = "S" + idNumber++ + "RV";
        return id;
    }

}
