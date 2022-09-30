package Simulator;

import java.util.*;

public class SimulationDriver {
    public static void main(String[] args) {
        /* Generate students randomly */
        Random rand = new Random();

        Student student1 = new Student(Integer.toString(rand.nextInt(1000)), "David");
        Student student2 = new Student(Integer.toString(rand.nextInt(1000)), "Kate");
        Student student3 = new Student(Integer.toString(rand.nextInt(1000)), "John");
        Student student4 = new Student(Integer.toString(rand.nextInt(1000)), "Mary");
        Student student5 = new Student(Integer.toString(rand.nextInt(1000)), "Joe");


        /* Configure question #1 */
        MultipleChoiceQuestion question1 = new MultipleChoiceQuestion();
        question1.setContext("How many ways to drive to CPP ?");

        question1.setAnswers(new ArrayList<String>(){
            {
                add("1");
                add("3");
                add("7");
            }
        });

        System.out.println(question1.getContext());
        displayChoices("1","3","4","7", question1.isMultipleChoice);

        /* Have students vote on question #1 */
        MCVS round1 = new MCVS(question1);

        System.out.println();
        round1.submit(student1, new ArrayList<String>(Arrays.asList("4","7")));
        round1.submit(student4, new ArrayList<String>(Arrays.asList("1","7")));
        round1.submit(student1, new ArrayList<String>(Arrays.asList("1","3","7")));
        round1.submit(student2, new ArrayList<String>());
        round1.submit(student3, new ArrayList<String>(Arrays.asList("1","7","3")));

        System.out.println();
        round1.isValid(student1);
        round1.isValid(student2);
        round1.isValid(student3);
        round1.isValid(student4);

        /* Print stats #1 */
        round1.printStats();


        /* Configure question #2 */
        SingleChoiceQuestion question2 = new SingleChoiceQuestion();
        question2.setContext("What's the mascot for CPP?");

        question2.setAnswer("Horse");

        System.out.println(question2.getContext());
        displayChoices("Elephant","Anteater","Horse","Bear", question2.isMultipleChoice);

        /* Have students vote on question #2 */
        SCVS round2 = new SCVS(question2);

        System.out.println();
        round2.submit(student3,"Anteater" );
        round2.submit(student2,"Bear" );
        round2.submit(student1,"Elephant" );
        round2.submit(student4, "Anteater");
        round2.submit(student3,"Horse" );

        System.out.println();
        round2.isValid(student1);
        round2.isValid(student2);
        round2.isValid(student3);
        round2.isValid(student4);

        /* Print stats #2 */
        round2.printStats();

    }

    public static void  displayChoices(String str1, String str2, String str3, String str4, boolean isMultipleChoice) {
        System.out.println(isMultipleChoice ? "Please choose multiple answers if needed." : "Please choose one option only.");
        System.out.println("- " + str1);
        System.out.println("- " + str2);
        System.out.println("- " + str3);
        System.out.println("- " + str4);

    }
}
