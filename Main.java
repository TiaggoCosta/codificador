import java.io.*;
import javax.swing.*;
 
public class Main {
 
    public static void main(String[] args) {
        Boolean isOn = true;

        while(isOn) {
            // escolher função (0-codificar 1-decodificar)
            int op = 0;  
        
            Object[] functions = { "Codificar", "Decodificar" };
            op = JOptionPane.showOptionDialog(null, "Escolha a função desejada: (Para encerrar feche a janela!)", "Função", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, functions, functions[0]);
            
            if(op == 0) {
                System.out.println("Codificar: " + op);
            } else if(op == 1) {
                System.out.println("Decodificar: " + op);
            } else {
                Object[] options = { "Sim, finalizar programa", "Não, desejo recomeçar" };
                int end = JOptionPane.showOptionDialog(null, "Deseja encerrar o programa?", "Finalizar",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                if(end == 0) {
                    isOn = false;
                    break;
                } else {
                    continue;
                }
            }
            
            // seleção de arquivo
            final JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(false);
    
            int retVal = fileChooser.showOpenDialog(null);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(null, selectedFile.getName());
            }

            if(retVal == 0) {
                System.out.println("Open: " + retVal);
            } else if(retVal == 1) {
                System.out.println("Cancel/close: " + retVal);
                continue;
            } 
            
            // escolher codificador (0: Golomb, 1:Elias-Gamma, 2:Fibonacci, 3:Unária e 4:Delta)
            Object[] items = { "Golomb", "Elias-Gamma", "Fibonacci", "Unária", "Delta" };
            Object selectedValue = JOptionPane.showInputDialog(null, "Escolha um codificador:", "Opção",
                JOptionPane.INFORMATION_MESSAGE, null, items, items[0]);

            if(selectedValue == null) {
                System.out.println("Close: " + selectedValue);
                continue;
            } else {
                System.out.println("Selected Value: " + selectedValue);
            }
        }
        System.exit(0);
    }
}
