package Simulator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** QUESTION Abstract Class */
public class Question {
    String context = null;
    boolean isMultipleChoice;

    /** Constructor */
    Question() {
        super();
    }

    /**
     * Check if current question type, True if SingleChoice, False if Multiple Choice
     * @return
     */
    public boolean isMultipleChoice() {
        return isMultipleChoice;
    }

    /**
     * SET method
     * @param context - provided question(s)
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * GET method
     * @return return provided question(s)
     */
    public String getContext() {
        return context == null ? "Please set context first" : context;
    }
}

/** MULTIPLE CHOICE QUESTION CLASS */
class MultipleChoiceQuestion extends Question {
    Set<String> answers;

    /** Constructor */
    public MultipleChoiceQuestion() {
        answers = new HashSet<>();
        this.isMultipleChoice = true;
    }

    /**
     * SET method
     * @param answers provided possible answers
     */
    public void setAnswers(List<String> answers) {
        for(String answer : answers) {
            this.answers.add(answer);
        }
    }

    /**
     * GET method
     * @return answer(s)
     */
    public Set<String> getAnswers() {
        return answers;
    }

    /**
     * Method that check if student's answer is correct or not
     * @param studentAnswers list of answers from student
     * @return True if student's answer is correct, False otherwise
     */
    public boolean isCorrect(List<String> studentAnswers) {
        for(String studentAnswer : studentAnswers) {
            if(!this.answers.contains(studentAnswer))
                return false;
        }
        return true;
    }
}

/** SINGLE CHOICE QUESTION CLASS */
class SingleChoiceQuestion extends Question {
    String answer;

    /** Constructor */
    public SingleChoiceQuestion() {
        this.answer = null;
        this.isMultipleChoice = false;
    }

    /**
     * SET method
     * @param answer provided single possible answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * GET method
     * @return single answer
     */
    public String getAnswer() {
        return answer == null ? "Please set answer first" : answer;
    }

    /**
     * Method that check if student's answer is correct or not
     * @param studentAnswer single answer from student
     * @return True if student's answer is correct, False otherwise
     */
    public boolean isCorrect(String studentAnswer) {
        return this.answer.equals(studentAnswer);
    }
}