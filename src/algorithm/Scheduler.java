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
                if (p.getType().equals("system"))
                    systemQueue.add(waitingQueue.remove(i));
                if (p.getType().equals("interactive"))
                    interactiveQueue.add(waitingQueue.remove(i));
                if (p.getType().equals("batch"))
                    batchQueue.add(waitingQueue.remove(i));
            }
        }

        // processor one algorithms
        if (processor1.getProcess() != null) {

            // system process (Round Robin algorithm)
            if (processor1.getProcess().getType().equals("system")) {
                if (processor1.getProcessRunningTime() >= SYSTEM_QUEUE_RR_QUANTUM) {
                    Process p = processor1.getProcess();
                    // demotes process to lower priority
                    p.demoteType();
                    interactiveQueue.add(p);
                    Process shortestProcess = findShortestProcess(interactiveQueue);
                    // adds to processor
                    processor1.setProcess(shortestProcess); // no need for nullable checks
                    interactiveQueue.remove(shortestProcess);
                }
            }

            // interactive process (Shortest Remaining Time First)
            else if (processor1.getProcess().getType().equals("interactive")) {
                if (!systemQueue.isEmpty()) {
                    Process p = processor1.getProcess();
                    processor1.setProcess(systemQueue.remove(0));
                    // demotes process to lowest priority
                    p.demoteType();
                    batchQueue.add(p);
                }
                Process shortestProcess = findShortestProcess(interactiveQueue);
                if (shortestProcess != null
                        && processor1.getProcess() != shortestProcess
                        && processor1.getProcess().getBurst() > shortestProcess.getBurst()) {
                    Process p = processor1.getProcess();
                    interactiveQueue.remove(shortestProcess);
                    processor1.setProcess(shortestProcess);
                    // demotes process to lowest priority
                    p.demoteType();
                    batchQueue.add(p);
                }
            }

            // batch process (First Come First Serve algorithm)
            else if (processor1.getProcess().getType().equals("batch")) {
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

        // for processes connected to the second processor
        if (processor2.getProcess() != null) {
            if (processor2.getProcess().getType().equals("system")) {
                if (processor2.getProcessRunningTime() >= SYSTEM_QUEUE_RR_QUANTUM) {
                    Process p = processor2.getProcess();
                    p.demoteType();
                    interactiveQueue.add(p);
                    Process shortestProcess = findShortestProcess(interactiveQueue);
                    processor2.setProcess(shortestProcess); // no need for nullable checks
                    interactiveQueue.remove(shortestProcess);
                }
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

        // removes processes from each priority type
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

    // overloading tickAll method
    public static void tickAll(Scheduler scheduler) {
        Scheduler.tickAll(scheduler, false);
    }

    public static void tickAll(Scheduler scheduler, boolean verbose) {
        while (!scheduler.getWaitingQueue().isEmpty()
                || !scheduler.getSystemQueue().isEmpty()
                || !scheduler.getInteractiveQueue().isEmpty()
                || !scheduler.getBatchQueue().isEmpty()
                || scheduler.getActiveProcess(1) != null
                || scheduler.getActiveProcess(2) != null) {
            scheduler.tick();
            if (verbose) {
                consoleOutputScheduler(scheduler);
            }
        }
    }

    // console output for the scheduler
    public static void consoleOutputScheduler(Scheduler scheduler) {
        System.err.println("========================================");
        System.out.println("Clock: " + scheduler.getClock());
        System.out.println("Waiting Queue: " + scheduler.getWaitingQueue());
        System.out.println("System Queue: " + scheduler.getSystemQueue());
        System.out.println("Interactive Queue: " + scheduler.getInteractiveQueue());
        System.out.println("Batch Queue: " + scheduler.getBatchQueue());
        System.err.println("Processor 1: " + scheduler.getActiveProcess(1));
        System.err.println("Processor 2: " + scheduler.getActiveProcess(2));
        System.out.println("Gantt Chart 1: " + scheduler.getGanttChart(1));
        System.out.println("Gantt Chart 2: " + scheduler.getGanttChart(2));
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