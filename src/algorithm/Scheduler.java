package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    // data members
    public final int SYSTEM_QUEUE_RR_QUANTUM = 5;

    private List<Process> waitingQueue = null;
    private List<Process> systemQueue = new ArrayList<>();
    private List<Process> interactiveQueue = new ArrayList<>();
    private List<Process> batchQueue = new ArrayList<>();
    private Processor processor1 = new Processor();
    private Processor processor2 = new Processor();
    private int clock = 0;

    // constructor
    public Scheduler(List<Process> processes) {
        waitingQueue = processes;
    }

    /*
     * MLFQ
     * 1st System Queue: Round Robin
     * 2nd Interactive Queue: Shortest Remaining Time First
     * 3rd Batch Queue: First Come First Serve
     */

    public void tick() {
        processor1.tick();
        if (processor1.getProcess() != null && processor1.getProcess().isFinished()) {
            processor1.setProcess(null);
        }
        processor2.tick();
        if (processor2.getProcess() != null && processor2.getProcess().isFinished()) {
            processor2.setProcess(null);
        }

        // sets the processes in the waiting queue
        for (int i = 0; i < waitingQueue.size(); i++) {
            Process p = waitingQueue.get(i);

            // removes process from respective type list
            if (p.getArrival() <= clock) {
                if (p.getType().equals("system")) {
                    systemQueue.add(waitingQueue.remove(i));
                }
                if (p.getType().equals("interactive")) {
                    interactiveQueue.add(waitingQueue.remove(i));
                }
                if (p.getType().equals("batch")) {
                    batchQueue.add(waitingQueue.remove(i));
                }

            }
        }

        // processor one algorithms
        if (processor1.getProcess() != null) {

            // system queue (round robin algorithm)
            if (processor1.getProcess().getType().equals("system")) {
                if (processor1.getProcessRunningTime() >= SYSTEM_QUEUE_RR_QUANTUM) {
                    Process p = processor1.getProcess();
                    p.demoteType();
                    interactiveQueue.add(p);
                    Process shortestProcess = findShortestProcess(interactiveQueue);
                    systemQueue.remove(shortestProcess);
                }

                // interactive queue (shortest time remaining first algorithm)
            } else if (processor1.getProcess().getType().equals("interactive")) {

                // demotes any remaining processes from previous queue
                if (!systemQueue.isEmpty()) {
                    Process p = processor1.getProcess();
                    processor1.setProcess(systemQueue.remove(0));
                    p.demoteType();
                }

                // find the shortest process and compare remaining burst time
                Process shortestProcess = findShortestProcess(interactiveQueue);
                if (shortestProcess != null
                        && processor1.getProcess() != shortestProcess
                        && processor1.getProcessRunningTime() < shortestProcess.getArrival()) {
                    Process p = processor2.getProcess();
                    p.demoteType();
                    batchQueue.add(p);

                    processor1.setProcess(shortestProcess);
                    processor2.setProcess(shortestProcess);
                    interactiveQueue.remove(shortestProcess);

                }

                // batch queue (first come first serve algorithm)
            } else if (processor1.getProcess().getType().equals("batch")) {
                // checks both queues for remaining processes and transfers it too batch queue
                if (!systemQueue.isEmpty()) {
                    Process p = processor1.getProcess();
                    processor1.setProcess(systemQueue.remove(0));
                    p.demoteType();
                }
                if (!interactiveQueue.isEmpty()) {
                    Process p = processor1.getProcess();
                    processor2.setProcess(p);
                    p.demoteType();
                    batchQueue.add(0, p);
                }
            }
        }

        // checks for remaining processes
        if (processor1.getProcess() == null) {
            if (!systemQueue.isEmpty()) {
                processor1.setProcess(systemQueue.remove(0));
            } else if (!batchQueue.isEmpty()) {
                processor1.setProcess(batchQueue.remove(0));
            }
        }
        if (processor2.getProcess() == null) {
            if (!interactiveQueue.isEmpty()) {
                processor2.setProcess(interactiveQueue.remove(0));
            }
        }

        clock++;
    }

    // compares processes and gets the shortest burst time
    private static Process findShortestProcess(List<Process> processes) {
        Process shortest = null;
        if (processes.size() != 0)
            shortest = processes.get(0);
        for (Process p : processes) {
            if (p.getBurst() < shortest.getBurst())
                shortest = p;
        }
        return shortest;
    }

    // setters and getters
    public List<Process> getWaitingQueue() {
        return waitingQueue;
    }

    public List<Process> getSystemQueue() {
        return systemQueue;
    }

    public List<Process> getInteractiveQueue() {
        return interactiveQueue;
    }

    public List<Process> getBatchQueue() {
        return batchQueue;
    }

    public int getClock() {
        return clock - 1;
    }

    // set processes to designated processors and gantt charts
    public Process getActiveProcess(int processor) {
        if (processor == 1)
            return processor1.getProcess();
        if (processor == 2)
            return processor2.getProcess();
        return null;
    }

    public List<String> getGanttChart(int processor) {
        if (processor == 1)
            return processor1.getGanttChart();
        if (processor == 2)
            return processor2.getGanttChart();
        return new ArrayList<String>();
    }
}