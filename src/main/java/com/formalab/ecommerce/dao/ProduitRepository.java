package com.formalab.ecommerce.dao;

import com.formalab.ecommerce.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


@EnableJpaRepositories("com.formalab.ecommerce.dao")
public interface ProduitRepository extends JpaRepository<Produit,Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Produit p SET p.prixVente =:prix WHERE p.id=:id")
    public void updatePrix(@Param("id") Integer id,@Param("prix") Double prix);
}
