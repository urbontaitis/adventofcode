package lt.urbontaitis.adventofcode.day11;

import java.util.List;
import java.util.stream.Collectors;

class PlainSeatingSystem {

    //make final
    private char[][] seats;
    private List<char[]> inputData;

    public PlainSeatingSystem(List<String> inputData) {
        this.seats = new char[inputData.size()][inputData.get(0).length()];
        for (int i = 0; i < inputData.size(); i++) {
            String line = inputData.get(i);
            for (int j = 0; j < line.length(); j++) {
                this.seats[i][j] = line.charAt(j);
            }
        }
        this.inputData = inputData.stream().map(String::toCharArray).collect(Collectors.toList());
    }

    enum CellType {
        FREE('L'),
        OCC('#'),
        GAP('.');

        private final char type;

        CellType(char type) {
            this.type = type;
        }

        public char getType() {
            return type;
        }
    }

    record Coordinates(int x, int y) {}



    private boolean modifySeatingChart1() {

        char[][] copy = new char[seats.length][seats[0].length];
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[0].length; j++) {
                copy[i][j] = seats[i][j];
            }
        }

        boolean changeMade = false;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                int occupiedNeighbors = 0;
                if (i - 1 >= 0 && j - 1 >= 0 && seats[i - 1][j - 1] == '#')
                    occupiedNeighbors++;
                if (i - 1 >= 0 && seats[i - 1][j] == '#')
                    occupiedNeighbors++;
                if (i - 1 >= 0 && j + 1 < seats[0].length && seats[i - 1][j + 1] == '#')
                    occupiedNeighbors++;
                if (j - 1 >= 0 && seats[i][j - 1] == '#')
                    occupiedNeighbors++;
                if (j + 1 < seats[0].length && seats[i][j + 1] == '#')
                    occupiedNeighbors++;
                if (i + 1 < seats.length && j - 1 >= 0 && seats[i + 1][j - 1] == '#')
                    occupiedNeighbors++;
                if (i + 1 < seats.length && seats[i + 1][j] == '#')
                    occupiedNeighbors++;
                if (i + 1 < seats.length && j + 1 < seats[0].length && seats[i + 1][j + 1] == '#')
                    occupiedNeighbors++;
                if (seats[i][j] == 'L' && occupiedNeighbors == 0) {
                    copy[i][j] = '#';
                    changeMade = true;
                } else if (seats[i][j] == '#' && occupiedNeighbors >= 4) {
                    copy[i][j] = 'L';
                    changeMade = true;
                }
            }
        }

        seats = copy;
        return changeMade;
    }

    Integer doSomething() {
        boolean changed = true;
        while (changed) {
            changed = modifySeatingChart1();
        }

        int occupied = 0;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j] == '#') {
                    occupied++;
                }
            }
        }
        return occupied;
    }

    Integer doSomething2() {
        boolean changed = true;
        while (changed) {
            changed = modifySeatingChart2();
        }

        int occupied = 0;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j] == '#') {
                    occupied++;
                }
            }
        }
        return occupied;
    }

    private boolean modifySeatingChart2() {

        char[][] copy = new char[seats.length][seats[0].length];
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[0].length; j++) {
                copy[i][j] = seats[i][j];
            }
        }

        boolean changeMade = false;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                int visbilyOccupiedSeats = 0;
                int r = i;
                int c = j;
                // up and left
                while (r - 1 >= 0 && c - 1 >= 0) {
                    r--;
                    c--;
                    if (seats[r][c] == 'L') {
                        break;
                    } else if (seats[r][c] == '#') {
                        visbilyOccupiedSeats++;
                        break;
                    }
                }
                r = i;
                c = j;
                // up
                while (r - 1 >= 0) {
                    r--;
                    if (seats[r][c] == 'L') {
                        break;
                    } else if (seats[r][c] == '#') {
                        visbilyOccupiedSeats++;
                        break;
                    }
                }
                r = i;
                c = j;
                // up and right
                while (r - 1 >= 0 && c <= seats[0].length - 2) {
                    r--;
                    c++;
                    if (seats[r][c] == 'L') {
                        break;
                    } else if (seats[r][c] == '#') {
                        visbilyOccupiedSeats++;
                        break;
                    }
                }
                r = i;
                c = j;
                // left
                while (c - 1 >= 0) {
                    c--;
                    if (seats[r][c] == 'L') {
                        break;
                    } else if (seats[r][c] == '#') {
                        visbilyOccupiedSeats++;
                        break;
                    }
                }
                r = i;
                c = j;
                // right
                while (c <= seats[0].length - 2) {
                    c++;
                    if (seats[r][c] == 'L') {
                        break;
                    } else if (seats[r][c] == '#') {
                        visbilyOccupiedSeats++;
                        break;
                    }
                }
                r = i;
                c = j;
                // down and left
                while (r <= seats.length - 2 && c - 1 >= 0) {
                    r++;
                    c--;
                    if (seats[r][c] == 'L') {
                        break;
                    } else if (seats[r][c] == '#') {
                        visbilyOccupiedSeats++;
                        break;
                    }
                }
                r = i;
                c = j;
                // down
                while (r <= seats.length - 2) {
                    r++;
                    if (seats[r][c] == 'L') {
                        break;
                    } else if (seats[r][c] == '#') {
                        visbilyOccupiedSeats++;
                        break;
                    }
                }
                r = i;
                c = j;
                // down and right
                while (r <= seats.length - 2 && c <= seats[0].length - 2) {
                    r++;
                    c++;
                    if (seats[r][c] == 'L') {
                        break;
                    } else if (seats[r][c] == '#') {
                        visbilyOccupiedSeats++;
                        break;
                    }
                }

                if (seats[i][j] == 'L' && visbilyOccupiedSeats == 0) {
                    copy[i][j] = '#';
                    changeMade = true;
                } else if (seats[i][j] == '#' && visbilyOccupiedSeats >= 5) {
                    copy[i][j] = 'L';
                    changeMade = true;
                }
            }
        }

        seats = copy;
        return changeMade;
    }
}
