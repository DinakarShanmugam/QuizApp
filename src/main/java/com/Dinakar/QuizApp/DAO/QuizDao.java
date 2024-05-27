package com.Dinakar.QuizApp.DAO;

import com.Dinakar.QuizApp.Model.Question;
import com.Dinakar.QuizApp.Model.Quiz;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer> {

}
