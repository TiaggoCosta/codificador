package codificacoes.unaria;

import codificacoes.Decoder;
import codificacoes.Encoder;

import java.util.ArrayList;
import java.util.BitSet; 

public class UnaryCodification implements Encoder, Decoder {

    @Override
    public String decode(byte[] data) {
        ArrayList<Integer> decoded = new ArrayList<>();
        int count = 0;
        for(byte b : data) {
            BitSet bits = BitSet.valueOf(new long[] { b });
            for(int i = 7; i >= 0; i--){
                System.out.println("Bit on index: "+i+" = "+ bits.get(i));
                if(bits.get(i) == false){
                    System.out.println("Increasing count");
                    count ++;
                    System.out.println("count is now: "+count);
                } else {
                    System.out.println("Found bit 1, adding to decoded and reseting count");
                    decoded.add(Integer.valueOf(count));
                    System.out.println("Value added to decoded is: "+count);
                    count = 0;
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

        for(byte b : data) {
            for(int i = 0; i < b; i++) {
                if (bitPosition >= 8) {
                    //byte is complete, add to array and start over
                    resultBytes.add(resultByte);
                    resultByte = 0;
                    bitPosition = 0;
                }

                bitPosition++;
            }

            if (bitPosition >= 8) {
                //byte is complete, add to array and start over
                resultBytes.add(resultByte);
                resultByte = 0;
                bitPosition = 0;
            }

            //resultByte add stopbit (1)
            int valToShift = 7-bitPosition;
            resultByte = (byte) (resultByte | (1<<valToShift));
            bitPosition++;
        }

        //add residual bits of non complete byte
        if (bitPosition > 0) {
            resultBytes.add(resultByte);
        }

        byte[] result = new byte[resultBytes.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = resultBytes.get(i);
        }

        return result;
    }
}