package com.swati.quiz_dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swati.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
    
}
