package lt.urbontaitis.adventofcode.day5;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BoardingPassReader {

  private final List<BoardingPass> inputData;

  public BoardingPassReader(List<String> inputData) {
    this.inputData = inputData.stream().map(BoardingPassMapper::transform).collect(Collectors.toList());
  }

  int findHighestSeatId() {
    return inputData.stream().map(BoardingPass::seatId).max(Comparator.naturalOrder()).orElse(-1);
  }

  int findFreeSeatId() {
    var takenSeatsId = inputData.stream().map(BoardingPass::seatId).sorted().collect(Collectors.toList());
    var firstSeat = takenSeatsId.get(0);
    var lastSeat = takenSeatsId.get(takenSeatsId.size()-1);
    var allSeats = IntStream.rangeClosed(firstSeat, lastSeat).boxed().collect(Collectors.toList());
    allSeats.removeAll(takenSeatsId);

    return allSeats.stream().findFirst().orElse(-1);
  }
}
