package codificacoes.fibonacci;

import codificacoes.Decoder;
import codificacoes.Encoder;

import java.util.ArrayList;
import java.util.Collections;

public class FibonacciCodification implements Encoder, Decoder {

    @Override
    public String decode(byte[] data) {
        ArrayList<Integer> decoded = new ArrayList<>();
        int currentNumber = 1;
        int nextNumber = 2;

        int decodeValue = 0;

        int bitPosition = 7;
        for (int bytePosition = 0; bytePosition < data.length;) {
//            int unsignedValue = data[bytePosition] & 0xFF;
            boolean bit = (data[bytePosition] & (1 << bitPosition)) > 0;
            boolean nextBit = (data[bytePosition] & (1 << (bitPosition - 1 < 0 ? 7 : (bitPosition - 1)))) > 0;

            System.out.println("Bit on index: " + bitPosition + " = " + bit);
            System.out.println("Value: " + currentNumber);
            if (bit) {
                System.out.println("Total: " + decodeValue + " + " + currentNumber);
                decodeValue += currentNumber;
                System.out.println("Total: " + decodeValue);
            }

            int temp = currentNumber;
            currentNumber = nextNumber;
            nextNumber = temp + nextNumber;

            bitPosition--;

            if (bit && nextBit) {
                currentNumber = 1;
                nextNumber = 2;
                decoded.add(decodeValue);
                decodeValue = 0;
                //pass stop bit
                bitPosition--;
            }

            if (bitPosition < 0) {
                bitPosition = 7 + bitPosition + 1;
                bytePosition++;
            }
        }

        StringBuilder result = new StringBuilder();

        for (Integer integer : decoded) {
            System.out.println("decoded: " + (char) integer.intValue());
            result.append((char) integer.intValue());
        }

        return result.toString();
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
