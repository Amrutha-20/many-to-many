package com.example.joins.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.joins.model.book;

public interface bookrepo extends JpaRepository<book,Long>{
    
}
