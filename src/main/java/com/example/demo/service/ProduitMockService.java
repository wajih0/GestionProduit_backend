package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Produit;

@Service
public class ProduitMockService implements IProduitService {
	
	private List<Produit> produits ;
	public ProduitMockService() {
		
		produits = new ArrayList<Produit>();
		

	
		
	}

	@Override
	public List<Produit> getProduits() {
		// TODO Auto-generated method stub
		return produits;
	}

	@Override
	public void addProduit(Produit produit) {
       produits.add(produit);	
	}

	
	public void updateProduit(Produit produit) {
		// TODO Auto-generated method stub
		produits.remove(produit);
		produits.add(produit);
		
	}

	@Override
	public void deleteProduit(Long id) {
		// TODO Auto-generated method stub
		Produit produit = new Produit();
		produit.setId(id);
		produits.remove(produit);
				
			}

	@Override
	public Produit updateProduit(Long id, Produit produit) {
		// TODO Auto-generated method stub
		return null;
	}

}
