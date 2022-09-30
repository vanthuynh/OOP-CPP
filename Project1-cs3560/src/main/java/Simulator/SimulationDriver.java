package Simulator;

import java.util.*;

public class SimulationDriver {
    public static void main(String[] args) {
        /* Generate students randomly */
        Random rand = new Random();

        Student student1 = new Student(Integer.toString(rand.nextInt(1000)), "Luke");
        Student student2 = new Student(Integer.toString(rand.nextInt(1000)), "Vader");
        Student student3 = new Student(Integer.toString(rand.nextInt(1000)), "Obiwan");
        Student student4 = new Student(Integer.toString(rand.nextInt(1000)), "Cassian");
        Student student5 = new Student(Integer.toString(rand.nextInt(1000)), "Chewbacca");
        Student student6 = new Student(Integer.toString(rand.nextInt(1000)), "Han Solo");


        /* Configure question #1 */
        MultipleChoiceQuestion question1 = new MultipleChoiceQuestion();
        question1.setContext("1. Which are correct main features of Object Oriented Programming?");

        question1.setAnswers(new ArrayList<String>(){
            {
                add("Inheritance ");
                add("Polymorphism");
                add("Abstraction ");
            }
        });

        System.out.println(question1.getContext());
        displayChoices("Inheritance","Procedural","Polymorphism","Abstraction", question1.isMultipleChoice);

        /* Have students vote on question #1 */
        MCVS round1 = new MCVS(question1);

        System.out.println();
        round1.submit(student1, new ArrayList<String>(Arrays.asList("Polymorphism","Abstraction")));
        round1.submit(student4, new ArrayList<String>(Arrays.asList("Inheritance","Abstraction")));
        round1.submit(student1, new ArrayList<String>(Arrays.asList("Inheritance","Abstraction","Polymorphism")));
        round1.submit(student2, new ArrayList<String>());
        round1.submit(student3, new ArrayList<String>(Arrays.asList("Inheritance","Polymorphism","Procedural")));
        round1.submit(student5, new ArrayList<String>(Arrays.asList("Inheritance","Polymorphism","Abstraction")));

        System.out.println();
        round1.isValid(student1);
        round1.isValid(student2);
        round1.isValid(student3);
        round1.isValid(student4);

        /* Print stats #1 */
        round1.printStats();


        /* Configure question #2 */
        SingleChoiceQuestion question2 = new SingleChoiceQuestion();
        question2.setContext("2. Who created the time machine in Avengers Endgame?");

        question2.setAnswer("Ironman");

        System.out.println(question2.getContext());
        displayChoices("Smart Hulk","Ironman","Antman","Vision", question2.isMultipleChoice);

        /* Have students vote on question #2 */
        SCVS round2 = new SCVS(question2);

        System.out.println();
        round2.submit(student3,"Vision" );
        round2.submit(student2,"Smart Hulk" );
        round2.submit(student1,"Vision" );
        round2.submit(student4, "Antman");
        round2.submit(student3,"Ironman" );
        round2.submit(student5,"Ironman" );
        round2.submit(student1,"Vision" );

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
        System.out.println("A - " + str1);
        System.out.println("B - " + str2);
        System.out.println("C - " + str3);
        System.out.println("D - " + str4);

    }
}
