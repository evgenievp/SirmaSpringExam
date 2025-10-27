package com.quiz.controllers;

import com.quiz.models.QuestionModel;
import com.quiz.services.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class MainController {
    private final QuizService serviceQuiz;
    private int validAnswers = 0;

    public MainController(QuizService serviceQuiz) {
        this.serviceQuiz = serviceQuiz;
    }

    @GetMapping("/show-quizzes")
    public String showQuizzes(Model model) {
        List<String> categories = this.serviceQuiz.getCategories();
        model.addAttribute("quizzes", categories);
        return "show-all";
    }



    @PostMapping("/show-quiz")
    public String redirectToQuiz(
            @RequestParam("category") String category,
            Model model) {
        if (category == null || category.isEmpty()) {
            return "redirect:/show-quizzes";
        }
        model.addAttribute("questions", serviceQuiz.getQuestionsByCategory(category));

        return "redirect:/show-quiz/" + category;
    }
    @GetMapping("/show-quiz/{category}")
    public String showQuiz(Model model,
                           @PathVariable("category") String category,
                           @RequestParam(required = false) Integer currentIdx,
                           @RequestParam(required = false) Integer validAnswers) {

        if (currentIdx == null){
            currentIdx = 0;
        }
        if (validAnswers == null) {
            validAnswers = 0;
        }

        LinkedList<QuestionModel> questionQuiz = serviceQuiz.getQuestionsByCategory(category);

        if (questionQuiz.isEmpty()) {
            model.addAttribute("question", null);
            model.addAttribute("validAnswers", validAnswers);
            model.addAttribute("currentIdx", currentIdx);
            model.addAttribute("category", category);
            return "show-quiz";
        }
        if (currentIdx < 0 || currentIdx >= questionQuiz.size()) {
            currentIdx = 0;
        }

        QuestionModel currentQuestion = questionQuiz.get(currentIdx);

        model.addAttribute("question", currentQuestion);
        model.addAttribute("validAnswers", validAnswers);
        model.addAttribute("currentIdx", currentIdx);
        model.addAttribute("category", category);

        return "show-quiz";
    }

    @PostMapping("/show-quiz/{category}")
    public String processAnswer(
            @PathVariable String category,
            @RequestParam(required = false) String answer,
            @RequestParam(required = false) Integer currentIdx,
            @RequestParam(required = false) Integer validAnswers) {

        if (currentIdx == null){
            currentIdx = 0;
        }
        if (validAnswers == null) {
            validAnswers = 0;
        }


        LinkedList<QuestionModel> questionQuiz = serviceQuiz.getQuestionsByCategory(category);
        /* Maybe this is bad idea, but I will shuffle questions every time.
        The reason is required prev button. This would prevent showing same questions.
         */
        questionQuiz = serviceQuiz.shuffleQuestions(questionQuiz);
        if (currentIdx < questionQuiz.size()) {
            QuestionModel currentQuestion = questionQuiz.get(currentIdx);

            if (currentQuestion.getValidAnswer().toString().equals(answer)) {
                validAnswers++;
            }
        }

        int nextIdx = currentIdx + 1;
        if (nextIdx >= questionQuiz.size()) {
            return "redirect:/quiz-result/" + category + "?validAnswers="  + validAnswers;
        }

        return "redirect:/show-quiz/" + category + "?currentIdx=" + nextIdx + "&validAnswers=" + validAnswers;
    }


    @GetMapping("/quiz-result/{category}")
    public String getResults(Model model,
                             @RequestParam int validAnswers,
                             @PathVariable String category){
        model.addAttribute("category", category);
        model.addAttribute("validAnswers",  validAnswers);
        return "show-results";
    }

}
