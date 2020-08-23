package codificacoes.unaria;

import codificacoes.Decoder;
import codificacoes.Encoder;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnaryCodification implements Encoder, Decoder {

    @Override
    public void decode() {

    }

    @Override
    public byte[] encode(byte [] data) {
        ArrayList<Byte> resultBytes = new ArrayList<>();
        byte resultByte = 0;
        int bitPosition = 0;

        for(byte b : data) {
            for(int i = 0; i < b; i++) {
                resultByte = (byte) (resultByte << 1);
                bitPosition++;

                //byte is complete, add to array and start over
                if (bitPosition >= 7) {
                    resultBytes.add(resultByte);
                    resultByte = 0;
                    bitPosition = 0;
                }
            }

            //byte is complete, add to array and start over
            if (bitPosition >= 7) {
                resultBytes.add(resultByte);
                resultByte = 0;
                bitPosition = 0;
            }

            //resultByte add stopbit (1)
            resultByte = (byte) (resultByte | (1<<bitPosition));
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

    @Override
    public String encodeChar(char[] data) {
        String s = "abc";
        int asc2;
        String codification = "";

        for(int i = 0; i < s.length(); i++){
            asc2 = (int)data[i];
            codification = codification.concat(Stream.generate(() -> "0")
                    .limit(asc2)
                    .collect(Collectors.joining()));

            codification = codification.concat("1");
        }

        System.out.println("the codification is: " + codification);
        return codification;
    }
}
