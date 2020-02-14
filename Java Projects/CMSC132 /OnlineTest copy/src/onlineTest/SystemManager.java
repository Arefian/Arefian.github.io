package onlineTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;






public class SystemManager implements Manager, Serializable {
	private static final long serialVersionUID = 1L;
	//
	Question currentQuestion, currentAnswer;
	HashMap<Exam, Set<Question>> questionsList;
	HashMap<String, Set<Question>> studentAnswers;
	//HashMap<String, TreeMap<Exam,Question>> studentAnswers;
	String result = "";
	double questionScore = 0;
	private double[] scoreGrades;
	private String[] letterGrades;



	public SystemManager(){
		questionsList = new HashMap<Exam, Set<Question>>();
		studentAnswers = new HashMap<String, Set<Question>>();
		currentQuestion = null;
		currentAnswer = null;

	}
	//create keyList
	public Exam getExamId(int id){
		//Creating a set of Exams that represents the keys
		Set<Exam> keyList = questionsList.keySet();
		for (Exam exam : keyList){
			if (exam.getExamID() == id) {
				return exam;
			}
		}
		return null;
	}
	@Override
	public boolean addStudent(String name){
		// if map is empty populate with some name, no questions yet
		if (studentAnswers.size() == 0){
			studentAnswers.put(name, null);          
			//  System.out.println(name);

			return true;
		}
		else{
			//create some set of the current keys to iterate through for compare
			Set<String> StudentKeys = studentAnswers.keySet();
			for (String studentName : StudentKeys){
				if (name.equals(studentName)) {
					return false;
				}
			}
			//if the name was not in the list, it is now
			studentAnswers.put(name, null);
			//     System.out.println(name);
			return true;
		}
	}

	@Override
	public boolean addExam(int examId, String title) {
		//create the exam
		Exam newExam = new Exam(examId, title);
		//creating a set of Exams
		Set<Exam> QuestionSet = questionsList.keySet();
		//if set is empty, add the exam to the exam
		if (QuestionSet == null) {
			questionsList.put(newExam, null);
			return true;
		}
		else {
			//Iterate through the list of exams, if the exam is already there, return false
			for (Exam exam : QuestionSet){
				if (exam.equals(newExam)){
					return false;
				}
			}
		}
		//If set isnt empty, and Exam isn't in the set
		questionsList.put(newExam, null);
		return true;
	}



	@Override
	public void addTrueFalseQuestion(int examId, int questionNumber, String text,
			double points, boolean answer) {
		//Find the exam the question belongs on
		Exam exam = getExamId(examId);
		//create a set of questions that represent the current exam
		Set<Question> question = questionsList.get(exam);

		if (question == null){
			//if set is empty, create an ordered hashset and insert question
			question = new LinkedHashSet<Question>();
			questionsList.put(exam, question);

		}
		//if set isnt empty just add the question to current exam
		currentQuestion = new TrueOrFalseQuestion(examId, questionNumber, text, points, answer);
		question.add(currentQuestion);
		// TODO Auto-generated method stub

	}

