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
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) { this.serviceRepository = serviceRepository;}

    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    public Services saveServices(Services service) {
        return serviceRepository.save(service);
    }

    public Optional<Services> getServicesById(String id) {
        return serviceRepository.findById(id);
    }

    public void deleteServicesById(String id) {
        serviceRepository.deleteById(id);
    }
}
