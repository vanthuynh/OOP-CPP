package Simulator;

public class Student {
    private String id;
    private String name;

    /**
     * Constructor
     * @param id - student's unique id
     * @param name - student's name
     */
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get-ID method
     * @return student's id
     */
    public String getId() {
        return id;
    }

    /**
     * Get-Name method
     * @return student's name
     */
    public String getName() {
        return name;
    }
}