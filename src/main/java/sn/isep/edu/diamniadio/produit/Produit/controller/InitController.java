package sn.isep.edu.diamniadio.produit.Produit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.isep.edu.diamniadio.produit.Produit.service.UtilisateurService;

@RestController
@RequestMapping("/api/init")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:52365"})
public class InitController {
    
    private final UtilisateurService utilisateurService;
    
    @PostMapping("/users")
    public ResponseEntity<String> initializeUsers() {
        try {
            utilisateurService.initializeUsers();
            return ResponseEntity.ok("Utilisateurs initialisés avec succès!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de l'initialisation: " + e.getMessage());
        }
    }
} 