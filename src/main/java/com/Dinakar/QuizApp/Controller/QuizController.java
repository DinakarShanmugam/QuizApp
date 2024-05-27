package com.Dinakar.QuizApp.Controller;

import com.Dinakar.QuizApp.Model.Question;
import com.Dinakar.QuizApp.Model.QuestionWrapper;
import com.Dinakar.QuizApp.Model.Response;
import com.Dinakar.QuizApp.Service.QuizService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createNew(category,numQ,title);
    }

    @GetMapping("/getQ/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
        return quizService.getQuiz(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> getScore(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.checkScore(id,responses);
    }

}
