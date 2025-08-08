package sn.isep.edu.diamniadio.produit.Produit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isep.edu.diamniadio.produit.Produit.dto.AuthRequest;
import sn.isep.edu.diamniadio.produit.Produit.dto.AuthResponse;
import sn.isep.edu.diamniadio.produit.Produit.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentification", description = "API pour l'authentification")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:52365"})
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    @Operation(summary = "Se connecter", description = "Authentifie un utilisateur et retourne un token JWT")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        AuthResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }
    

} 