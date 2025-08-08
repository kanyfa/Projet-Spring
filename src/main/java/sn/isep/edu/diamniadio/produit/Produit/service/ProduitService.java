package sn.isep.edu.diamniadio.produit.Produit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.isep.edu.diamniadio.produit.Produit.entity.Produit;
import sn.isep.edu.diamniadio.produit.Produit.repository.ProduitRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProduitService {
    
    private final ProduitRepository produitRepository;
    
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }
    
    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }
    
    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }
    

    
    public Optional<Produit> updateProduit(Long id, Produit produitDetails) {
        return produitRepository.findById(id)
                .map(existingProduit -> {
                    existingProduit.setNom(produitDetails.getNom());
                    existingProduit.setDescription(produitDetails.getDescription());
                    existingProduit.setPrixUnitaire(produitDetails.getPrixUnitaire());
                    return produitRepository.save(existingProduit);
                });
    }
    
    public boolean deleteProduit(Long id) {
        if (produitRepository.existsById(id)) {
            produitRepository.deleteById(id);
            return true;
        }
        return false;
    }
} 