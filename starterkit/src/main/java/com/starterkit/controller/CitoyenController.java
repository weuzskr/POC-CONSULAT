package com.starterkit.controller;


import com.starterkit.model.Citoyen;
import com.starterkit.model.Admin;
import com.starterkit.repository.CitoyenRepository;
import com.starterkit.service.CitoyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/citoyens")
public class CitoyenController {

    private final CitoyenService citoyenService;

    @Autowired
    public CitoyenController(CitoyenService citoyenService) {
        this.citoyenService = citoyenService;
    }
    @Autowired
    private CitoyenRepository citoyenRepository;

    @GetMapping("/par-id/{id}")
    public ResponseEntity<?> getCitoyenById(@PathVariable("id") Long id) {
        Optional<Citoyen> optionalCitoyen = citoyenService.getCitoyenById(id);
        if (optionalCitoyen.isEmpty()) {
            return ResponseEntity.notFound().build(); // Renvoie 404 si le citoyen n'est pas trouvé
        }
        return ResponseEntity.ok(optionalCitoyen.get());
    }

    @GetMapping("/tous")
    public ResponseEntity<List<Citoyen>> getAllCitoyens() {
        List<Citoyen> citoyens = citoyenRepository.findAll();
        return ResponseEntity.ok(citoyens);
    }
    // Endpoint pour rechercher un citoyen par son nom
    @GetMapping("/rechercher")
    public ResponseEntity<List<Citoyen>> searchCitoyenByName(@RequestParam String nom) {
        List<Citoyen> citoyens = citoyenRepository.findByNomContainingIgnoreCase(nom);
        return ResponseEntity.ok(citoyens);
    }

    @PostMapping("/enroler")
    public ResponseEntity<?> enregistrerCitoyen(@RequestBody Citoyen citoyen) {
        try {
            Citoyen savedCitoyen = citoyenService.saveCitoyenWithRelations(citoyen);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCitoyen);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement du citoyen : " + e.getMessage());
        }
    }
    @GetMapping("/par-admin")
    public ResponseEntity<List<Citoyen>> getCitoyensByAdmin(@AuthenticationPrincipal Admin admin) {
        if (admin == null || admin.getConsulat() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Ou une autre réponse d'erreur appropriée
        }

        Long consulatId = admin.getConsulat().getId();
        List<Citoyen> citoyens = citoyenRepository.findByConsulatId(consulatId);
        return ResponseEntity.ok(citoyens);
    }


}
