package codificacoes.fibonacci;

import codificacoes.Decoder;
import codificacoes.Encoder;

import java.util.ArrayList;

public class FibonacciCodification implements Encoder, Decoder {

    @Override
    public void decode() {

    }

    @Override
    public byte[] encode(byte [] data) {
        ArrayList<Byte> result = new ArrayList<>();
        for (byte b : data) {
            int value = Byte.toUnsignedInt(b);

        }
        return data;
    }
}
