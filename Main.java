import codificacoes.Coder;
import codificacoes.delta.DeltaCodification;
import codificacoes.eliasGamma.EliasGammaCodification;
import codificacoes.fibonacci.FibonacciCodification;
import codificacoes.golomb.GolombCodification;
import codificacoes.unaria.UnaryCodification;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import java.io.File;

import static codificacoes.CodingType.*;


import codificacoes.Encoder;
import codificacoes.Decoder;

public class Main {
 
    public static void main(String[] args) {
        Map<String, Integer> identifiers = new HashMap<String, Integer>();
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
            }
            if(op == -1) {
                System.out.println("Finalizar: " + op);
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

            if(retVal == 1) {
                System.out.println("Cancel/close: " + retVal);
                continue;
            } else if(retVal == 0) {
                System.out.println("Open: " + retVal);
            }
            
            // escolher codificador (0: Golomb, 1:Elias-Gamma, 2:Fibonacci, 3:Unária e 4:Delta)
            Object[] items = { Golomb.getName(), EliasGamma.getName(), Fibonacci.getName(), Unary.getName(), Delta.getName() };
            identifiers.put("Golomb", 0); identifiers.put("Elias-Gamma", 1); identifiers.put("Fibonacci", 2); identifiers.put("Unária", 3); identifiers.put("Delta", 4);
            String codingType = op == 1 ? "codificador" : "decodificador";
            Object selectedValue = JOptionPane.showInputDialog(null, "Escolha um " + codingType + ":", "Opção",
                JOptionPane.INFORMATION_MESSAGE, null, items, items[0]);

            if(selectedValue == null) {
                System.out.println("Close: " + selectedValue);
                continue;
            } else {
                //Encoder encoder = new Encoder(selectedFile,identifiers.get(selectedValue), divisor);
                System.out.println("Encoder: " + selectedFile.getPath() + " codificador: " + identifiers.get(selectedValue) + " divisor: " + divisor);

                Coder coder = null;
                int divisor = 0;

                switch (getValueByName((String) selectedValue)) {
                    case Golomb -> {
                        coder = new GolombCodification();
                        String inputValue = JOptionPane.showInputDialog("Insira o valor do divisor: ");
                        try {
                            divisor = Integer.parseInt(inputValue);
                        } catch (Exception e) {
                            //TODO: handle exception
                        }
                    }
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
            if(op == 1) {
                //Decoder decoder = new Decoder(selectedFile);
                System.out.println("Decoder: " + selectedFile.getPath());
            }
        }
        System.exit(0);
    }
}
