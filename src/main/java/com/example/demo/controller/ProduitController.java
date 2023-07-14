package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.IProduitService;
import com.example.demo.service.ProduitService;

import java.util.List;

import com.example.demo.entity.Produit ;

@RestController
@RequestMapping("/api/produit")
@CrossOrigin
public class ProduitController {
	@Autowired
	private IProduitService produitService ;
	private ProduitService prs ;
	
	@GetMapping
	public List<Produit> getProduits() {
		return produitService.getProduits();
	}
	
	@PostMapping
	public void addProduit(@RequestBody Produit produit) {
		 produitService.addProduit(produit);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id,  @RequestBody Produit produit) {
		Produit updateProduit = produitService.updateProduit(id, produit);
        if (updateProduit == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateProduit);
    }
	
	@DeleteMapping("/{id}")
	public void deleteProduit(@PathVariable Long id ) {	
		produitService.deleteProduit(id);
	}

}
