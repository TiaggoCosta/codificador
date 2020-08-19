package codificacoes.golomb;

import codificacoes.Decoder;
import codificacoes.Encoder;

public class GolombCodification implements Encoder, Decoder {
    
    @Override
    public void decode() {

    }

    @Override
    public byte[] encode(byte [] data) {
        return data;
    }
}
