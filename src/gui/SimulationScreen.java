package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import algorithm.Scheduler;
import algorithm.Process;

public class SimulationScreen extends JFrame {

        // initializing lists
        private Scheduler scheduler;
        private List<Process> rtmProcessList;

        private boolean running = false;

        // initializing data members
        JButton play, stop;
        JLabel waitingQueue, highPriority, lowPriority, systemLabel, interactiveLabel, batchLabel, processor1Label,
                        processor2Label, realTimeLabel, finishedProcessLabel, ganttChartOne, ganttChartTwo, timerLabel,
                        processNameLabel, arrivalLabel, burstLabel, processTypeLabel;
        JPanel waitingPanel, systemPanel, interactivePanel, batchPanel, finishedPanel, processor1Panel, processor2Panel,
                        ganttChart1Panel, ganttChart2Panel, processNamePanel, arrivalPanel, burstPanel,
                        processTypePanel, timerPanel;
        JTextArea waitingQueueText, systemQueueText, interactiveText, batchText, processNameText, arrivalText,
                        burstText, processTypeText, processor1Text, processor2Text, ganttChart1Text, ganttChart2Text,
                        timerText;
        JScrollPane waitingScroll, systemScroll, interactiveScroll, batchScroll, processNameScroll, arrivalScroll,
                        burstScroll, processTypeScroll, processor1Scroll, processor2Scroll, ganttChart1Scroll,
                        ganttChart2Scroll;

