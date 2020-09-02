package codificacoes.golomb;

import codificacoes.Decoder;
import codificacoes.Encoder;

import java.util.ArrayList;
import java.util.BitSet;

public class GolombCodification implements Encoder, Decoder {

    final int divisor;

    public GolombCodification(int divisor) {
        this.divisor = divisor;
    }

    @Override
    public String decode(byte[] data) {
        return "Implementar";
    }

    @Override
    public byte[] encode(byte [] data) {
        ArrayList<Byte> resultBytes = new ArrayList<>();
        byte resultByte = 0;
        int bitPosition = 0;

        int suffixSize = calculateLog2(divisor);

        int value;
        int rest;
        int valToShift;

        addHeaderValues(resultBytes);

        for(byte b : data) {
            value = b / divisor;
            rest = b - (value * divisor);

            //add value size in zeroes
            for(int i = 0; i < value; i++) {
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
            valToShift = 7-bitPosition;
            resultByte = (byte) (resultByte | (1<<valToShift));
            bitPosition++;

            //add rest in binary
            BitSet bitsOfRest = BitSet.valueOf(new long[] { rest });
            for(int i = suffixSize-1; i >= 0; i--){
                if (bitPosition >= 8) {
                    //byte is complete, add to array and start over
                    resultBytes.add(resultByte);
                    resultByte = 0;
                    bitPosition = 0;
                }

                if(bitsOfRest.get(i) == true) {
                    valToShift = 7-bitPosition;
                    resultByte = (byte) (resultByte | (1<<valToShift));
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

        return result;
    }

    //https://www.techiedelight.com/calculate-log-base-2-in-java/
    private int calculateLog2(int value){
        return (int) (Math.log(value) / Math.log(2) + 1e-10);
    }

    private void addHeaderValues(ArrayList<Byte> resultBytes){
        resultBytes.add((byte) 0);
        resultBytes.add((byte) divisor);
    }
}
