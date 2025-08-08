package sn.isep.edu.diamniadio.produit.Produit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.isep.edu.diamniadio.produit.Produit.entity.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
} 