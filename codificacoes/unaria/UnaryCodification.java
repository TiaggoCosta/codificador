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
}
