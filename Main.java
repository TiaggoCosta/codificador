import codificacoes.Coder;
import codificacoes.delta.DeltaCodification;
import codificacoes.eliasGamma.EliasGammaCodification;
import codificacoes.fibonacci.FibonacciCodification;
import codificacoes.golomb.GolombCodification;
import codificacoes.unaria.UnaryCodification;

import javax.swing.*;
import java.io.File;

import static codificacoes.CodingType.*;

public class Main {
 
    public static void main(String[] args) {
        Boolean isOn = true;

        while(isOn) {
            // escolher função (0-codificar 1-decodificar)
            int op;
        
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
            Object[] items = { Golomb.getName(), EliasGamma.getName(), Fibonacci.getName(), Unary.getName(), Delta.getName() };
            String codingType = op == 1 ? "codificador" : "decodificador";
            Object selectedValue = JOptionPane.showInputDialog(null, "Escolha um " + codingType + ":", "Opção",
                JOptionPane.INFORMATION_MESSAGE, null, items, items[0]);

            if(selectedValue == null) {
                System.out.println("Close: " + selectedValue);
                continue;
            } else {
                System.out.println("Selected Value: " + selectedValue);

                Coder coder = null;

                switch (getValueByName((String) selectedValue)) {
                    case Golomb -> coder = new GolombCodification();
                    case EliasGamma -> coder = new EliasGammaCodification();
                    case Fibonacci -> coder = new FibonacciCodification();
                    case Unary -> coder = new UnaryCodification();
                    case Delta -> coder = new DeltaCodification();
                }

                if (op == 1) {
                    coder.encode();
                } else {
                    coder.decode();
                }
            }
        }
        System.exit(0);
    }
}
