Project Description

Simple Quiz App

This is a Spring Boot MVC application that allows users to take multiple-choice quizzes and see their results.
All questions are loaded from CSV files; no database is required.
The app demonstrates MVC architecture, form handling, routing, and basic frontend styling.


Features

Home Pagе

Lists all available quizzes.
Users can select a quiz.


Display Quiz

Shows 10 multiple-choice questions.
Each question has 4 options.
Users answer with radio buttons.
“Retake Quiz” button reloads the quiz with different questions if the list is large enough to be shuffled. 


Submit Answers

Submits the form via POST.
The controller evaluates answers and counts the correct ones.


Display Results

Shows the number of correct answers.
Displays a message based on the score (e.g., "Great job!", "Try again!").
Shows the quiz category.


Advanced Features

Questions are shuffled on each load.
Next/Previous buttons for navigating between questions.
Questions are loaded from CSV files.


Bonus Features

Basic CSS styling with images for background and form container.


How to Run

1. Clone the repository.
2. Important:
   Please note that the paths in the CSV drivers are personal paths. If you want to use them, update the paths to your own absolute paths.
3. Open the application in your browser using:


http://localhost:8080/show-quizzes


Final Words

The hardest part was making the questions functional and overcoming Errors 400/405.
These errors were caused by @RequestParam, and I had to find a way to make the parameters optional to prevent issues.
I hope skipping the timer is for good, because I see how much time this will cost.


