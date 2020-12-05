package lt.urbontaitis.adventofcode.day5;

class BoardingPassMapper {

  private BoardingPassMapper() {
  }

  static BoardingPass transform(String raw) {
    int row = Integer.parseInt(raw.substring(0, 7).replace('F', '0').replace('B', '1'), 2);
    int column =
        Integer.parseInt(raw.substring(raw.length() - 3).replace('R', '1').replace('L', '0'), 2);
    int seatId = row * 8 + column;
    return new BoardingPass(row, column, seatId);
  }
}
