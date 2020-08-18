package codificacoes;

import java.util.Arrays;

public enum CodingType {
    Golomb("Golomb"),
    EliasGamma("Elias-Gamma"),
    Fibonacci("Fibonacci"),
    Unary("Unária"),
    Delta("Delta");

    private final String name;

    CodingType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CodingType getValueByName(String string) {
        return Arrays.stream(CodingType.values())
                .filter(codingType -> codingType.getName().equals(string))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("CodingType not found: " + string));
    }
}
