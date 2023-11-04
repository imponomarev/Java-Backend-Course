package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {

    private static final int FOUR = 4;

    private static final int EIGHT = 8;

    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }


    enum Sex {
        M, F
    }


    public int paws() {
        return switch (type) {
            case CAT, DOG -> FOUR;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> EIGHT;
        };
    }
}
