package com.CreateSolutions.QuizGenerator.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class QuestionWrapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String query;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public QuestionWrapper(int ID, String query, String option1, String option2, String option3, String option4) {
        this.ID = ID;
        this.query = query;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
}
