package com.swati.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swati.model.Question;
import com.swati.model.QuestionWrapper;
import com.swati.model.Quiz;
import com.swati.model.Response;
import com.swati.quiz_dao.QuestionDao;
import com.swati.quiz_dao.QuizDao;

@Service
public class QuizService {

    @Autowired
    QuizDao quizdao;
    @Autowired
    QuestionDao questiondao;
    public ResponseEntity<String> create(String category, int numques, String title) {
          List<Question> question = questiondao.findRandomQuestionsByCategory(category, numques);
          Quiz quiz = new Quiz();
          quiz.setTitle(title);
          quiz.setQuestion(question);
          quizdao.save(quiz);

        return new ResponseEntity<>("Success" , HttpStatus.CREATED);
          

    }
    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        
         Optional <Quiz> quiz = quizdao.findById(id);
         List<Question> questionfromdb= quiz.get().getQuestion();
         List<QuestionWrapper> questionForUser= new ArrayList<>();
         for(Question q: questionfromdb)
         {
            QuestionWrapper qw= new QuestionWrapper(q.getId(), q.getCategory(),q.getOption1(), q.getOption2(),q.getOption3(), q.getOption4(),q.getQuestion_title());
            questionForUser.add(qw);
         }

        return new ResponseEntity<>(questionForUser , HttpStatus.OK);
    
    
}
    public ResponseEntity<Integer> CalculateResult(int id, List<Response> response) {
      Quiz quiz = quizdao.findById(id).get();
      List<Question> question= quiz.getQuestion();
      int right=0;
      int i=0;
      for(Response res: response)
      {
        if(res.getResponse().equals(question.get(i).getRight_answer()))
        {
         right++;
        }

        i++;
      }
        return new ResponseEntity<>(right , HttpStatus.OK) ;
    }
}
    

    

