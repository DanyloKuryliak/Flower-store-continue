package com.example.demo.flower.store;
public enum FlowerColor {
    /**
     * Flower colors.
     */
    RED("#FF0000"),
    BLUE("#0000FF"),
    DEFAULT_COLOR("#000000");
    private final String stringRepresentation;

    FlowerColor(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }
}
