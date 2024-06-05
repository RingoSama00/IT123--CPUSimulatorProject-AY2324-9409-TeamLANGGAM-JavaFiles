package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Processor {

    // data members
    private Process currentProcess = null;
    private List<String> ganttChart = new ArrayList<>();
    private int processRunningTime = 0;

    // constructor
    public Processor(

    ) {
    }

    // setters and getters
    public List<String> getGanttChart() {
        return ganttChart;
    }

    // clock ticks as long as there is a process and its burst time is not 0
    public void tick() {
        if (currentProcess != null && currentProcess.getBurst() > 0)
            currentProcess.setBurst(currentProcess.getBurst() - 1);
        this.processRunningTime++;
    }

    public Process getProcess() {
        return this.currentProcess;
    }

    public void setProcess(Process process) {
        if (processRunningTime > 0) {
            String processName = "";
            // prints IDLE when no processes
            if (this.currentProcess == null)
                processName = "IDLE";
            // sets currently running process
            else
                processName = currentProcess.getName();
            ganttChart.add(processName + " " + processRunningTime);
        }

        this.currentProcess = process;
        this.processRunningTime = 0;
    }

    public int getProcessRunningTime() {
        return processRunningTime;
    }
}