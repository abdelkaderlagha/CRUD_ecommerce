package com.formalab.ecommerce.controller;

import com.formalab.ecommerce.dao.ProduitRepository;
import com.formalab.ecommerce.model.Categorie;
import com.formalab.ecommerce.model.Produit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "gestion des produit")
@RestController
@RequestMapping(value="/rest/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ProduitController {
    @Autowired
    ProduitRepository produitRepository;

    @ApiOperation("consulter tout les produits")
    @GetMapping(value="/allProduit")
    public List<Produit> allProduit(){
        return produitRepository.findAll();
    }

    @GetMapping(value="/produit/{id}")
    public ResponseEntity<Produit> produit(@PathVariable Integer id) throws Exception{
        final Produit produit = produitRepository.findById(id).orElseThrow(()->new Exception("Le produit n'existe pas"));
        return ResponseEntity.ok().body(produit);
    }

    @ApiOperation("consulter tout les produits")
    @PostMapping(value="/addProduit")
    @PreAuthorize("hasRole('admin') or hasRole('pm')")
    public Produit addProduit(@Valid @RequestBody Produit produit){
        return produitRepository.save(produit);
    }

    @PutMapping(value="/produit/{id}")
    @PreAuthorize("hasRole('admin') or hasRole('pm')")
    public ResponseEntity<Produit> updateProduit(@PathVariable Integer id, @Valid @RequestBody Produit produitDetails) throws Exception{
        Produit produit = produitRepository.findById(id).orElseThrow(()->new Exception("Le produit n'existe pas"));
        produit.setNom(produitDetails.getNom());
        produit.setPrixAchat(produitDetails.getPrixAchat());
        produit.setPrixVente(produitDetails.getPrixVente());
        produit.setCategorie(produitDetails.getCategorie());
        produitRepository.save(produit);
        return ResponseEntity.ok(produit);
    }

    @DeleteMapping(value="/produit/{id}")
    @PreAuthorize("hasRole('admin') or hasRole('pm')")
    public Map<String,Boolean> deleteProduit(@PathVariable Integer id) throws Exception{
        Produit produit = produitRepository.findById(id).orElseThrow(()->new Exception("Le produit n'existe pas"));
        produitRepository.delete(produit);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Le produit est supprimé!",Boolean.TRUE);
        return response;
    }

    @PutMapping(value="/produit/{id}/{prix}")
    @PreAuthorize("hasRole('admin') or hasRole('pm')")
    public void updatePrix(@PathVariable Integer id,@PathVariable Double prix){
        produitRepository.updatePrix(id,prix);
    }

}
