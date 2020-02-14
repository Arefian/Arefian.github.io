
package onlineTest;

import java.io.Serializable;

//TrueOrFalse IS a question, so it extends Question.
public class TrueOrFalseQuestion extends Question implements Serializable {
	private static final long serialVersionUID = 1L;

	boolean answer;

public TrueOrFalseQuestion(int examID, int questionNumber, String question, double points, boolean answer){
	super(examID, questionNumber, question, points);
    // TODO Auto-generated constructor stub
    this.answer = answer;
}
//setters and getters
public boolean Answer(){
    return this.answer;
}

public void setAnswer(boolean answer){
    this.answer = answer;
}
public double getScoreValue(TrueOrFalseQuestion question){  
	//compare this answer to acutal answer
    if(this.answer == question.Answer()) {
        return this.points;
    }
    //got it wrong
    return 0;
}

public String toString(){      
	String bool = Answer()==true?"True":"False";
    String result=super.toString()+
    		"Correct Answer: "+bool+"\n";      
    return result;      
}
}