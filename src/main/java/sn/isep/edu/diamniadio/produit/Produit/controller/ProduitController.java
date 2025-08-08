package sn.isep.edu.diamniadio.produit.Produit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sn.isep.edu.diamniadio.produit.Produit.dto.ProduitDto;
import sn.isep.edu.diamniadio.produit.Produit.entity.Produit;
import sn.isep.edu.diamniadio.produit.Produit.service.ProduitService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
@Tag(name = "Produits", description = "API pour la gestion des produits")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:52365"})
public class ProduitController {
    
    private final ProduitService produitService;
    
    @GetMapping
    @Operation(summary = "Récupérer tous les produits", description = "Retourne la liste de tous les produits")
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        return ResponseEntity.ok(produits);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un produit par ID", description = "Retourne un produit spécifique par son ID")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        Optional<Produit> produit = produitService.getProduitById(id);
        return produit.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @Operation(summary = "Créer un nouveau produit", description = "Crée un nouveau produit")
    public ResponseEntity<?> createProduit(@Valid @RequestBody ProduitDto produitDto) {
        try {
            Produit produit = new Produit();
            produit.setNom(produitDto.getNom());
            produit.setDescription(produitDto.getDescription());
            produit.setPrixUnitaire(produitDto.getPrixUnitaire());

            
            Produit createdProduit = produitService.createProduit(produit);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduit);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la création du produit: " + e.getMessage());
        }
    }
    

    
    @PutMapping("/{id}")
    @Operation(summary = "Modifier un produit", description = "Modifie un produit existant")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produitDetails) {
        Optional<Produit> updatedProduit = produitService.updateProduit(id, produitDetails);
        return updatedProduit.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un produit", description = "Supprime un produit par son ID")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        boolean deleted = produitService.deleteProduit(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    

    

} 