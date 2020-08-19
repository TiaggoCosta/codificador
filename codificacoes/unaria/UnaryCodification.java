package codificacoes.unaria;

import codificacoes.Decoder;
import codificacoes.Encoder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnaryCodification implements Encoder, Decoder {

    @Override
    public void decode() {

    }

    @Override
    public byte[] encode(byte [] data) {
        String s = "abc";
        int asc2;
        String codification = "";

        for(int i = 0; i < s.length(); i++){
            asc2 = s.charAt(i);
            codification = codification.concat(Stream.generate(() -> "0")
                    .limit(asc2)
                    .collect(Collectors.joining()));

            codification = codification.concat("1");
        }

        System.out.println("the codification is: " + codification);
        return data;
    }
}
