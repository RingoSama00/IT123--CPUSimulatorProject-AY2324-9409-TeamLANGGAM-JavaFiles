package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scheduler {

    // data members
    public final int SYSTEM_QUEUE_RR_QUANTUM = 3;

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
                    Collections.sort(systemQueue);
                }
                if (p.getType().equals("interactive")) {
                    interactiveQueue.add(waitingQueue.remove(i));
                    Collections.sort(interactiveQueue);
                }
                if (p.getType().equals("batch")) {
                    batchQueue.add(waitingQueue.remove(i));
                    Collections.sort(batchQueue);
                }
            }
        }

        // processor one algorithms
        if (processor1.getProcess() != null) {

            // system queue will only execute when process running time is greater
            // than time quantum
            if (processor1.getProcess().getType().equals("system")) {
                if (processor1.getProcessRunningTime() >= SYSTEM_QUEUE_RR_QUANTUM) {
                    Process p = processor1.getProcess();
                    p.demoteType(); // demote the current process
                    interactiveQueue.add(p); // add to lower queue

                    // find the shortest burst time to set current process to processor
                    Process shortestProcess = findShortestProcess(interactiveQueue);
                    processor1.setProcess(shortestProcess); // no need for nullable checks
                    interactiveQueue.remove(shortestProcess); // remove from interactive to set in processor
                }

                // interactive queue executes any interactive type process
            } else if (processor1.getProcess().getType().equals("interactive")) {
                if (!systemQueue.isEmpty()) {

                    // remove from queue and set to processor
                    Process p = processor1.getProcess();
                    processor1.setProcess(systemQueue.remove(0));
                    p.demoteType(); // demote queue to batch
                    batchQueue.add(p); // add to batch queue
                }

                // removes process from queue when current process burst time is greater than
                // the shortest process burst time
                Process shortestProcess = findShortestProcess(interactiveQueue);
                if (shortestProcess != null
                        && processor1.getProcess() != shortestProcess
                        && processor1.getProcess().getBurst() > shortestProcess.getBurst()) {
                    Process p = processor1.getProcess();
                    interactiveQueue.remove(shortestProcess);
                    processor1.setProcess(shortestProcess);
                    p.demoteType(); // demotes to batch queue
                    batchQueue.add(p); // adds to batch queue
                }

                // batch queue executes all remaining processes
            } else if (processor1.getProcess().getType().equals("batch")) {

                // add any remaining processes to batch
                if (!systemQueue.isEmpty()) {
                    Process p = processor1.getProcess();
                    processor1.setProcess(systemQueue.remove(0));
                    p.demoteType();
                    batchQueue.add(0, p);
                }
                if (!interactiveQueue.isEmpty()) {
                    Process p = processor1.getProcess();
                    processor1.setProcess(interactiveQueue.remove(0));
                    p.demoteType();
                    batchQueue.add(0, p);
                }
            }
        }

        // second processor will continue processing remaining processes until all
        // queues are empty and is a copy of the first processor
        if (processor2.getProcess() != null) {

            // system for second processor
            if (processor2.getProcess().getType().equals("system")) {
                if (processor2.getProcessRunningTime() >= SYSTEM_QUEUE_RR_QUANTUM) {
                    Process p = processor2.getProcess();
                    p.demoteType();
                    interactiveQueue.add(p);
                    Process shortestProcess = findShortestProcess(interactiveQueue);
                    processor2.setProcess(shortestProcess); // no need for nullable checks
                    interactiveQueue.remove(shortestProcess);
                }

                // interactive queue for second processor
            } else if (processor2.getProcess().getType().equals("interactive")) {
                if (!systemQueue.isEmpty()) {
                    Process p = processor2.getProcess();
                    processor2.setProcess(systemQueue.remove(0));
                    p.demoteType();
                    batchQueue.add(p);
                }
                Process shortestProcess = findShortestProcess(interactiveQueue);
                if (shortestProcess != null
                        && processor2.getProcess() != shortestProcess
                        && processor2.getProcess().getBurst() > shortestProcess.getBurst()) {
                    Process p = processor2.getProcess();
                    interactiveQueue.remove(shortestProcess);
                    processor2.setProcess(shortestProcess);
                    p.demoteType();
                    batchQueue.add(p);
                }

                // batch queue for second processor
            } else if (processor2.getProcess().getType().equals("batch")) {
                if (!systemQueue.isEmpty()) {
                    Process p = processor2.getProcess();
                    processor2.setProcess(systemQueue.remove(0));
                    p.demoteType();
                    batchQueue.add(0, p);
                }
                if (!interactiveQueue.isEmpty()) {
                    Process p = processor2.getProcess();
                    processor2.setProcess(interactiveQueue.remove(0));
                    p.demoteType();
                    batchQueue.add(0, p);
                }
            }
        }

        // checks for remaining processes
        if (processor1.getProcess() == null) {
            if (!systemQueue.isEmpty()) {
                processor1.setProcess(systemQueue.remove(0));
            } else if (!interactiveQueue.isEmpty()) {
                processor1.setProcess(interactiveQueue.remove(0));
            } else if (!batchQueue.isEmpty()) {
                processor1.setProcess(batchQueue.remove(0));
            }
        }
        if (processor2.getProcess() == null) {
            if (!systemQueue.isEmpty()) {
                processor2.setProcess(systemQueue.remove(0));
            } else if (!interactiveQueue.isEmpty()) {
                processor2.setProcess(interactiveQueue.remove(0));
            } else if (!batchQueue.isEmpty()) {
                processor2.setProcess(batchQueue.remove(0));
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