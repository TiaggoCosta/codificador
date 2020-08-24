package codificacoes.fibonacci;

import codificacoes.Decoder;
import codificacoes.Encoder;

import java.util.ArrayList;
import java.util.Collections;

public class FibonacciCodification implements Encoder, Decoder {

    @Override
    public void decode(byte[] data) {

    }

    @Override
    public byte[] encode(byte [] data) {
        ArrayList<Byte> resultBytes = new ArrayList<>();
        byte resultByte = 0;
        int bitPosition = 7;

        for (byte b : data) {
            int value = Byte.toUnsignedInt(b);
            int rest = value;
            ArrayList<Integer> fibonacciNumbers = getFibonacciNumbers(value);
            fibonacciNumbers.sort(Collections.reverseOrder());

            for (Integer fibonacciNumber : fibonacciNumbers) {
                if (bitPosition <= 0) {
                    resultBytes.add(resultByte);
                    resultByte = 0;
                    bitPosition = 7;
                }

                if (rest - fibonacciNumber >= 0) {
                    rest -= fibonacciNumber;
                    //resultByte add 1
                    resultByte = (byte) (resultByte | ((byte) Math.pow(2, bitPosition)));
                }

                bitPosition--;

                if (rest == 0) {
                   break;
                }
            }

            if (bitPosition <= 0) {
                resultBytes.add(resultByte);
                resultByte = 0;
                bitPosition = 7;
            }

            //resultByte add stopbit (1)
            resultByte = (byte) (resultByte | ((byte) Math.pow(2, bitPosition)));
            bitPosition--;
        }

        if (resultByte != (byte)0) {
            resultBytes.add(resultByte);
        }

        byte[] result = new byte[resultBytes.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = resultBytes.get(i);
        }

        return result;
    }

    public ArrayList<Integer> getFibonacciNumbers(int value) {
        ArrayList<Integer> fibonacciNumbers = new ArrayList<>();
        int currentNumber = 1;
        int nextNumber = 2;
        fibonacciNumbers.add(currentNumber);

        while (nextNumber <= value) {
            fibonacciNumbers.add(nextNumber);
            int temp = currentNumber;
            currentNumber = nextNumber;
            nextNumber = temp + nextNumber;
        }

        return fibonacciNumbers;
    }

}
