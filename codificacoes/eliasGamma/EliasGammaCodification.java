package codificacoes.eliasGamma;

import codificacoes.Decoder;
import codificacoes.Encoder;

import java.util.ArrayList;
import java.util.BitSet; 

public class EliasGammaCodification implements Encoder, Decoder {

    @Override
    public String decode(byte[] data) {
        // n = ler(o)
        // le 1
        // binario = le(n+1)
        // valorChar = int(binario)
        return "Implementar";
    }

    @Override
    public byte[] encode(byte [] data) {
        ArrayList<Byte> resultBytes = new ArrayList<>();
        byte resultByte = 0;
        int bitPosition = 0;

        addHeaderValues(resultBytes);

        for(byte b : data) {
            // b = valor inteiro do char
            System.out.println("valor char = " + b);
            System.out.println("byte = " + Integer.toBinaryString(b));
            System.out.println("tamanho do byte = " + Integer.toBinaryString(b).length());
            // tamanho do byte = k
            int byteLength = Integer.toBinaryString(b).length();
            // escreve k-1 zeros
            for (int i = 0; i < byteLength-1; i++) {
                if (bitPosition >= 8) {
                    //byte is complete, add to array and start over
                    resultBytes.add(resultByte);
                    resultByte = 0;
                    bitPosition = 0;
                }
                bitPosition++;
            }
            // escreve 1
            if (bitPosition >= 8) {
                //byte is complete, add to array and start over
                resultBytes.add(resultByte);
                resultByte = 0;
                bitPosition = 0;
            }
            int valToShift = 7-bitPosition;
            resultByte = (byte) (resultByte | (1<<valToShift));
            bitPosition++;
            // escreve binario(b)
            for (int i = 0; i < byteLength; i++) {
                if (bitPosition >= 8) {
                    //byte is complete, add to array and start over
                    resultBytes.add(resultByte);
                    resultByte = 0;
                    bitPosition = 0;
                }

                bitPosition++;
            }
        }
        System.out.println(resultBytes.toString());
        for(byte b : resultBytes) {
            System.out.println("byte = " + Integer.toBinaryString(b));
        }

        return data;
    }

    private void addHeaderValues(ArrayList<Byte> resultBytes){
        resultBytes.add((byte) 1);
        resultBytes.add((byte) 0);
    }
}
