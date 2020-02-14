// Exam.java
package onlineTest;

import java.io.Serializable;
import java.util.*;

public class Exam implements Serializable {
	private static final long serialVersionUID = 1L;
	// CREATE THE EXAM
	int examID;
	String examTitle;
	//An exam is a set of Questions (no duplicates)
	Set<Question> questions;

	public Exam(){
		//default values
		examID = 0;
		examTitle = "";
		questions = new HashSet<Question>();
	}

	public Exam(int examID, String examTitle){
		//assign title + ID to exam
		this.examID = examID;
		this.examTitle = examTitle;
		questions = new HashSet<Question>();
	}
	//setters and getters
	public int getExamID(){
		return this.examID;
	}

	public void setExamID(int examID){
		this.examID = examID;
	}

	public String getExamTitle(){
		return this.examTitle;
	}

	public void setExamTitle(String examTitle){
		this.examTitle = examTitle;
	}

	public void addQuestion(Question question){
		//add question to set
		this.questions.add(question);
	}

	public Set<Question> getQuestions(){
		return this.questions;
	}

	public Question getQuestion(int examId){
		//check if the ID associated with each question is in the exam
		for (Question question : this.questions){
			if (question.getexamID() == examId)
				return question;
		}
		return null;
	}

	public boolean equals(Exam other){
		//If two exams have same ID's and names they the same exam bro
		if (this.examID == other.getExamID()&&this.examTitle.equals(other.getExamTitle())){
			return true;
		}
				return false;
	}

}