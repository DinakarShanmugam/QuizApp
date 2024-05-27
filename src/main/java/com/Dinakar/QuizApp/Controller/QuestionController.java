package com.Dinakar.QuizApp.Controller;


import com.Dinakar.QuizApp.Model.Question;
import com.Dinakar.QuizApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable("category") String category){
        return questionService.getAllQuestionsByCategory(category);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategorys(@PathVariable("category") String category){
        return questionService.getAllQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") Integer id ){
        return  questionService.deleteQuestion(id);
    }



}
