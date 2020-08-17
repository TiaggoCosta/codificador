import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import codificacoes.Encoder;
import codificacoes.Decoder;
 
public class Main {
 
    public static void main(String[] args) {
        Boolean isOn = true;

        while(isOn) {
            // escolher função (0-codificar 1-decodificar)
            Object[] functions = { "Codificar", "Decodificar" };
            int op = JOptionPane.showOptionDialog(null, "Escolha a função desejada: (Para encerrar feche a janela!)", "Função", 
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
    
            File selectedFile = null;
            int retVal = fileChooser.showOpenDialog(null);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(null, selectedFile.getName());
            }

            if(retVal == 0) {
                System.out.println("Open: " + retVal);
            } else if(retVal == 1) {
                System.out.println("Cancel/close: " + retVal);
                continue;
            } 
            
            // escolher codificador (0: Golomb, 1:Elias-Gamma, 2:Fibonacci, 3:Unária e 4:Delta)
            static Map<String, Integer> identifiers = new HashMap<String, Integer>();
            identifiers.put("Golomb", 0); identifiers.put("Elias-Gamma", 1); identifiers.put("Fibonacci", 2); identifiers.put("Unária", 3); identifiers.put("Delta", 4);
            Object[] items = { "Golomb", "Elias-Gamma", "Fibonacci", "Unária", "Delta" };
            Object selectedValue = JOptionPane.showInputDialog(null, "Escolha um codificador:", "Opção",
                JOptionPane.INFORMATION_MESSAGE, null, items, items[0]);
            
            int divisor = 0;
            // se golomb, informar o divisor

            if(selectedValue == null) {
                System.out.println("Close: " + selectedValue);
                continue;
            } else {
                System.out.println("Selected Value: " + selectedValue);
                if(op == 0) {
                    Encoder encoder = new Encoder(selectedFile,identifiers.get(selectedValue), divisor);
                }
                if(op == 1) {
                    Decoder decoder = new Decoder(selectedFile);
                }
            }
        }
        System.exit(0);
    }
}
