package com.samta.project.model;

public class QuestionDTO {
	
	private String questionId;
    private String question;
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public QuestionDTO(String questionId, String question) {
		super();
		this.questionId = questionId;
		this.question = question;
	}
    
    


}
