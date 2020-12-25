package lt.urbontaitis.adventofcode.day25;

import java.util.List;

class ComboBreaker {

  private static final int REMAINDER_VALUE = 20201227;
  private final long card;
  private final long door;

  ComboBreaker(List<String> inputData) {
    card = Long.parseLong(inputData.get(0));
    door = Long.parseLong(inputData.get(1));
  }

  private int getLoopSize(long target) {
    long number = 1;
    int loopSize = 0;
    while(number != target) {
      number *= 7;
      number %= REMAINDER_VALUE;
      loopSize += 1;
    }

    return loopSize;
  }

  private long encryptionKey(long subjectNumber, int loopSize) {
    long pubKey = 1;
    for(int i = 0; i < loopSize; i++) {
      pubKey *= subjectNumber;
      pubKey %= REMAINDER_VALUE;
    }
    return pubKey;
  }

  Long findEncryptionKey() {
    return encryptionKey(card, getLoopSize(door));
  }

}
