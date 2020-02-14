// MultipleChoiceQuestion.java
package onlineTest;

import java.io.Serializable;

//Multiple Choice IS A type of Question
public class MultipleChoiceQuestion extends Question implements Serializable {
	private static final long serialVersionUID = 1L;
   String answers[];
   //Only unique aspect of this Question is the answer type, everything else can go to super
   public MultipleChoiceQuestion(int examID, int questionNumber, String questionText, double points, String answers[]){
       super(examID, questionNumber, questionText, points);
       this.answers = answers;
   }
   //setter and getter
   public String[] getAnswers(){
       return answers;
   }

   public void setAnswers(String[] answers) {
       this.answers = answers;
   }
  
   public String toString(){      
       String result=super.toString()+"Correct Answer: [";
       for(int i = 0; i<answers.length; i++){
           if(i<answers.length-1){
               result+= answers[i]+", ";
           }
           else{
               result+=answers[i]+"]\n";
           }
       }
       return result;      
   }
  
   public double getScoreValue(MultipleChoiceQuestion other)
   {
       String otheranswers[] = other.getAnswers();
    if(otheranswers.length != this.answers.length) {
    	return 0;
    }
   
       for(int i = 0; i<answers.length; i++){
           if(!otheranswers[i].equals(answers[i])){
               return 0;
           }
       }
       return this.points;
   }
}