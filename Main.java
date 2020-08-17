import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
 
public class Main {
 
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Encoder Demo");
 
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setCurrentDirectory(new File("./arquivos"));
 
        JButton btn1 = new JButton("Show Open Dialog");
        btn1.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                int retVal = fileChooser.showOpenDialog(frame);
                if (retVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(frame, selectedFile.getName());
                }
 
            }
        });
 
        Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(3, 1, 10, 10));
        pane.add(btn1);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}