package codificacoes.delta;

import codificacoes.Decoder;
import codificacoes.Encoder;

public class DeltaCodification implements Encoder, Decoder {

    @Override
    public void decode(byte[] data) {

    }

    @Override
    public byte[] encode(byte [] data) {
        return data;
    }
}
