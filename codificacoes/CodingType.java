package codificacoes;

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
}
