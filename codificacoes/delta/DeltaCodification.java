package codificacoes.delta;

import codificacoes.Decoder;
import codificacoes.Encoder;

public class DeltaCodification implements Encoder, Decoder {

    @Override
    public String decode(byte[] data) {
        return "Implementar";
    }

    @Override
    public byte[] encode(byte [] data) {
        return data;
    }
}
