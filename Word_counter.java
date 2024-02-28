package blogs_program;
import javax.swing.*;
import java.awt.event.*;

public class Word_counter extends JFrame implements ActionListener {

    private JTextArea textArea;
    private JButton countButton;
    private JLabel countLabel;

    public Word_counter() {
        super("Word Counter");

        textArea = new JTextArea(10, 30);
        countButton = new JButton("Count");

        countLabel = new JLabel("Word count: 0");
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(textArea));
        panel.add(countButton);
        panel.add(countLabel);
        add(panel);

        countButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String text = textArea.getText();

        int count = text.split("\\s+").length;
        countLabel.setText("Word count: " + count);
    }

    public static void main(String[] args) {
        new Word_counter();
    }
}