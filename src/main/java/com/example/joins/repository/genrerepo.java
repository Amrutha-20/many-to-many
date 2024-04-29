package com.example.joins.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.joins.model.genre;

public interface genrerepo extends JpaRepository<genre,Long>{
    
}
