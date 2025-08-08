package sn.isep.edu.diamniadio.produit.Produit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.isep.edu.diamniadio.produit.Produit.entity.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNom(Role.ERole nom);
} 