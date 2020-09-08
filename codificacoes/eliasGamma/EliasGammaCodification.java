package codificacoes.eliasGamma;

import codificacoes.Decoder;
import codificacoes.Encoder;

import java.util.ArrayList;
import java.util.BitSet; 

public class EliasGammaCodification implements Encoder, Decoder {

    @Override
    public String decode(byte[] data) {
        ArrayList<Integer> decoded = new ArrayList<>();
        int count = 0;
        boolean charCodeArea = false;
        String binario = "0";
        // count = ler(zeros)
        for(int index = 2; index < data.length; index++) {
            BitSet bits = BitSet.valueOf(new long[] { data[index] });
            
            for(int i = 7; i >= 0; i--) {
                if(!charCodeArea) {
                    System.out.println("Bit on index: "+i+" = "+ bits.get(i));
                    if(bits.get(i) == false) {
                        count ++;
                    } else { 
                        // le 1
                        System.out.println("Found stop bit 1");
                        System.out.println("Value of counter is: "+count);
                        charCodeArea = true;
                    }
                } else {
                    System.out.println("Bit on index: "+i+" = "+ bits.get(i));
                    System.out.println("Counter: " + count);
                    System.out.println("Binario: " + binario);
                    // binario = le(n+1)
                    if(count > -1) {
                        if(bits.get(i)){
                            binario.concat("1");
                        } else {
                            binario.concat("0");
                        }
                        count--;
                    } else {
                        // valorChar = int(binario)
                        System.out.println("Binario final: " + binario);
                        decoded.add(Integer.valueOf(binario));
                        count = 0;
                        charCodeArea = false;
                        binario = "0";
                    }
                }
            }
        }
        
        StringBuilder builder = new StringBuilder("");
        for(int i = 0; i < decoded.size(); i++){
            System.out.println("decoded: " + decoded.get(i));
            System.out.println("Back to string: " + Integer.toString(decoded.get(i)));
            int ascii = decoded.get(i);
            char ch = (char) ascii;
            builder.append(ch);
        }
        System.out.println("returning: " + builder);
        return builder.toString();
    }

    @Override
    public byte[] encode(byte [] data) {
        ArrayList<Byte> resultBytes = new ArrayList<>();
        byte resultByte = 0;
        int bitPosition = 0;

        addHeaderValues(resultBytes);
        ArrayList<Integer> bits = new ArrayList<>();
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
                bits.add(0);
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
            bits.add(1);
            // escreve binario(b)
            for (int i = 0; i < byteLength; i++) {
                if (bitPosition >= 8) {
                    //byte is complete, add to array and start over
                    resultBytes.add(resultByte);
                    resultByte = 0;
                    bitPosition = 0;
                }

                String bitsOfRest = Integer.toBinaryString(b);
                if(bitsOfRest.charAt(i) == '1') {
                    valToShift = 7-bitPosition;
                    resultByte = (byte) (resultByte | (1<<valToShift));
                    bits.add(1);
                } else {
                    bits.add(0);
                }

                bitPosition++;
            }
        }

        //add residual bits of non complete byte
        if (bitPosition > 0) {
            resultBytes.add(resultByte);
        }

        byte[] result = new byte[resultBytes.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = resultBytes.get(i);
        }

        System.out.println(resultBytes.toString());
        for(byte b : resultBytes) {
            System.out.println("byte = " + Integer.toBinaryString(b));
        }
        System.out.println(bits.toString());
        return result;
    }

    private void addHeaderValues(ArrayList<Byte> resultBytes){
        resultBytes.add((byte) 1);
        resultBytes.add((byte) 0);
    }
}
