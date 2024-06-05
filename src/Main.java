import java.util.Collections;
import java.util.List;
import algorithm.Process;
import algorithm.Scheduler;

import algorithm.ProcessFileHelper;

public class Main {
    

    public static void main(String[] args) {
        List<Process> processes = ProcessFileHelper.loadProcesses("src/processes.csv");
        Collections.sort(processes);
        Scheduler scheduler = new Scheduler(processes);
        Scheduler.tickAll(scheduler, true);
    }
}
