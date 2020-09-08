package codificacoes.delta;

import codificacoes.Decoder;
import codificacoes.Encoder;

public class DeltaCodification implements Encoder, Decoder {

    @Override
    public byte[] decode(byte[] data) {
        return null;
    }

    @Override
    public byte[] encode(byte [] data) {
        return data;
    }
}
