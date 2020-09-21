package codificacoes.eliasGamma;

import codificacoes.Decoder;
import codificacoes.Encoder;

import java.util.ArrayList;
import java.util.BitSet; 

public class EliasGammaCodification implements Encoder, Decoder {

    @Override
    public byte[] decode(byte[] data) {
        ArrayList<Byte> decoded = new ArrayList<>();
        BitSet byteSuffix = new BitSet();
        int count = 0;
        boolean charCodeArea = false;
        boolean isZero = false;
        // count = ler(zeros)
        for(int index = 2; index < data.length; index++) {
            BitSet bits = BitSet.valueOf(new long[] { data[index] });

            for(int i = 7; i >= 0; i--){
                if(charCodeArea) {
                    System.out.println("Bit on index: "+i+" == "+ bits.get(i));
                    System.out.println("Counter: " + count);
                    
                    // binario = le(n+1)
                    if(isZero) {
                        if(bits.get(i)) {
                            decoded.add((byte)1);
                        } else {
                            decoded.add((byte)0);
                        }
                        System.out.println("byte convertido => " + Integer.toBinaryString((byte)0));
                        charCodeArea = false;
                        isZero = false;
                        byteSuffix.clear();
                        count = 0;
                        continue;
                    }
                    if(count > -1) {
                            System.out.println(">0 "+isZero);
                            if(bits.get(i)) {
                                byteSuffix.set(count);
                            } 
                        
                        System.out.println("Binario: " + byteSuffix.toString());
                        if(count == 0) {
                            // valorChar = int(binario)
                            byte[] converted = byteSuffix.toByteArray();
                            System.out.println("byte convertido = " + Integer.toBinaryString(converted[0]));
                            System.out.println("Binario final: " + byteSuffix.toString());
                            decoded.add(converted[0]);
                            count = 0;
                            charCodeArea = false;
                            byteSuffix.clear();
                            continue;
                        }
                        count--;
                    } 
                } else {
                    System.out.println("Bit on index: "+i+" = "+ bits.get(i));
                if(!bits.get(i)) {
                    count ++;
                } else {
                    // le 1
                    System.out.println("Found stop bit 1");
                    System.out.println("Value of counter is: "+count);
                    charCodeArea = true;
                    if(count == 0) {
                        isZero = true;
                    }
                }
                }
                
                
            }
        }

        byte[] decodedBytes = new byte[decoded.size()];
        for (int i = 0; i < decodedBytes.length; i++) {
            int ascii = decoded.get(i);
            decodedBytes[i] = (byte)ascii;
            System.out.println("byte => " + ascii);
        }
        
        return decodedBytes;
    }

    @Override
    public byte[] encode(byte [] data) {
        ArrayList<Byte> resultBytes = new ArrayList<>();
        byte resultByte = 0;
        int bitPosition = 0;

        addHeaderValues(resultBytes);
        ArrayList<Integer> bits = new ArrayList<>();
        byte um = (byte)1;
        byte dois = (byte)2;
        byte zero = (byte)0;
        byte exclamacao = (byte)33;
        byte[] data1 = new byte[8];
        data1[0] = um;
        data1[1] = dois;
        data1[2] = zero;
        data1[3] = exclamacao;
        data1[4] = zero;
        data1[5] = um;
        data1[6] = zero;
        data1[7] = exclamacao;
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
                    /* valToShift = 7-bitPosition;
                    resultByte = (byte) (resultByte | (0<<valToShift)); */
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
