package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Bin;


public interface BinRepository extends JpaRepository<Bin,Long>{
   
     Optional<Bin> findByIdentifer(String identifer);
    
}
