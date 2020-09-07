package codificacoes.fibonacci;

import codificacoes.Decoder;
import codificacoes.Encoder;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;

public class FibonacciCodification implements Encoder, Decoder {

    @Override
    public byte[] decode(byte[] data) {
        ArrayList<Integer> decoded = new ArrayList<>();
        BitSet bits = BitSet.valueOf(data);

        int currentNumber = 1;
        int nextNumber = 2;
        int count = 0;

        for (int i = 0; i < bits.length(); i++) {
            boolean bit = bits.get(i);
            if (!bits.get(i - 1) || !bits.get(i)) {
                currentNumber = 1;
                nextNumber = 2;
                decoded.add(count);
                count = 0;
            }

            System.out.println("Bit on index: " + i + " = " + bits.get(i));
            System.out.println("Value: " + currentNumber);
            if (bits.get(i)) {
                System.out.println("Total: " + count + " + " + currentNumber);
                count += currentNumber;
                System.out.println("Total: " + count);
            }

            int temp = currentNumber;
            currentNumber = nextNumber;
            nextNumber = temp + nextNumber;
            i++;
        }

        byte[] decodedBytes = new byte[decoded.size()];
        for (int i = 0; i < decodedBytes.length; i++) {
            int ascii = decoded.get(i);
            decodedBytes[i] = (byte)ascii;
        }

        return decodedBytes;
    }

    @Override
    public byte[] encode(byte[] data) {
        ArrayList<Byte> resultBytes = new ArrayList<>();
        int bytePosition;
        int bitPosition;
        int totalBitsUsed = 0;
        byte resultByte;

        for (byte b : data) {
            int value = Byte.toUnsignedInt(b);
            int rest = value;
            ArrayList<Integer> fibonacciNumbers = getFibonacciNumbersByValue(value);
            fibonacciNumbers.sort(Collections.reverseOrder());
            // totalBytes = round up (fibonacciNumbersSize + stop bit / number of bits)
            totalBitsUsed += (fibonacciNumbers.size() + 1);
            int totalBytes = (int) Math.ceil(totalBitsUsed / 8.00);

            //add empty bytes
            for (int i = 0; resultBytes.size() != totalBytes; i++) {
                resultBytes.add((byte) 0);
            }

            bytePosition = resultBytes.size() - 1;
            resultByte = resultBytes.get(bytePosition);

            bitPosition = 8 - totalBitsUsed % 8;

            //start with stop bit (1)
            resultByte = (byte) (resultByte | (1 << bitPosition));
            bitPosition++;

            int i = 0;
            for (Integer fibonacciNumber : fibonacciNumbers) {
                if (bitPosition >= 8) {
                    resultBytes.set(bytePosition, resultByte);
                    bytePosition--;
                    bitPosition = 0;
                    resultByte = resultBytes.get(Math.max(bytePosition, 0));
                }

                if (rest - fibonacciNumber >= 0) {
                    rest -= fibonacciNumber;
                    //resultByte add 1
                    resultByte = (byte) (resultByte | (1 << bitPosition));
                }

                bitPosition++;
                i++;
            }

            resultBytes.set(bytePosition, resultByte);
        }

        byte[] result = new byte[resultBytes.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = resultBytes.get(i);
        }

        return result;
    }

    public ArrayList<Integer> getFibonacciNumbersByValue(int value) {
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
