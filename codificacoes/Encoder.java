package codificacoes;

public interface Encoder {

    byte[] encode(byte [] data);

    String encodeChar(char [] data);
}
