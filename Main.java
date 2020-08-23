import codificacoes.CodingType;
import codificacoes.Encoder;
import codificacoes.delta.DeltaCodification;
import codificacoes.eliasGamma.EliasGammaCodification;
import codificacoes.fibonacci.FibonacciCodification;
import codificacoes.golomb.GolombCodification;
import codificacoes.unaria.UnaryCodification;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static codificacoes.CodingType.*;

public class Main {

    public static void main(String[] args) {
        boolean isOn = true;

        while (isOn) {
            // escolher função (0-codificar 1-decodificar)
            Object[] functions = {"Codificar", "Decodificar"};
            int op = JOptionPane.showOptionDialog(null, "Escolha a função desejada: (Para encerrar feche a janela!)", "Função",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, functions, functions[0]);

            if (op == 0) {
                System.out.println("Codificar: " + op);
            } else if (op == 1) {
                System.out.println("Decodificar: " + op);
            }
            if (op == -1) {
                System.out.println("Finalizar: " + op);
                Object[] options = {"Sim, finalizar programa", "Não, desejo recomeçar"};
                int end = JOptionPane.showOptionDialog(null, "Deseja encerrar o programa?", "Finalizar",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                if (end == 0) {
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

            if (retVal == 1) {
                System.out.println("Cancel/close: " + retVal);
                continue;
            } else if (retVal == 0) {
                System.out.println("Open: " + retVal);
            }

            if (op == 1) {
                //Decoder decoder = new Decoder(selectedFile);
                //decoder.decode();
                System.out.println("Decodificação do arquivo: " + selectedFile.getPath());
            } else {
                // escolher codificador (0: Golomb, 1:Elias-Gamma, 2:Fibonacci, 3:Unária e 4:Delta)
                Object[] items = {Golomb.getName(), EliasGamma.getName(), Fibonacci.getName(), Unary.getName(), Delta.getName()};
                Object selectedValue = JOptionPane.showInputDialog(null, "Escolha um codificador:", "Opção",
                        JOptionPane.INFORMATION_MESSAGE, null, items, items[0]);

                if (selectedValue == null) {
                    System.out.println("Close: " + selectedValue);
                    continue;
                } else {
                    final CodingType selectedCodingType = getValueByName((String) selectedValue);
                    Encoder encoder = null;

                    switch (selectedCodingType) {
                        case Golomb:
                            boolean invalidDivisor = true;
                            String inputValue = null;

                            while (invalidDivisor) {
                                inputValue = JOptionPane.showInputDialog("Insira o valor do divisor: ");

                                if (inputValue == null) {
                                    break;
                                }

                                try {
                                    int divisor = Integer.parseInt(inputValue);
                                    encoder = new GolombCodification(divisor);
                                    System.out.println("Divisor: " + divisor);
                                    invalidDivisor = false;
                                } catch (Exception e) {
                                    //TODO: handle exception
                                }
                            }

                            if (inputValue == null) {
                                continue;
                            }
                            break;
                        case EliasGamma:
                            encoder = new EliasGammaCodification();
                            break;
                        case Fibonacci:
                            encoder = new FibonacciCodification();
                            break;
                        case Unary:
                            encoder = new UnaryCodification();
                            break;
                        case Delta:
                            encoder = new DeltaCodification();
                            break;
                    }

                    System.out.println("Codificação do arquivo: " + selectedFile.getPath());
                    System.out.println("Codificador: " + selectedCodingType.getName());
                    //TODO: class or method to read and write the files
                    try {
                        byte[] data = Files.readAllBytes(selectedFile.toPath());
                        byte[] result = encoder.encode(data);
                        final String ext = ".cod";
                        String filePath = selectedFile.getPath();
                        int extIndex = filePath.lastIndexOf(".");
                        String newPath = (extIndex > -1 ? filePath.substring(0, extIndex) : filePath) + ext;
                        System.out.println("resultado: " + Arrays.toString(result));
                        Files.write(Paths.get(newPath), result);
                        JOptionPane.showMessageDialog(null, "Codificação concluída com sucesso");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.exit(0);
    }
}

