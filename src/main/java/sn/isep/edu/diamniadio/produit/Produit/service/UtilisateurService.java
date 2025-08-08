package sn.isep.edu.diamniadio.produit.Produit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.isep.edu.diamniadio.produit.Produit.entity.Role;
import sn.isep.edu.diamniadio.produit.Produit.entity.Utilisateur;
import sn.isep.edu.diamniadio.produit.Produit.repository.RoleRepository;
import sn.isep.edu.diamniadio.produit.Produit.repository.UtilisateurRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateurService implements UserDetailsService {
    
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé: " + username));
    }
    
    public void initializeUsers() {
        // Créer les rôles s'ils n'existent pas
        Role roleClient = roleRepository.findByNom(Role.ERole.CLIENT)
                .orElseGet(() -> roleRepository.save(new Role(null, Role.ERole.CLIENT)));
        
        Role roleManager = roleRepository.findByNom(Role.ERole.MANAGER)
                .orElseGet(() -> roleRepository.save(new Role(null, Role.ERole.MANAGER)));
        
        // Créer les utilisateurs s'ils n'existent pas
        if (!utilisateurRepository.existsByUsername("client1")) {
            createUser("client1", "password", "Dupont", "Jean", "client1@email.com", Arrays.asList(roleClient));
        }
        if (!utilisateurRepository.existsByUsername("client2")) {
            createUser("client2", "password", "Martin", "Marie", "client2@email.com", Arrays.asList(roleClient));
        }
        if (!utilisateurRepository.existsByUsername("client3")) {
            createUser("client3", "password", "Bernard", "Pierre", "client3@email.com", Arrays.asList(roleClient));
        }
        if (!utilisateurRepository.existsByUsername("client4")) {
            createUser("client4", "password", "Petit", "Sophie", "client4@email.com", Arrays.asList(roleClient));
        }
        if (!utilisateurRepository.existsByUsername("manager")) {
            createUser("manager", "password", "Durand", "Paul", "manager@email.com", Arrays.asList(roleManager));
        }
    }
    
    private void createUser(String username, String password, String nom, String prenom, String email, List<Role> roles) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(username);
        utilisateur.setPassword(passwordEncoder.encode(password));
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setRoles(roles);
        utilisateurRepository.save(utilisateur);
    }
    

} 