package codificacoes.golomb;

import codificacoes.Decoder;
import codificacoes.Encoder;

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
        return data;
    }
}
