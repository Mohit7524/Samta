package com.samta.project.model;

public class NextQuestionResponse {
	
	private String correctAnswer;
    private QuestionDTO nextQuestion;
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public QuestionDTO getNextQuestion() {
		return nextQuestion;
	}
	public void setNextQuestion(QuestionDTO nextQuestion) {
		this.nextQuestion = nextQuestion;
	}
	public NextQuestionResponse(String correctAnswer, QuestionDTO nextQuestion) {
		super();
		this.correctAnswer = correctAnswer;
		this.nextQuestion = nextQuestion;
	}
    
    


}
