package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {

}
