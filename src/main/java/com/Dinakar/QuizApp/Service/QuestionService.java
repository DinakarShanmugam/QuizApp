package com.Dinakar.QuizApp.Service;

import com.Dinakar.QuizApp.DAO.QuestionDao;
import com.Dinakar.QuizApp.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try{
            Optional<Question> q=questionDao.findById(id);
            if(q.isPresent()){
                questionDao.deleteById(id);
                return new ResponseEntity<>("success",HttpStatus.ACCEPTED);
            }
            else{
                return new ResponseEntity<>("Id not present check the database",HttpStatus.ACCEPTED);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("some error occured  ... ",HttpStatus.ACCEPTED);
    }
}
