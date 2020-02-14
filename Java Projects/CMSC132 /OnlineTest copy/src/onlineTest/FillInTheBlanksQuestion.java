
// FillInTheBlanksQuestion.java
package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

//Fill in the blanks IS A type of Question
public class FillInTheBlanksQuestion extends Question implements Serializable {
	private static final long serialVersionUID = 1L;

   String answers[];
   
   public FillInTheBlanksQuestion(int examID, int questionNumber, String question, double points, String answers[]){
	// TODO Auto-generated constructor stub
	   super(examID, questionNumber, question, points);
       this.answers = answers;
   }
   public String getAnswers(){
	   ArrayList<String> answerarraylist = new ArrayList<String>(Arrays.asList(answers));
	   Collections.sort(answerarraylist, String.CASE_INSENSITIVE_ORDER);
	   String[] stringArray = answerarraylist.toArray(new String[0]);
	   String str = String.join(", ", stringArray);
	   String finalString = "[" + str + "]";

	   return finalString;
}

   public void setAnswer(String[] answers){
       this.answers = answers;
   }
   public double getScoreValue(FillInTheBlanksQuestion other){
	  double score = 0;
	  int counter = 0;
	   Set<String> studentAnswers = new HashSet<String>(Arrays.asList(this.answers));
	   Set<String> questionAnswers = new HashSet<String>(Arrays.asList(other.answers));
	  // Set<String> intersection = new HashSet<String>(studentAnswers);
	   studentAnswers.retainAll(questionAnswers);
	   String[] correctAnswers = studentAnswers.toArray(new String[studentAnswers.size()]);
	   counter = correctAnswers.length;
	   if(counter == other.answers.length) {
		   return this.points;
	   }
	   
	   score = (counter / (double)(other.answers.length)) * (this.points);
	
	   
	   return score;
   }
  
   public String toString()
   {      
	  // String[] holder = getAnswer();
       String result=super.toString()+"Correct Answer: " + getAnswers();

       return result;      
   }
}