Simple Quiz App

This is a Spring Boot MVC application that allows users to take multiple-choice quizzes and see their results.
All questions are loaded from CSV files; no database is required.
The app demonstrates MVC architecture, form handling, routing, and basic frontend styling.

Features

1. Home Pagе

    Lists all available quizzes.
    Users can select a quiz.

2. Display Quiz

   Shows 10 multiple-choice questions.
   Each question has 4 options.
  Users answer with radio buttons.
  “Retake Quiz” button reloads the quiz with different questions if the list is large enough to be shuffled.

3. Submit Answers

    Submits the form via POST.
    The controller evaluates answers and counts the correct ones.

4. Display Results

    Shows the number of correct answers.
   Displays a message based on the score (e.g., "Great job!", "Try again!").
   Shows the quiz category.

5. Bonus Features

   Questions are shuffled every time the quiz is loaded. This functionality ensures that when you go back and forward, you won’t see the same question in the same order.
   Next/Previous buttons for navigating between questions.
    Questions are loaded from CSV file.
    CSV file is generated with help of AI.
    Basic CSS styling with images for background and form container.
    Repo with instructions.

   
How to Run

1. Clone the repository.
2. Important:
   Please note that the paths in the CSV driver are personal path. If you want to use CSV file, please update the paths to your own working path.
3. Run app via terminal command or run button
4. Open the application in your browser using:
http://localhost:8080/show-quizzes


Final Words

The hardest part was making the questions functional and overcoming Errors 400/405.
These errors were caused by @RequestParam problem, and I had to find a way to make the parameters optional to prevent issues.
I hope skipping the timer functionality is for good, because I see how much time this functionality can take.


