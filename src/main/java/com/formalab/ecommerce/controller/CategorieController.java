package com.formalab.ecommerce.controller;

import com.formalab.ecommerce.dao.CategorieRepository;
import com.formalab.ecommerce.model.Categorie;
import com.formalab.ecommerce.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value="/rest/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CategorieController {
    @Autowired
    CategorieRepository categorieRepository;

    @GetMapping(value="/allCategorie")
    public List<Categorie> allCategorie(){
        return categorieRepository.findAll();
    }

    @PostMapping(value="/addCategorie")
    @PreAuthorize("hasRole('admin') or hasRole('pm')")
    public Categorie addCategorie(@Valid @RequestBody Categorie c){
        return categorieRepository.save(c);
    }

    @GetMapping(value="/categorie/{id}")
    public ResponseEntity<Categorie> getCategorie(@PathVariable Integer id) throws Exception{
        Categorie c = categorieRepository.findById(id).orElseThrow(()->new Exception("La catégorie n'existe pas"));
        return ResponseEntity.ok().body(c);
    }

    @PutMapping(value="/categorie/{id}")
    @PreAuthorize("hasRole('admin') or hasRole('pm')")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Integer id,@Valid @RequestBody Categorie catDetails) throws Exception{
        Categorie c = categorieRepository.findById(id).orElseThrow(()->new Exception("La catégorie n'existe pas"));
        c.setNom(catDetails.getNom());
        Categorie updateCat = categorieRepository.save(c);
        return ResponseEntity.ok(updateCat);
    }

    @DeleteMapping(value="/categorie/{id}")
    @PreAuthorize("hasRole('admin') or hasRole('pm')")
    public Map<String,Boolean> deleteCategorie(@PathVariable Integer id) throws Exception {
        Categorie cat = categorieRepository.findById(id).orElseThrow(()->new Exception("La catégorie n'existe pas"));
        categorieRepository.delete(cat);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Catégorie est supprimé!",Boolean.TRUE);
        return response;
    }
}
