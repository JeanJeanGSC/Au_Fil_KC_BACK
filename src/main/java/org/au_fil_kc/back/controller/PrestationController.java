package org.au_fil_kc.back.controller;

import org.au_fil_kc.back.entities.Prestation;
import org.au_fil_kc.back.services.PrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestations")
public class PrestationController {
    private final PrestationService prestationService;

    @Autowired
    public PrestationController(PrestationService prestationService) { this.prestationService = prestationService; }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/services")
    public Prestation createService(@RequestBody Prestation service) {
        return prestationService.createService(service, "S");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/cours")
    public Prestation createCour(@RequestBody Prestation cour) {
        return prestationService.createService(cour, "C");
    }

    @GetMapping
    public List<Prestation> getPrestaByType(@RequestParam String type) {
        if (type != null && (type.equals("S") || type.equals("C"))) {
            return prestationService.getPrestaByType(type);
        }
        return prestationService.getAllPresta();
    }


    @GetMapping("/{id}")
    public Optional<Prestation> getServiceById(@PathVariable String id) {
        return prestationService.getServicesById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/mod{id}")
    public Prestation updateService(@RequestBody Prestation service) {
        return prestationService.updateService(service);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/d{id}")
    public void deleteService(@PathVariable String id) {
        prestationService.deleteServicesById(id);
    }

}
