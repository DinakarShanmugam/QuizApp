package com.Dinakar.QuizApp.Service;


import com.Dinakar.QuizApp.DAO.QuestionDao;
import com.Dinakar.QuizApp.DAO.QuizDao;
import com.Dinakar.QuizApp.Model.Question;
import com.Dinakar.QuizApp.Model.QuestionWrapper;
import com.Dinakar.QuizApp.Model.Quiz;
import com.Dinakar.QuizApp.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<String> createNew(String category, int numQ, String title) {
        try {
            List<Question> questions = questionDao.getRandomQuestionByCategory(category, numQ);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);
            return new ResponseEntity<>("Successfully created a new quiz", HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            System.out.println("Some error in QuizService");
        }
        return new ResponseEntity<>("Some error in QuizService", HttpStatus.ACCEPTED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questionsRaw=quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser =new ArrayList<>();
        for(Question question:questionsRaw){
            QuestionWrapper questionWrapper=new QuestionWrapper(
                    question.getId(), question.getQuestion(), question.getOption1(),question.getOption2(),question.getOption3()
            );
            questionsForUser.add(questionWrapper);
        }
        return new ResponseEntity<>(questionsForUser,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Integer> checkScore(Integer id, List<Response> responses) {
        int i,j,k=0;
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> l=quiz.get().getQuestions();
        int n=l.size();
        System.out.println(l);
        System.out.println(responses);
        for(i=0;i<n;i++){
            if(responses.get(i).getResponse().equals(l.get(i).getOption4())){
                k++;
            }
        }
        return  new ResponseEntity<>(k,HttpStatus.ACCEPTED);

    }
}
