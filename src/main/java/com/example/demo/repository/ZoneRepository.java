package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Zone;

public interface ZoneRespository extends JpaRepository<Zone,Long> {

    List<Zone>findByZoneName(String name);
    
}
