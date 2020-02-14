// Question.java
package onlineTest;

import java.io.Serializable;

public class Question implements Serializable {
	private static final long serialVersionUID = 1L;
	//declare variables for questions on exam
   int examID, questionNumber; 
   String question, answer;
   double points;
   
   public Question(int examID,int questionNumber, String question, double points){
    //each question needs to be assigned a exam number, a weight, text value, and a number;
	   this.examID = examID;
       this.questionNumber = questionNumber;
       this.question = question;
       this.points = points;
      // this.answer=answer;
   }
   //getters and setters
   public String getQuestion(){
       return question;
   }

   public void setQuestion(String question){
       this.question = question;
   }

   public double getPoints(){
       return points;
   }

   public void setPoints(double points){
       this.points = points;
   }


   public void setExamID(int examID){
       this.examID = examID;
   }

   public int getQuestionNumber(){
       return questionNumber;
   }
   public String getQuestionAnswer() {
	   return this.answer;
   }
   public void setQuestionNumber(int questionNumber){
       this.questionNumber = questionNumber;
   }
  @Override
   public String toString(){
       String result = "Question Text: "+getQuestion()+"\n";
       result+="Points: "+this.points+"\n";
       return result;
   }

public int getexamID() {

	return examID;
}





}