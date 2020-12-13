package lt.urbontaitis.adventofcode.day12;

record Position(int x, int y) {
    Position swap() {
        return new Position(y, x);
    }

    Position addX(int unit) {
        return new Position(x + unit, y);
    }

    Position addY(int unit) {
        return new Position(x, y + unit);
    }

    Position subX(int unit) {
        return new Position(x - unit, y);
    }

    Position subY(int unit) {
        return new Position(x, y - unit);
    }

    Position oppositeX() {
        return new Position(x * -1, y);
    }

    Position oppositeY() {
        return new Position(x, y * -1);
    }

    Position add(Position other, int units) {
        int newX = x + other.x * units;
        int newY = y + other.y * units;

        return new Position(newX, newY);
    }
}
