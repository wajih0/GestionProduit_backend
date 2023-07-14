package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Produit;
import com.example.demo.repository.ProduitRepository;
import com.mysql.cj.xdevapi.ExprParser;

@Service
@Primary
public class ProduitService implements IProduitService {
	
	@Autowired
	private ProduitRepository produitRepository ;
	

	@Override
	public List<Produit> getProduits() {
      return produitRepository.findAll();
	
	}

	@Override
	public void addProduit(Produit produit) {
		 produitRepository.save(produit);		
		
	}


	


	@Override
	public void deleteProduit(Long id) {
		Produit produit = new Produit();
		produit.setId(id);
		produitRepository.delete(produit);
		}




	@Override
	public Produit updateProduit(Long id, Produit produit) {
		 Optional<Produit> existingProduitOptional = produitRepository.findById(id);
		    if (existingProduitOptional.isPresent()) {
		        Produit existingProduit = existingProduitOptional.get();
		        existingProduit.setRef(produit.getRef());
		        existingProduit.setQuantite(produit.getQuantite());
		        existingProduit.setPrixUnitaire(produit.getPrixUnitaire());
		        return produitRepository.save(existingProduit);
		    }
		    return null;
	}



}
