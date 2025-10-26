package com.quiz.services;

import com.quiz.CSVDrivers.CSVQuestionsDriver;
import com.quiz.models.QuestionModel;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private CSVQuestionsDriver questionsDriver;
    private LinkedList<QuestionModel> allQuestions;
    private CSVQuestionsDriver driver;
    private LinkedList<String> categories;
    private Map<String, LinkedList<QuestionModel>> cachedCategoryQuizzes = new HashMap<>();

    public QuizService(CSVQuestionsDriver driver) {
        this.driver = driver;
        this.allQuestions = new LinkedList<>();
        this.questionsDriver = new CSVQuestionsDriver();
        this.categories = new LinkedList<>();
        this.allQuestions = driver.readAllQuestions();
    }

    public LinkedList<String> getCategories() {
        for (var q : this.allQuestions) {
            if (!categories.contains(q.getCategory())) {
                categories.add(q.getCategory());
            }
        }
        return categories;
    }

    public LinkedList<QuestionModel> getQuestionsByCategory(String category) {

        if (cachedCategoryQuizzes.containsKey(category)) {
            return cachedCategoryQuizzes.get(category);
        }

        List<QuestionModel> filtered = allQuestions.stream()
                .filter(q -> q.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());

        Collections.shuffle(filtered);
        List<QuestionModel> selected = filtered.stream()
                .limit(10)
                .collect(Collectors.toList());
        LinkedList<QuestionModel> quiz = new LinkedList<>(selected);
        cachedCategoryQuizzes.put(category, quiz);
        return quiz;
    }
}
