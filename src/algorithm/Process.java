package algorithm;

public class Process implements Comparable<Process> {

    // data members
    private String name;
    private int arrival;
    private int burst;
    private String type;
    private static int current_id = 1;

    // constructor
    public Process(String name, int arrival, int burst, String type) {

        this.name = name;
        this.arrival = arrival;
        this.burst = burst;
        this.type = type;
        current_id++;

    }

    // setters and getters for the processes
    public Process(int arrival, int burst, String type) {
        this("P" + current_id, arrival, burst, type);
    }

    public String getName() {
        return name;
    }

    public int getArrival() {
        return arrival;
    }

    public int getBurst() {
        return burst;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }

    public String toString() {
        return "(" + name + " " + burst + ")";
    }

    public String getType() {
        return type;
    }

    // demotes the processes to a lower priority
    public void demoteType() {
        if (this.type.equals("system"))
            this.type = "interactive";
        else if (this.type.equals("interactive"))
            this.type = "batch";
    }

    // for when a process' burst time is 0
    public boolean isFinished() {
        return this.burst <= 0;
    }

    // compareTo overriding
    @Override
    public int compareTo(Process p) {
        if (this.arrival == p.arrival)
            return this.name.compareTo(p.getName());
        else
            return this.arrival - p.arrival;
    }

}