	@Override
	public void answerTrueFalseQuestion(String studentName, int examId,
			int questionNumber, boolean answer) {
		//Create Exam object 
		Exam exam = getExamId(examId);
		//get the set of questions for the exam
		Set<Question> questions = questionsList.get(exam);

		Question currentQ = null;
		for (Question question : questions){
			//System.out.println(question.getQuestionNumber());
			//  System.out.println(questionNumber);

			if (question.getQuestionNumber() == questionNumber) {
				currentQ = question;
			}
		}
		//student answer to current question
		currentAnswer = new TrueOrFalseQuestion(examId, questionNumber, currentQ.getQuestion(), currentQ.getPoints(), answer);

		Set<Question> answers = studentAnswers.get(studentName);
		if (answers == null){
			answers = new LinkedHashSet<Question>();
			studentAnswers.put(studentName, answers);
		}
		answers.add(currentAnswer);


	}
	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber,
			String text, double points, String[] answer) {
		// TODO Auto-generated method stub

		Exam exam = getExamId(examId); 	//Find the exam the question needs to be on
		Set<Question> question = questionsList.get(exam); //Create a set of the exams questions

		if (question == null) { // if set is empty, create an ordered set and put first value
			question = new LinkedHashSet<Question>();
			questionsList.put(exam, question);
		}
		//If the set has already been initilized and populated, just add the questions to the exam
		currentQuestion = new MultipleChoiceQuestion(examId, questionNumber, text, points, answer);
		question.add(currentQuestion);



	}
	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber,
			String text, double points, String[] answer) {
		// TODO Auto-generated method stub
		Exam exam = getExamId(examId);
		Set<Question> questions = questionsList.get(exam);

		if (questions == null){
			questions = new LinkedHashSet<Question>();
			questionsList.put(exam, questions);

		}

		currentQuestion = new FillInTheBlanksQuestion(examId, questionNumber, text, points, answer);
		questions.add(currentQuestion);  

	}
	@Override
	public String getKey(int examId)
	{
		/*
		 * "Question Text: " followed by the question's text<br />
		 *
		 * "Points: " followed by the points for the question<br />
		 *
		 * "Correct Answer: " followed by the correct answer. <br />
		 *
		 * The format for the correct answer will be: <br />
		 *
		 * a. True or false question: "True" or "False"<br />
		 *
		 * b. Multiple choice question: [ ] enclosing the answer (each entry
		 * separated by commas) and in
		 *
		 * sorted order. <br />
		 *
		 * c. Fill in the blanks question: [ ] enclosing the answer (each entry
		 * separated by commas) and
		 */
		Exam exam = getExamId(examId);
		Set<Question> questions = questionsList.get(exam);

		FillInTheBlanksQuestion tfq = null;
		String result = "";
		//Assuming set of questions is not empty
		if (questions != null){
			for (Question quest : questions) {
				result+= quest.toString();
			}
			return result;
		}
		return "Exam not found";
	}
	@Override
	public void answerMultipleChoiceQuestion(String studentName, int examId,
			int questionNumber, String[] answer) {
		// TODO Auto-generated method stub
		//Create Exam object 
		Exam exam = getExamId(examId);
		//get the set of questions for the exam
		Set<Question> questions = questionsList.get(exam);

		Question quest = null;
		for (Question question : questions){
			//System.out.println(question.getQuestionNumber());
			//  System.out.println(questionNumber);

			if (question.getQuestionNumber() == questionNumber) {
				quest = question;
			}
		}
		//student answer to current question
		currentAnswer = new MultipleChoiceQuestion( examId, questionNumber, quest.question,  quest.points, answer);



		Set<Question> answers = studentAnswers.get(studentName);
		if (answers == null)
		{
			answers = new LinkedHashSet<Question>();
			studentAnswers.put(studentName, answers);
		}
		answers.add(currentAnswer);



	}
	@Override
	public void answerFillInTheBlanksQuestion(String studentName, int examId,
			int questionNumber, String[] answer) {
		// TODO Auto-generated method stub
		//Create Exam object 
		Exam exam = getExamId(examId);
		//get the set of questions for the exam
		Set<Question> questions = questionsList.get(exam);

		Question currentQ = null;
		for (Question question : questions){
			//System.out.println(question.getQuestionNumber());
			//  System.out.println(questionNumber);

			if (question.getQuestionNumber() == questionNumber) {
				currentQ = question;
			}
		}
		//student answer to current question
		currentAnswer = new FillInTheBlanksQuestion( examId, questionNumber, currentQ.question,  currentQ.points, answer);



		Set<Question> answers = studentAnswers.get(studentName);
		if (answers == null){
			answers = new LinkedHashSet<Question>();
			studentAnswers.put(studentName, answers);
		}
		answers.add(currentAnswer);


	}
	@Override
	public double getExamScore(String studentName, int examId) {

		// TODO Auto-generated method stub
		double score = 0;
		Exam exam = getExamId(examId);      
		Set<Question> questions = questionsList.get(exam);
		Set<Question> answers = studentAnswers.get(studentName);  

		score = getTotalScore(questions, answers);
		return score;
	}


	private double getTotalScore(Set<Question> questions, Set<Question> answers){
		double totalScore = 0;
		TrueOrFalseQuestion tfq = null, tfa = null;
		MultipleChoiceQuestion mcq = null, mca = null;
		FillInTheBlanksQuestion fbq = null, fba = null;
		result = "";
		int questionCount = 1;
		// double eachScore = 0;
		questionScore = getTotalQuestionScore(questions);
		for (Question quest : questions){
			//reset question values
			tfq = null; mcq = null; fbq = null;
			if (quest instanceof TrueOrFalseQuestion) {
				tfq = (TrueOrFalseQuestion) quest;
			}
			else if (quest instanceof MultipleChoiceQuestion){
				mcq = (MultipleChoiceQuestion) quest;
			}
			else if (quest instanceof FillInTheBlanksQuestion){
				fbq = (FillInTheBlanksQuestion) quest;
			}
			for (Question answer : answers){
				//reset answer values
				tfa = null; mca = null; fba = null;

				if (answer instanceof TrueOrFalseQuestion&& tfq != null){
					//We've checked that its a T/F question, so cast answer so we can access T/F methods
					tfa = (TrueOrFalseQuestion) answer;
					//Check if the question and answer match
					if (tfa.getexamID() == tfq.getexamID() && tfa.getQuestionNumber() == tfq.getQuestionNumber()){
						// Add the score the answer receives out of questions total value
						result += "Question #"+questionCount+" "+tfq.getScoreValue(tfa)+" points out of "+quest.points+"\n";
						//Students total score is a sum of their questions scores
						totalScore += tfq.getScoreValue(tfa);
						questionCount++;
					}
				}
				if (answer instanceof MultipleChoiceQuestion && mcq != null){
					mca = (MultipleChoiceQuestion) answer;
					if (mca.getexamID() == mcq.getexamID() && mca.getQuestionNumber() == mcq.getQuestionNumber()){
						result += "Question #"+questionCount+" "+mca.getScoreValue(mcq)+" points out of "+quest.points+"\n";
						totalScore += mcq.getScoreValue(mca);
						questionCount++;
					}

				}
				if (answer instanceof FillInTheBlanksQuestion&& fbq != null){
					fba = (FillInTheBlanksQuestion) answer;
					if (fba.getexamID() == fbq.getexamID() && fba.getQuestionNumber() == fbq.getQuestionNumber()){
						result += "Question #"+questionCount+" "+fba.getScoreValue(fbq)+" points out of "+quest.points+"\n";
						fba.getScoreValue(fbq) ;
						totalScore += fba.getScoreValue(fbq);
						questionCount++;
					}

				}
			}
		}
		return totalScore;   
	}


	private double getTotalQuestionScore(Set<Question> questions){
		double score = 0;      
		for (Question currentQ : questions){
			score+=currentQ.getPoints();

		}
		return score;
	}
	@Override
	public String getGradingReport(String studentName, int examId) {
		// TODO Auto-generated method stub

		Exam exam = getExamId(examId);
		String report; //Fix this, we should not be getting the report make it empty	
		Set<Question> questions = questionsList.get(exam);
		double TotalQuestionsWorth = getTotalQuestionScore(questions); //this should be TotalQuestionsWorth
		double FinalScore = 0;
		FinalScore = getExamScore(studentName, examId);
		report = result;

		report+="Final Score: "+ FinalScore+ " out of "+ TotalQuestionsWorth+"\n";
		// System.out.println(report);
		return report;
	}

	public String getAnswer(String questionAnswer){
		Set<String> keys = studentAnswers.keySet();
		for (String answer : keys) {
			//System.out.print(answer);
			if (answer.equals(questionAnswer)){
				return answer;
			}
		}
		return null;
	}
	@Override
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		// TODO Auto-generated method stub
		this.letterGrades = letterGrades;
		this.scoreGrades = cutoffs;
	}
	@Override
	public double getCourseNumericGrade(String studentName) {
		//  String answer = getAnswer(studentName);      

		Set<Question> answers = studentAnswers.get(studentName);
		int examId = 0;
		double questionworth = 0;
		//  int counter[] = new int[50];
		double score = 0;
		for(Question ans: answers) {
			if(ans.getexamID() != examId) {
				examId = ans.getexamID();
				score += getExamScore(studentName, examId);
				Exam exam = getExamId(examId);
				Set<Question> questionsPoint = questionsList.get(exam);
				questionworth +=  getTotalQuestionScore(questionsPoint);


				//    counter[examId] = examId;
			}
		}

		//  score = score/counter;

		// TODO Auto-generated method stub
		return score/questionworth *100;
	}
	@Override
	public String getCourseLetterGrade(String studentName) {
		double score = getCourseNumericGrade(studentName);
		for(int i =0; i<letterGrades.length; i++){
			if(score>=scoreGrades[i]) {
				return letterGrades[i];

			}
		}
		return null;
	}
	/*Returns a listing with the grades for each student. For each student the report will include the following information: 
{studentName} {courseNumericGrade} {courseLetterGrade}
The names will appear in sorted order.*/

	@Override
	public String getCourseGrades() {
		// TODO Auto-generated method stub
		StringBuilder answer = new StringBuilder();

		String finalAnswer = "";
		Set<String> holder = studentAnswers.keySet();
		List<String> list = new ArrayList<String>(holder);
		list.sort(String.CASE_INSENSITIVE_ORDER);

		for(String student :list) {
			double score = getCourseNumericGrade(student);
			String letterGrade = getCourseLetterGrade(student);
			answer= answer.append(student);
			answer.append(score);
			answer.append(letterGrade+"\n");


		}
		finalAnswer +=  answer.toString();

		return finalAnswer;
	}

	@Override
	public double getMaxScore(int examId) {
		// TODO Auto-generated method stub
		double max = 0;
		Set<String> holder = studentAnswers.keySet();
		for(String student : holder ) {
			double score = getExamScore(student, examId);
			if(score>max) {
				max = score;
			}


		}
		return max;
	}
	@Override
	public double getMinScore(int examId) {
		// TODO Auto-generated method stub
		double min = 1005; //arbitrarily large number
		Set<String> holder = studentAnswers.keySet();
		for(String student : holder ) {
			double score = getExamScore(student, examId);
			if(score<=min) {
				min = score;
			}


		}
		return min;
	}
	@Override
	public double getAverageScore(int examId) {
		// TODO Auto-generated method stub
		double averageScore = 0; 
		double averageQuestions = 0;
		double finalAverage =0;
		//number of students who
		Set<String> holder = studentAnswers.keySet();
		//represents number of students who haven taken exam
		int counter = 1;
		//which exam 
		Exam exam = getExamId(examId);
		//get the questions of this exam
		Set<Question> questions = questionsList.get(exam);
		//for each student who has taken the exam
		for(String student : holder ) {

			double score = getExamScore(student, examId);
			double totalquestionscore =	getTotalQuestionScore(questions);
			averageQuestions +=totalquestionscore;
			averageScore += score;
		//	System.out.println(averageScore+ "this is the Average score + ");
		//	System.out.println(averageQuestions + " This is the questions total");


			counter++;

		}
		finalAverage = averageScore/averageQuestions;
	//	System.out.print(finalAverage + " this is total score average/ total questions");
		//System.out.print( averageScore + " this is the average score \n");
		double scored = Math.round(finalAverage*100);
		//	System.out.println(scored + "THIS IS THE AVERAGE");
		return scored;
	}
	@Override
	public void saveManager(Manager manager, String fileName) {
		File file = new File(fileName);


		ObjectOutputStream output = null;
		try {
			output = new ObjectOutputStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			output.writeObject(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	@Override
	public Manager restoreManager(String fileName)  {
		//File file = new File(fileName);
		//File file = new File("phonebookFile.info");

		try {
			FileInputStream fileInput = new FileInputStream(fileName);
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);


			Object phonebook = objectInput.readObject();


			objectInput.close();
			return (Manager) phonebook;
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}

}