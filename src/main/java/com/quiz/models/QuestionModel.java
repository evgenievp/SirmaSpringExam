package com.quiz.models;

import java.util.HashMap;
import java.util.List;

// We should make blueprint of question here.
public class QuestionModel {
    private Integer number;
    private final Integer choices = 4;
    private String category;
    private String question;
    private HashMap<Character, String> options;
    private Character validAnswer;
    public QuestionModel(Integer number,
                     String category,
                     String question,
                     HashMap<Character, String> options,
                     Character validAnswer) {
        this.number = number;
        this.category = category;
        this.question = question;
        this.options = options;
        this.validAnswer = validAnswer;
    }
    // We can't set easy relation between quiz questions.csv and question number, so this is just basic restriction.
    public void setNumber(Integer number) {
        if (number < 1 || number > 10) {
            throw new RuntimeException("Question number out of bounds");
        }
        this.number = number;

    }
    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptions(HashMap<Character, String> options) {
        if (this.options.size() != 4) {
            throw new RuntimeException("In our rules options are always 4.");
        }
        this.options = options;
    }

    public void setValidAnswer(Character validAnswer) {
        this.validAnswer = validAnswer;
    }

    public String getCategory() {
        return this.category;
    }


    public Integer getNumber() {
        return number;
    }

    public Integer getChoices() {
        return choices;
    }

    public String getQuestion() {
        return question;
    }

    public HashMap<Character, String> getOptions() {
        return options;
    }

    public Character getValidAnswer() {
        return validAnswer;
    }
}
