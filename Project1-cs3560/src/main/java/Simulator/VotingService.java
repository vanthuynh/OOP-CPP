package Simulator;

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

public class VotingService {
    Hashtable<String,Integer> stats = new Hashtable<>(); // frequency map to keep score
    List<Student> students = new ArrayList<>(); // list of student objects
    Question question;

    /** Constructor */
    VotingService() {
        super();
    }

    /**
     * Method to add student object to existing list
     * @param student - existing student object
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Method to count frequency of answers
     * @param answer - answer that is provided
     */
    public void addStat(String answer) {
        this.stats.put(answer, this.stats.getOrDefault(answer,0) + 1);
    }

    /**
     * Display result method
     */
    public void printStats() {
        System.out.println();
        System.out.println("Stats outcome...");
        System.out.println("============================================");
        for(String answer : stats.keySet()) {
            int total = stats.get(answer);
            System.out.println(answer + " : " + prettifyStats(total) + "(" + total + ")");
        }
        System.out.println("============================================");
        System.out.println();
    }

    /**
     * Bar graph display format
     * @param num - count of specific answer
     * @return  string of "num" of asterisks
     */
    private String prettifyStats(Integer num) {
        String result = "";
        for (int i = 0; i < num; ++i) {
            result += "* ";
        }
        return result;
    }
}

/*
 * MC VOTING SERVICE
 */
class MCVS extends VotingService {
    Hashtable<Student, ArrayList<String>> studentAnswerMapping;
    MultipleChoiceQuestion question;

    /** Constructor */
    public MCVS(MultipleChoiceQuestion question) {
        this.question = question;
        this.studentAnswerMapping = new Hashtable<>();
    }

    /**
     * method that update newest answers and stats
     * @param student - student object
     * @param studentAnswers - provided answers from student
     */
    public void submit(Student student, ArrayList<String> studentAnswers) {
        System.out.println(student.getName() + " selected " + studentAnswers);
        // update student's newest answers
        if(studentAnswerMapping.get(student) != null) {
            List<String> oldAnswers = studentAnswerMapping.get(student);
            for(String oldAnswer : oldAnswers) {
                this.stats.put(oldAnswer, this.stats.getOrDefault(oldAnswer,0) - 1);
            }
            System.out.println(student.getName() + "'s newest answers are updated.");
        }
        else System.out.println(student.getName() + "'s first attempt is recorded.");

        this.studentAnswerMapping.put(student,studentAnswers);

        // update answer count
        for(String answer : studentAnswers)
            addStat(answer);

    }

    /**
     * method that check correctness of student's answer
     * @param student - student object
     * @return True if student answers correctly, False otherwise
     */
    public boolean isValid(Student student) {
        List<String> studentAnswers = studentAnswerMapping.get(student);

        if(studentAnswers.size() == 0) {
            System.out.println(student.getName() + " didn't provide any answers.");
            return false;
        }
        else if (studentAnswers.size() != question.getAnswers().size()) {
            System.out.println(student.getName() + "'s answers are not correct.");
            return false;
        }


        if(studentAnswers != null && question.isCorrect(studentAnswers)) {
            System.out.println(student.getName() + " is correct.");
            return true;
        }

        return false;
    }
}

/*
 * SC VOTING SERVICE
 */
class SCVS extends VotingService {
    Hashtable<Student, String> studentAnswerMapping;
    SingleChoiceQuestion question;

    /** Constructor */
    public SCVS(SingleChoiceQuestion question) {
        this.question = question;
        this.studentAnswerMapping = new Hashtable<>();
    }

    /**
     * method that update latest answers and stats
     * @param student - student object
     * @param studentAnswer - provided answer from student
     */
    public void submit(Student student, String studentAnswer) {
        System.out.println(student.getName() + " selected " + studentAnswer);
        // update student's newest answers
        if(studentAnswerMapping.get(student) != null) {
            String oldAnswer = studentAnswerMapping.get(student);
            this.stats.put(oldAnswer, this.stats.getOrDefault(oldAnswer,0) - 1);

            System.out.println(student.getName() + "'s newest answer is updated.");
        }
        else System.out.println(student.getName() + "'s first attempt is recorded.");
        this.studentAnswerMapping.put(student,studentAnswer);

        // update answer count
        addStat(studentAnswer);
    }

    /**
     * method that check correctness of student's answer
     * @param student - student object
     * @return True if student answers correctly, False otherwise
     */
    public boolean isValid(Student student) {
        String studentAnswer = studentAnswerMapping.get(student);

        if(studentAnswer.length() == 0) {
            System.out.println(student.getName() + " didn't provide any answers.");
            return false;
        }

        if(studentAnswer != null && question.isCorrect(studentAnswer)) {
            System.out.println(student.getName() + " is correct.");
            return true;
        }
        else System.out.println(student.getName() + "'s answer is not correct.");

        return false;
    }
}