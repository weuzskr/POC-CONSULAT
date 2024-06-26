package com.starterkit.controller;

import com.starterkit.model.Consulat;
import com.starterkit.model.Region;
import com.starterkit.repository.ConsulatRepository;
import com.starterkit.service.ConsulatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/consulats")
public class ConsulatController {

    @Autowired
    private ConsulatService consulatService;

    private final ConsulatRepository consulatRepository;

    @Autowired
    public ConsulatController(ConsulatRepository consulatRepository) {
        this.consulatRepository = consulatRepository;
    }

    @GetMapping("/tous")
    public ResponseEntity<List<Consulat>> getAllConsulats() {
        List<Consulat> consulats = consulatRepository.findAll();
        return ResponseEntity.ok(consulats);
    }
   /* @GetMapping("/departement/{departementId}")
    public ResponseEntity<List<Consulat>> getConsulatsByDepartement(@PathVariable Long departementId) {
        List<Consulat> consulats = consulatService.getConsulatsByDepartement(departementId);
        return ResponseEntity.ok().body(consulats);
    }*/
    // Autres endpoints pour CRUD des consulats peuvent être ajoutés ici

    @GetMapping("/regions")
    public ResponseEntity<Map<Region, List<Consulat>>> getAllConsulatsByRegion() {
        Map<Region, List<Consulat>> consulatsByRegion = consulatService.getAllConsulatsByRegion();
        return ResponseEntity.ok().body(consulatsByRegion);
    }
}
