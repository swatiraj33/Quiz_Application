package com.swati.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swati.model.Question;
import com.swati.quiz_dao.QuestionDao;


@Service
public class QuestionService {
    
    @Autowired
    QuestionDao quesdao;
    public ResponseEntity<List<Question>> getAllQuestions()
    {
       try {
        return new ResponseEntity<>( quesdao.findAll() ,HttpStatus.OK);
       } catch (Exception e) {
          return new ResponseEntity<>( new ArrayList<>() ,HttpStatus.BAD_REQUEST);
       }
        
    }
    
    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
         try {
            return new ResponseEntity<>(quesdao.findQuestionByCategory(category),HttpStatus.OK) ;
         } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST) ;
         }
         
    }

    public ResponseEntity<String> addQuestions(Question question) {

        quesdao.save(question);
        try {
            
         return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(" Question Not Found ", HttpStatus.BAD_REQUEST);
        } 
    }


    public ResponseEntity<String> deleteQuestionById(int id) {
       
     
        if(quesdao.existsById(id))
        {
        quesdao.deleteById(id);
        return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Id not Present ", HttpStatus.BAD_REQUEST);
   
    
     
    
    
    
}   
       
}
