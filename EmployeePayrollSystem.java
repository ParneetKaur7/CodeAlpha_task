package blogs_program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollSystem {
    private List<Employee> employees;

    public EmployeePayrollSystem() {
        employees = new ArrayList<>();
        initializeUI();
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Employee Payroll System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new FlowLayout());

        JTextField nameField = new JTextField(15);
        JTextField salaryField = new JTextField(15);
        JButton addButton = new JButton("Add Employee");
        JButton calculateButton = new JButton("Calculate Total Salary");
        JTextArea outputArea = new JTextArea(20, 30);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double salary = Double.parseDouble(salaryField.getText());
                employees.add(new Employee(name, salary));
                nameField.setText("");
                salaryField.setText("");
                outputArea.append("Added: " + name + " with salary $" + salary + "\n");
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double totalSalary = calculateTotalSalary();
                outputArea.append("Total Salary: $" + totalSalary + "\n");
            }
        });

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Salary:"));
        panel.add(salaryField);
        panel.add(addButton);
        panel.add(calculateButton);
        panel.add(new JScrollPane(outputArea));
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private double calculateTotalSalary() {
        double totalSalary = 0.0;
        for (Employee employee : employees) {
            totalSalary += employee.getSalary();
        }
        return totalSalary;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeePayrollSystem());
    }

    static class Employee {
        private String name;
        private double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public double getSalary() {
            return salary;
        }
    }
}
