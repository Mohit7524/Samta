package com.samta.project.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samta.project.Repository.QuestionRepository;
import com.samta.project.model.NextQuestionResponse;
import com.samta.project.model.Question;
import com.samta.project.model.QuestionDTO;

@RestController
@RequestMapping("/api")
public class QuestionController {
	
	@Autowired
    private QuestionRepository questionRepository;
	
	@GetMapping("/play")
    public QuestionDTO play() {
        // Fetch 5 random questions from the database
        List<Question> randomQuestions = getRandomQuestions(5);

        // Return the first question from the random questions list
        if (!randomQuestions.isEmpty()) {
            Question question = randomQuestions.get(0);
            return new QuestionDTO(question.getId(), question.getQuestion());
        } else {
            throw new RuntimeException("No questions available.");
        }
    }
	
	@PostMapping("/next")
    public NextQuestionResponse next(@RequestBody NextQuestionRequest request) {
        String questionId = request.getQuestionId();
        String answer = request.getAnswer();

        // Find the question in the database by the provided questionId
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with ID: " + questionId));

        // Validate the answer
        boolean isCorrect = question.getAnswer().equalsIgnoreCase(answer);

        // Fetch the next question from the database
        List<Question> randomQuestions = getRandomQuestions(1);

        // Return the response with the correct answer and the next question
        NextQuestionResponse response = new NextQuestionResponse();
        response.setCorrectAnswer(question.getAnswer());

        if (!randomQuestions.isEmpty()) {
            Question nextQuestion = randomQuestions.get(0);
            response.setNextQuestion(new QuestionDTO(nextQuestion.getId(), nextQuestion.getQuestion()));
        }

        return response;
    }
	
	private List<Question> getRandomQuestions(int count) {
        List<Question> allQuestions = questionRepository.findAll();
        int totalQuestions = allQuestions.size();

        if (totalQuestions <= count) {
            return allQuestions;
        } else {
            Random random = new Random();
            List<Question> randomQuestions = new ArrayList<>();

            while (randomQuestions.size() < count) {
                int randomIndex = random.nextInt(totalQuestions);
                Question randomQuestion = allQuestions.get(randomIndex);
                if (!randomQuestions.contains(randomQuestion)) {
                    randomQuestions.add(randomQuestion);
                }
            }

            return randomQuestions;
        }
    }
	


}
