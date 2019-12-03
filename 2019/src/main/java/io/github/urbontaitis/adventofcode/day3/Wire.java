package io.github.urbontaitis.adventofcode.day3;

class Wire {

    private Direction direction;
    private Integer distance;

    Wire(String path) {
        String[] temp = path.split("(?<=\\D)(?=\\d)");
        this.direction = Direction.valueOf(temp[0]);
        this.distance = Integer.valueOf(temp[1]);
    }

    Direction getDirection() {
        return direction;
    }

    Integer getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Wire{" +
                "direction=" + direction +
                ", distance=" + distance +
                '}';
    }
}
