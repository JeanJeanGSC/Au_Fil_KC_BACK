package org.au_fil_kc.back.controller;

import org.au_fil_kc.back.entities.Services;
import org.au_fil_kc.back.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) { this.serviceService = serviceService; }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Services createService(@RequestBody Services service) {
        return serviceService.createService(service);
    }

    @GetMapping
    public List<Services> getAllServices() {
        return serviceService.getAllServices();
    }


    @GetMapping("/{id}")
    public Optional<Services> getServiceById(@PathVariable String id) {
        return serviceService.getServicesById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/mod{id}")
    public Services updateService(@RequestBody Services service) {
        return serviceService.updateService(service);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/d{id}")
    public void deleteService(@PathVariable String id) {
        serviceService.deleteServicesById(id);
    }

}
