package com.quiz.CSVDrivers;

import com.quiz.models.QuestionModel;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;

@Component
public class CSVQuestionsDriver {
    private LinkedList<QuestionModel> questions;

    // The path shall be overwritten when somebody with different machine use it.
    private final String path = "C:\\Users\\evgen\\Desktop\\app\\app\\src\\main\\java\\com\\quiz\\CSVFiles\\questions.csv";
    public CSVQuestionsDriver() {
        this.questions = new LinkedList<>();
    }

    public LinkedList<QuestionModel> readAllQuestions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String read;
            reader.readLine();
            while ((read = reader.readLine()) != null) {
                String[] data = read.split(";");
                int number = Integer.parseInt(data[0]);
                String category = data[1];
                String question = data[2];
                HashMap<Character, String> choices = new HashMap<>();
                choices.put('A', data[3]);
                choices.put('B', data[4]);
                choices.put('C', data[5]);
                choices.put('D', data[6]);
                Character validAnswer = data[7].charAt(0);

                QuestionModel currentQuestion = new QuestionModel(
                        number,
                        category,
                        question,
                        choices,
                        validAnswer);
                this.questions.add(currentQuestion);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
         return questions;
    }


}