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

    public void introductionScreen() {

        JLabel introductionLabel = new JLabel("Welcome to Team LANGGAM Simulator!");
        introductionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        introductionLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));

        // Load the introduction image
        ImageIcon introImageIcon = new ImageIcon("src/Intro.png");
        JLabel introImageLabel = new JLabel(introImageIcon);

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

        // Create a panel to hold the image
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        imagePanel.add(introImageLabel, BorderLayout.CENTER);

        // Add the imagePanel to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Adjust this value as needed
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(255, 182, 193)); // Pastel red background
        mainPanel.add(introductionLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(startSimulationButton, BorderLayout.SOUTH);

        this.setSize(1920, 1080); // Set to a reasonable height for initial display
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Team LANGGAM Introduction Screen");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\ant logo.png"));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.add(mainPanel);
    }

}
