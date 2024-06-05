package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class IntroductionScreen extends JFrame {

    JButton startSimulationButton;
    JLabel welcomeText;
    JPanel mainPanel;
    JTextArea displayText;

    public void introductionScreen() {

        JLabel introductionLabel = new JLabel("Welcome to Team LANGGAM Simulator!");
        introductionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        introductionLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));

        displayText = new JTextArea();
        displayText.setText(
                (" Objective:\n" +
                        " - Teach the group how to implement MLFQ\n" +
                        " - Teach the group how to do a Multi processor setup\n" +
                        " - Visualize how the the algorithms process a queue in a multi processor setup\n" +
                        "  \n" +
                        "  \n" +
                        "Scheduling rules:\n" +
                        "   Round Robin (system):\n" +
                        " - uses a time quantum to execute the process within a fix amount of time \n" +
                        "   Sortest Remaining Time First (interactive):\n" +
                        " - gives the minimum waiting time for a given set of processes to reduce wating time \n" +
                        " - First Come First Serve(batch):\n " +
                        " - Provides high response time for the processes\n" +
                        " \n" +
                        "  \n" +
                        "Components: \n" +
                        " - Processes of the MLFQ system(RR),interactive(SRTF), batch(FCFS)\n" +
                        " - RealTime monitoring panel \n" +
                        " - ready queue \n" +
                        " - Clock \n" +
                        " - Gantt Chart \n" +
                        "  \n" +
                        "  \n" +
                        "Instructions: \n" +
                        "1. You are introduced to an input screen.\n" +
                        "2. Click the New option first to create a process number.\n" +
                        "3. Input Arrival Time, Burst Time, and Priority.\n" +
                        "4. Click the Add button to save the inputs entered.\n" +
                        "5. Click Delete if you wish to delete a process number.\n" +
                        "6. After the preparations, choose an algorithm from the three.\n" +
                        "7. Click Run and the results should be ready in a while."));

        displayText.setEditable(false); // Optional: Disable editing
        displayText.setLineWrap(true); // Enable line wrapping
        displayText.setWrapStyleWord(true); // Wrap text at word boundaries (optional)
        displayText.setBackground(getContentPane().getBackground()); // Match background color
        displayText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Optional padding
        displayText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false)); // Add border to the panel
        displayText.setFont(new Font("Arial", Font.PLAIN, 15));

        startSimulationButton = new JButton("Start Simulation");
        startSimulationButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Optional padding
        startSimulationButton.setOpaque(true);
        startSimulationButton.setFont(new Font("Arial", Font.PLAIN, 15));
        startSimulationButton.setBackground(Color.green);
        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new InputScreen().setVisible(true);
                    }
                });
                dispose();
            }
        });

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(255, 182, 193)); // Pastel red background
        mainPanel.add(introductionLabel, BorderLayout.NORTH);
        mainPanel.add(displayText, BorderLayout.CENTER);
        mainPanel.add(startSimulationButton, BorderLayout.SOUTH);

        this.setSize(900, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Team LANGGAM Introduction Screen");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\ant logo.png"));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.add(mainPanel);
    }

}
