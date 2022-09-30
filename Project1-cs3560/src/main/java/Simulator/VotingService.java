package Simulator;

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

public class VotingService {
    Hashtable<String,Integer> stats = new Hashtable<>(); // frequency map to keep score
    List<Student> students = new ArrayList<>(); // list of student objects
    Question question;

    VotingService() {
        super();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addStat(String answer) {
        this.stats.put(answer, this.stats.getOrDefault(answer,0) + 1);
    }

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

    public MCVS(MultipleChoiceQuestion question) {
        this.question = question;
        this.studentAnswerMapping = new Hashtable<>();
    }

    public void submit(Student student, ArrayList<String> studentAnswers) {
        // this guarantees latest answers are recorded
        // and in sync with stats
        System.out.println(student.getName() + " selected " + studentAnswers);

        if(studentAnswerMapping.get(student) != null) {
            List<String> oldAnswers = studentAnswerMapping.get(student);
            for(String oldAnswer : oldAnswers) {
                this.stats.put(oldAnswer, this.stats.getOrDefault(oldAnswer,0) - 1);
            }

            System.out.println(student.getName() + "'s newest answers are updated.");
        }
        else System.out.println(student.getName() + "'s first attempt is recorded.");

        this.studentAnswerMapping.put(student,studentAnswers);
        for(String answer : studentAnswers)
            addStat(answer);

    }

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

    public SCVS(SingleChoiceQuestion question) {
        this.question = question;
        this.studentAnswerMapping = new Hashtable<>();
    }

    public void submit(Student student, String studentAnswer) {
        // this guarantees latest answer is recorded
        // and in sync with stats
        System.out.println(student.getName() + " selected " + studentAnswer);
        if(studentAnswerMapping.get(student) != null) {
            String oldAnswer = studentAnswerMapping.get(student);
            this.stats.put(oldAnswer, this.stats.getOrDefault(oldAnswer,0) - 1);

            System.out.println(student.getName() + "'s newest answer is updated.");
        }
        else System.out.println(student.getName() + "'s first attempt is recorded.");

        this.studentAnswerMapping.put(student,studentAnswer);
        addStat(studentAnswer);
    }

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