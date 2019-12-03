package io.github.urbontaitis.adventofcode.day3;

enum Direction {
    L("left", -1, 0),
    R("right", 1, 0),
    U("up", 0, 1),
    D("down", 0, -1);

    Direction(String code, int stepX, int stepY) {
            this.code = code;
            this.stepX = stepX;
            this.stepY = stepY;
    }

    private String code;
    private int stepX;
    private int stepY;

    public String getCode() {
        return code;
    }

    public int getStepX() {
        return stepX;
    }

    public int getStepY() {
        return stepY;
    }
}
