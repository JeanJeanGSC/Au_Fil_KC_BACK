package org.au_fil_kc.back.controller;

import org.au_fil_kc.back.entities.Services;
import org.au_fil_kc.back.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) { this.serviceService = serviceService; }


    @PostMapping
    public Services createService(@RequestBody Services service) {
        return serviceService.saveServices(service);
    }

    @GetMapping
    public List<Services> getAllServices() {
        return serviceService.getAllServices();
    }


    @GetMapping("/{id}")
    public Optional<Services> getServiceById(@PathVariable String id) {
        return serviceService.getServicesById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable String id) {
        serviceService.deleteServicesById(id);
    }

}
