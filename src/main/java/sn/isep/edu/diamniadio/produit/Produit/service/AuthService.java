package sn.isep.edu.diamniadio.produit.Produit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import sn.isep.edu.diamniadio.produit.Produit.dto.AuthRequest;
import sn.isep.edu.diamniadio.produit.Produit.dto.AuthResponse;
import sn.isep.edu.diamniadio.produit.Produit.entity.Utilisateur;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UtilisateurService utilisateurService;
    
    public AuthResponse authenticate(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(utilisateur);
        
        return new AuthResponse(
                jwtToken,
                "Bearer",
                utilisateur.getUsername(),
                utilisateur.getNom(),
                utilisateur.getPrenom()
        );
    }
} 