package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import algorithm.Process;
import algorithm.ProcessFileHelper;

public class InputScreen extends JFrame {

    // Creates new form InputScreen
    public InputScreen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        processTable = new JTable();
        burstText = new JTextField();
        arrivalLabel = new JLabel();
        processTypeList = new JComboBox<>();
        burstLabel = new JLabel();
        processTypeLabel = new JLabel();
        arrivalText = new JTextField();
        addButton = new JButton();
        preset1 = new JButton();
        deleteButton = new JButton();
        runButton = new JButton();
        preset2 = new JButton();
        preset3 = new JButton();
        processNameLabel = new JLabel();
        processNameText = new JTextField();
        clearButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Process Input Screen");

        processTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Process Name", "Process Type", "Arrival Time", "Burst Time"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jScrollPane1.setViewportView(processTable);

        burstText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                burstTextActionPerformed(evt);
            }
        });

        arrivalLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        arrivalLabel.setText("Arrival Time");

        processTypeList.setModel(new DefaultComboBoxModel<>(new String[] { "System", "Interactive", "Burst" }));
        processTypeList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                processTypeListActionPerformed(evt);
            }
        });

        burstLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        burstLabel.setText("Burst Time");

        processTypeLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        processTypeLabel.setText("Process Type");

        addButton.setBackground(new java.awt.Color(125, 197, 255));
        addButton.setText("Add Process");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        preset1.setBackground(new java.awt.Color(255, 204, 102));
        preset1.setText("Load Preset 1");
        preset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                preset1ActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(255, 51, 51));
        deleteButton.setText("Delete Selected");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        runButton.setBackground(new java.awt.Color(78, 241, 104));
        runButton.setText("RUN");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        preset2.setBackground(new java.awt.Color(255, 204, 102));
        preset2.setText("Load Preset 2");
        preset2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                preset2ActionPerformed(evt);
            }
        });

        preset3.setBackground(new java.awt.Color(255, 204, 102));
        preset3.setText("Load Preset 3");
        preset3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                preset3ActionPerformed(evt);
            }
        });

        processNameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        processNameLabel.setText("Process Name");

        clearButton.setBackground(new java.awt.Color(255, 51, 51));
        clearButton.setText("Clear Table");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        // code generated from NetBeans IDE
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(61, 61, 61)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(burstLabel, GroupLayout.DEFAULT_SIZE,
                                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(26, 26, 26))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(processTypeLabel,
                                                                        GroupLayout.PREFERRED_SIZE, 128,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addGap(52, 52, 52))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(arrivalLabel, GroupLayout.DEFAULT_SIZE,
                                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(
                                                                        LayoutStyle.ComponentPlacement.RELATED))))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(processNameLabel, GroupLayout.PREFERRED_SIZE, 174,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(processTypeList, 0, 145, Short.MAX_VALUE)
                                        .addComponent(arrivalText)
                                        .addComponent(burstText)
                                        .addComponent(processNameText))
                                .addGap(73, 73, 73)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(runButton, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                                        .addComponent(addButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE))
                                .addGap(34, 34, 34))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(clearButton, GroupLayout.DEFAULT_SIZE, 152,
                                                                Short.MAX_VALUE)
                                                        .addComponent(deleteButton, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 574,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(
                                                        layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(preset1, GroupLayout.DEFAULT_SIZE, 135,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(preset2, GroupLayout.DEFAULT_SIZE,
                                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(preset3, GroupLayout.DEFAULT_SIZE,
                                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(12, 12, 12))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 254,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 42,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(40, 40, 40)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(processNameLabel)
                                                        .addComponent(processNameText, GroupLayout.PREFERRED_SIZE, 30,
                                                                GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(arrivalText, GroupLayout.PREFERRED_SIZE, 30,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(arrivalLabel))
                                                .addGap(21, 21, 21)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(burstText, GroupLayout.PREFERRED_SIZE, 30,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(burstLabel))
                                                .addGap(18, 18, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(processTypeLabel)
                                                        .addComponent(processTypeList, GroupLayout.PREFERRED_SIZE, 35,
                                                                GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(143, 143, 143)
                                                .addComponent(addButton, GroupLayout.PREFERRED_SIZE, 51,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(runButton, GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 279,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(preset1, GroupLayout.PREFERRED_SIZE, 51,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(preset2, GroupLayout.PREFERRED_SIZE, 51,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(preset3, GroupLayout.PREFERRED_SIZE, 51,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(clearButton, GroupLayout.PREFERRED_SIZE, 55,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 55,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                                .addGap(72, 72, 72)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // get process information from the table
    private List<Process> getProcessesInTable() {
        List<Process> processes = new ArrayList<>();
        DefaultTableModel tblModel = (DefaultTableModel) processTable.getModel();
        for (int row = 0; row < tblModel.getRowCount(); row++) {
            String name = tblModel.getValueAt(row, 0).toString();
            String type = tblModel.getValueAt(row, 1).toString();
            int arrival = Integer.parseInt(tblModel.getValueAt(row, 2).toString());
            int burst = Integer.parseInt(tblModel.getValueAt(row, 3).toString());
            processes.add(new Process(name, arrival, burst, type));
        }
        return processes;
    }

    // calls the addProcessRow method to add processes each row count
    private void setProcessesInTable(List<Process> processes) {
        DefaultTableModel tblModel = (DefaultTableModel) processTable.getModel();
        tblModel.setRowCount(0);
        for (Process p : processes) {
            addProcessRow(p);
        }
    }

    // adds process information to the table model
    private void addProcessRow(Process process) {
        DefaultTableModel tblModel = (DefaultTableModel) processTable.getModel();
        tblModel.addRow(new String[] { process.getName(), process.getType().toLowerCase(), process.getArrival() + "",
                process.getBurst() + "" });
    }

    // adds text fields to process list
    private void addButtonActionPerformed(ActionEvent evt) {
        try {
            String name = processNameText.getText();
            String type = (String) processTypeList.getSelectedItem();
            int arrival = Integer.parseInt(arrivalText.getText());
            int burst = Integer.parseInt(burstText.getText());
            addProcessRow(new Process(name, arrival, burst, type));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter the correct values");
        }
    }

    // deletes a selected row in the table
    private void deleteButtonActionPerformed(ActionEvent evt) {
        int row = processTable.getSelectedRow();
        if (row != -1)
            ((DefaultTableModel) processTable.getModel()).removeRow(row);
    }

    // loads presets from 1-3 presets
    private void preset1ActionPerformed(ActionEvent evt) {
        List<Process> processes = ProcessFileHelper.loadProcesses("src/presets/processes1.csv");
        setProcessesInTable(processes);
    }

    private void preset2ActionPerformed(ActionEvent evt) {
        List<Process> processes = ProcessFileHelper.loadProcesses("src/presets/processes2.csv");
        setProcessesInTable(processes);
    }

    private void preset3ActionPerformed(ActionEvent evt) {
        List<Process> processes = ProcessFileHelper.loadProcesses("src/presets/processes3.csv");
        setProcessesInTable(processes);
    }

    // clears the table
    private void clearButtonActionPerformed(ActionEvent evt) {
        ((DefaultTableModel) processTable.getModel()).setRowCount(0);
    }

    // opens the simulation screen
    private void runButtonActionPerformed(ActionEvent evt) {
        new SimulationScreen(getProcessesInTable());
    }

    private void burstTextActionPerformed(ActionEvent evt) {// GEN-FIRST:event_burstTextActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_burstTextActionPerformed

    private void processTypeListActionPerformed(ActionEvent evt) {// GEN-FIRST:event_processTypeListActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_processTypeListActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton addButton;
    private JLabel arrivalLabel;
    private JTextField arrivalText;
    private JLabel burstLabel;
    private JTextField burstText;
    private JButton clearButton;
    private JButton deleteButton;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JButton preset1;
    private JButton preset2;
    private JButton preset3;
    private JLabel processNameLabel;
    private JTable processTable;
    private JTextField processNameText;
    private JLabel processTypeLabel;
    private JComboBox<String> processTypeList;
    private JButton runButton;
    // End of variables declaration//GEN-END:variables
}
