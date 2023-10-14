package com.swati.quiz_dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swati.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question , Integer> {

  List<Question> findQuestionByCategory( String category);

 @Query(value = "select * from questionsex q where q.category=:category Order By Rand() Limit :numques", nativeQuery=true)
List<Question> findRandomQuestionsByCategory(String category, int numques); 
   

}