        public SimulationScreen(List<Process> processes) {

                // adding and setting components
                scheduler = new Scheduler(processes);
                rtmProcessList = new ArrayList<>(processes);

                Font labelFonts = new Font("Tahoma", Font.PLAIN, 20);
                String sample = "";
                String rtmSample = "Process 1\nProcess 1\nProcess 1\nProcess 1\nProcess 1\nProcess 1\nProcess 1\nProcess 1\nProcess 1";

                waitingQueue = new JLabel("Waiting Queue");
                waitingQueue.setBounds(30, 10, 200, 50);
                waitingQueue.setFont(labelFonts);

                waitingQueueText = new JTextArea(1, 31);
                waitingQueueText.setFont(labelFonts);
                waitingQueueText.setText(sample);
                waitingQueueText.setEditable(false);

                waitingScroll = new JScrollPane(waitingQueueText) {
                        @Override
                        public void setLayout(LayoutManager layout) {
                                setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                                super.setLayout(layout);
                        }
                };

                waitingPanel = new JPanel();
                waitingPanel.setBackground(Color.green);
                waitingPanel.setOpaque(true);
                waitingPanel.setBounds(30, 50, 550, 70);
                waitingPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
                waitingPanel.add(waitingScroll);

                systemLabel = new JLabel("System (RR=5)");
                systemLabel.setBounds(180, 130, 200, 50);
                systemLabel.setFont(labelFonts);

                systemQueueText = new JTextArea(1, 22);
                systemQueueText.setFont(labelFonts);
                systemQueueText.setText(sample);
                systemQueueText.setEditable(false);

                systemScroll = new JScrollPane(systemQueueText) {
                        @Override
                        public void setLayout(LayoutManager layout) {
                                setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                                super.setLayout(layout);
                        }
                };

                systemPanel = new JPanel();
                systemPanel.setBackground(Color.green);
                systemPanel.setOpaque(true);
                systemPanel.setBounds(180, 170, 400, 70);
                systemPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
                systemPanel.add(systemScroll);

                highPriority = new JLabel("Highest Priority");
                highPriority.setBounds(30, 180, 200, 50);
                highPriority.setFont(labelFonts);

                interactiveLabel = new JLabel("Interactive (SRTF)");
                interactiveLabel.setBounds(180, 240, 200, 50);
                interactiveLabel.setFont(labelFonts);

                interactiveText = new JTextArea(1, 22);
                interactiveText.setFont(labelFonts);
                interactiveText.setText(sample);
                interactiveText.setEditable(false);

                interactiveScroll = new JScrollPane(interactiveText) {
                        @Override
                        public void setLayout(LayoutManager layout) {
                                setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                                super.setLayout(layout);
                        }
                };

                interactivePanel = new JPanel();
                interactivePanel.setBackground(Color.green);
                interactivePanel.setOpaque(true);
                interactivePanel.setBounds(180, 280, 400, 70);
                interactivePanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
                interactivePanel.add(interactiveScroll);

                batchLabel = new JLabel("Batch (FCFS)");
                batchLabel.setBounds(180, 350, 200, 50);
                batchLabel.setFont(labelFonts);

                batchText = new JTextArea(1, 22);
                batchText.setFont(labelFonts);
                batchText.setText(sample);
                batchText.setEditable(false);

                batchScroll = new JScrollPane(batchText) {
                        @Override
                        public void setLayout(LayoutManager layout) {
                                setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                                super.setLayout(layout);
                        }
                };

                batchPanel = new JPanel();
                batchPanel.setBackground(Color.green);
                batchPanel.setOpaque(true);
                batchPanel.setBounds(180, 390, 400, 70);
                batchPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
                batchPanel.add(batchScroll);

                lowPriority = new JLabel("Lowest Priority");
                lowPriority.setBounds(30, 400, 200, 50);
                lowPriority.setFont(labelFonts);

                realTimeLabel = new JLabel("Real Time Monitor for Processes");
                realTimeLabel.setFont(labelFonts);
                realTimeLabel.setBounds(30, 470, 400, 50);

                processNameLabel = new JLabel("Process");
                processNameLabel.setFont(labelFonts);
                processNameLabel.setBounds(30, 510, 400, 50);

                processNameText = new JTextArea(5, 8);
                processNameText.setFont(labelFonts);
                processNameText.setText(rtmSample);
                processNameText.setEditable(false);

                processNameScroll = new JScrollPane(processNameText);

                processNamePanel = new JPanel();
                processNamePanel.setBackground(Color.orange);
                processNamePanel.setOpaque(true);
                processNamePanel.setBounds(30, 550, 180, 150);
                processNamePanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
                processNamePanel.add(processNameScroll);

                arrivalLabel = new JLabel("Arrival Time");
                arrivalLabel.setFont(labelFonts);
                arrivalLabel.setBounds(215, 510, 400, 50);

                arrivalText = new JTextArea(5, 8);
                arrivalText.setFont(labelFonts);
                arrivalText.setText(rtmSample);
                arrivalText.setEditable(false);

                arrivalScroll = new JScrollPane(arrivalText);

                arrivalPanel = new JPanel();
                arrivalPanel.setBackground(Color.orange);
                arrivalPanel.setOpaque(true);
                arrivalPanel.setBounds(215, 550, 180, 150);
                arrivalPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
                arrivalPanel.add(arrivalScroll);

                burstLabel = new JLabel("Burst Time");
                burstLabel.setFont(labelFonts);
                burstLabel.setBounds(400, 510, 400, 50);

                burstText = new JTextArea(5, 8);
                burstText.setFont(labelFonts);
                burstText.setText(rtmSample);
                burstText.setEditable(false);

                burstScroll = new JScrollPane(burstText);

                burstPanel = new JPanel();
                burstPanel.setBackground(Color.orange);
                burstPanel.setOpaque(true);
                burstPanel.setBounds(400, 550, 180, 150);
                burstPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
                burstPanel.add(burstScroll);

                processTypeLabel = new JLabel("Process Type");
                processTypeLabel.setFont(labelFonts);
                processTypeLabel.setBounds(585, 510, 400, 50);

                processTypeText = new JTextArea(5, 8);
                processTypeText.setFont(labelFonts);
                processTypeText.setText(rtmSample);
                processTypeText.setEditable(false);

                processTypeScroll = new JScrollPane(processTypeText);

                processTypePanel = new JPanel();
                processTypePanel.setBackground(Color.orange);
                processTypePanel.setOpaque(true);
                processTypePanel.setBounds(585, 550, 180, 150);
                processTypePanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
                processTypePanel.add(processTypeScroll);

                processor1Label = new JLabel("Processor 1");
                processor1Label.setFont(labelFonts);
                processor1Label.setBounds(700, 180, 300, 50);

                processor1Text = new JTextArea(1, 31);
                processor1Text.setFont(labelFonts);
                processor1Text.setText(sample);
                processor1Text.setEditable(false);

                processor1Scroll = new JScrollPane(processor1Text) {
                        @Override
                        public void setLayout(LayoutManager layout) {
                                setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                                super.setLayout(layout);
                        }
                };

                processor1Panel = new JPanel();
                processor1Panel.setBackground(Color.green);
                processor1Panel.setOpaque(true);
                processor1Panel.setBounds(700, 220, 550, 70);
                processor1Panel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
                processor1Panel.add(processor1Scroll);

                processor2Label = new JLabel("Processor 2");
                processor2Label.setFont(labelFonts);
                processor2Label.setBounds(700, 290, 300, 50);

                processor2Text = new JTextArea(1, 31);
                processor2Text.setFont(labelFonts);
                processor2Text.setText(sample);
                processor2Text.setEditable(false);

                processor2Scroll = new JScrollPane(processor2Text) {
                        @Override
                        public void setLayout(LayoutManager layout) {
                                setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                                super.setLayout(layout);
                        }
                };

                processor2Panel = new JPanel();
                processor2Panel.setBackground(Color.green);
                processor2Panel.setOpaque(true);
                processor2Panel.setBounds(700, 330, 550, 70);
                processor2Panel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
                processor2Panel.add(processor2Scroll);

                ganttChartOne = new JLabel("Processor 1 Gantt Chart");
                ganttChartOne.setBounds(800, 430, 400, 50);
                ganttChartOne.setFont(labelFonts);

                ganttChart1Text = new JTextArea(1, 25);
                ganttChart1Text.setFont(labelFonts);
                ganttChart1Text.setText(sample);
                ganttChart1Text.setEditable(false);

                ganttChart1Scroll = new JScrollPane(ganttChart1Text) {
                        @Override
                        public void setLayout(LayoutManager layout) {
                                setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                                super.setLayout(layout);
                        }
                };

                ganttChart1Panel = new JPanel();
                ganttChart1Panel.setBackground(Color.green);
                ganttChart1Panel.setOpaque(true);
                ganttChart1Panel.setBounds(800, 470, 450, 70);
                ganttChart1Panel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
                ganttChart1Panel.add(ganttChart1Scroll);

                ganttChartTwo = new JLabel("Processor 2 Gantt Chart");
                ganttChartTwo.setBounds(800, 540, 300, 50);
                ganttChartTwo.setFont(labelFonts);

                ganttChart2Text = new JTextArea(1, 25);
                ganttChart2Text.setFont(labelFonts);
                ganttChart2Text.setText(sample);
                ganttChart2Text.setEditable(false);

                ganttChart2Scroll = new JScrollPane(ganttChart2Text) {
                        @Override
                        public void setLayout(LayoutManager layout) {
                                setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                                super.setLayout(layout);
                        }
                };

                ganttChart2Panel = new JPanel();
                ganttChart2Panel.setBackground(Color.green);
                ganttChart2Panel.setOpaque(true);
                ganttChart2Panel.setBounds(800, 580, 450, 70);
                ganttChart2Panel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
                ganttChart2Panel.add(ganttChart2Scroll);

                timerLabel = new JLabel("Timer");
                timerLabel.setFont(labelFonts);
                timerLabel.setBounds(1150, 10, 200, 50);

                timerText = new JTextArea(1, 5);
                timerText.setFont(labelFonts);
                timerText.setText("      " + "0");
                timerText.setEditable(false);

                timerPanel = new JPanel();
                timerPanel.setBackground(Color.green);
                timerPanel.setOpaque(true);
                timerPanel.setBounds(1120, 50, 110, 50);
                timerPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
                timerPanel.add(timerText);

                play = new JButton("Play");
                play.setOpaque(true);
                play.setBackground(Color.green);
                play.setBounds(700, 50, 110, 50);
                play.setFont(labelFonts);

                // runs the entire simulation
                play.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                running = true;
                                play.setEnabled(false);
                                stop.setEnabled(true);
                        }
                });

                stop = new JButton("Stop");
                stop.setOpaque(true);
                stop.setBackground(Color.red);
                stop.setBounds(900, 50, 110, 50);
                stop.setFont(labelFonts);
                stop.setEnabled(false);

                // stops the simulation
                stop.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                running = false;
                                play.setEnabled(true);
                                stop.setEnabled(false);
                        }
                });

                // adding all components to frame
                this.setSize(1300, 750);
                this.setTitle("Team Langgam CPU Scheduler");
                this.setLocationRelativeTo(null);
                this.setLayout(null);
                this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                this.setResizable(false);
                this.setVisible(true);
                this.add(waitingQueue);
                this.add(waitingPanel);
                this.add(highPriority);
                this.add(systemLabel);
                this.add(systemPanel);
                this.add(interactiveLabel);
                this.add(interactivePanel);
                this.add(batchLabel);
                this.add(batchPanel);
                this.add(lowPriority);
                this.add(realTimeLabel);
                this.add(processNameLabel);
                this.add(processNamePanel);
                this.add(arrivalLabel);
                this.add(arrivalPanel);
                this.add(burstLabel);
                this.add(burstPanel);
                this.add(processTypeLabel);
                this.add(processTypePanel);
                this.add(processor1Label);
                this.add(processor1Panel);
                this.add(processor2Label);
                this.add(processor2Panel);
                this.add(ganttChartOne);
                this.add(ganttChart1Panel);
                this.add(ganttChartTwo);
                this.add(ganttChart2Panel);
                this.add(timerLabel);
                this.add(timerPanel);
                this.add(play);
                this.add(stop);

                // tick and render per second
                new Timer(1000, new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                                if (running) {
                                        updateOutputs();
                                        scheduler.tick();
                                }
                        }
                }).start();
        }

        // updates text fields per 1000 ms
        private void updateOutputs() {
                timerText.setText(scheduler.getClock() + "");
                waitingQueueText.setText(scheduler.getWaitingQueue().toString());
                systemQueueText.setText(scheduler.getSystemQueue().toString());
                interactiveText.setText(scheduler.getInteractiveQueue().toString());
                batchText.setText(scheduler.getBatchQueue().toString());
                ganttChart1Text.setText(scheduler.getGanttChart(1).toString());
                ganttChart2Text.setText(scheduler.getGanttChart(2).toString());
                processor1Text.setText(scheduler.getActiveProcess(1) == null ? "IDLE"
                                : scheduler.getActiveProcess(1).toString());
                processor2Text.setText(scheduler.getActiveProcess(2) == null ? "IDLE"
                                : scheduler.getActiveProcess(2).toString());

                // sets the real time monitor processes
                String str = "";
                String type = "";
                String AT = "";
                String BT = "";
                for (Process p : rtmProcessList) {
                        str = str + p.getName() + "\n";
                        type = type + p.getName() + " | " + p.getType() + "\n";
                        AT = AT + p.getName() + " | " + p.getArrival() + "\n";
                        BT = BT + p.getName() + " | " + p.getBurst() + "\n";
                }
                processNameText.setText(str);
                processTypeText.setText(type);
                arrivalText.setText(AT);
                burstText.setText(BT);

        }

}
