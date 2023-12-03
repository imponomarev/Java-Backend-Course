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

    private static final int WITHOUT_LEGS = 0;

    private static final int TWO_LEGS = 2;
    private static final int FOUR_LEGS = 4;

    private static final int EIGHT_LEGS = 8;

    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }


    enum Sex {
        M, F
    }


    public int paws() {
        return switch (type) {
            case CAT, DOG -> FOUR_LEGS;
            case BIRD -> TWO_LEGS;
            case FISH -> WITHOUT_LEGS;
            case SPIDER -> EIGHT_LEGS;
        };
    }
}
