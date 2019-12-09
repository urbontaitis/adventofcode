package io.github.urbontaitis.adventofcode.day5;

import io.github.urbontaitis.adventofcode.day2.Intcode;
import java.util.LinkedList;
import java.util.List;

public class IntcodeUpgrade extends Intcode {

  private boolean halted = false;
  private int relativeBase = 0;

  public IntcodeUpgrade(List<Integer> dataInput) {
    super(dataInput);
  }

  LinkedList<Integer> move(int modeA, int opcodeIndex, LinkedList<Integer> state, int input) {
    int firstParameter = getIndexByMode(opcodeIndex + 1, modeA, state);

    state.set(firstParameter, input);
    return state;
  }

  Integer output(int modeA, int opcodeIndex, LinkedList<Integer> state) {
    int firstParameter = getIndexByMode(opcodeIndex + 1, modeA, state);

    return state.get(firstParameter);
  }

  LinkedList<Integer> multiply(
      int firstParameter,
      int secondParameter,
      int thirdParameter,
      int opcodeIndex,
      LinkedList<Integer> state) {
    int firstIndex = getIndexByMode(opcodeIndex + 1, firstParameter, state);
    int secondIndex = getIndexByMode(opcodeIndex + 2, secondParameter, state);
    int resultIndex = getIndexByMode(opcodeIndex + 3, thirdParameter, state);

    return multiply(firstIndex, secondIndex, resultIndex, state);
  }

  LinkedList<Integer> sum(
      int firstParameter,
      int secondParameter,
      int thirdParameter,
      int opcodeIndex,
      LinkedList<Integer> state) {
    int firstIndex = getIndexByMode(opcodeIndex + 1, firstParameter, state);
    int secondIndex = getIndexByMode(opcodeIndex + 2, secondParameter, state);
    int resultIndex = getIndexByMode(opcodeIndex + 3, thirdParameter, state);

    return sum(firstIndex, secondIndex, resultIndex, state);
  }

  private int getIndexByMode(int index, int mode, LinkedList<Integer> state) {
    switch (mode) {
      case 0:
        return state.get(index);
      case 1:
        return index;
      case 2:
        relativeBase += index;
        return relativeBase;
      default:
        throw new IllegalArgumentException("unsupported mode: " + mode);
    }
  }

  private LinkedList<Integer> temp;

  public LinkedList<Integer> getTemp() {
    return temp;
  }

  public int diagnostic(Integer input) {
    return executeAmplifier(input, null);
  }

  public int executeAmplifier(Integer input, Integer phase) {
    LinkedList<Integer> state = new LinkedList<>();
    state.addAll(getDataInput());
    int out = -1;
    boolean usePhase = phase != null;

    int i = 0;
    while (inProgress()) {
      Integer opcodeParameterized = state.get(i);
      int opcode = opcodeParameterized % 100;
      int modeA = opcodeParameterized / 100 % 10;
      int modeB = opcodeParameterized / 1000 % 10;
      int modeC = opcodeParameterized / 10000 % 10;

      if (opcode == 99) {
        temp = state;
        setInProgress(false);
        return out;
      } else if (opcode == 1) {
        sum(modeA, modeB, modeC, i, state);
        i += 4;
      } else if (opcode == 2) {
        multiply(modeA, modeB, modeC, i, state);
        i += 4;
      } else if (opcode == 3) {
        int inputToUse = usePhase ? phase : input;
        usePhase = false;
        move(modeA, i, state, inputToUse);
        i += 2;
      } else if (opcode == 4) {
        out = output(modeA, i, state);
        System.out.println("Output: " + out);
        i += 2;
      } else if (opcode == 5) {
        i = jumpIfTrue(modeA, modeB, i, state);
      } else if (opcode == 6) {
        i = jumpIfFalse(modeA, modeB, i, state);
      } else if (opcode == 7) {
        isLessThan(modeA, modeB, modeC, i, state);
        i += 4;
      } else if (opcode == 8) {
        isEquals(modeA, modeB, modeC, i, state);
        i += 4;
      } else if (opcode == 9) {
        adjustTheRelativeBase(modeA, i, state);
        i += 2;
      } else {
        throw new IllegalStateException("unknown opcode: " + opcode);
      }
    }

    throw new IllegalStateException("Nothing is found :(");
  }

  public int getRelativeBase() {
    return relativeBase;
  }

  public void setRelativeBase(int relativeBase) {
    this.relativeBase = relativeBase;
  }

  void adjustTheRelativeBase(int modeA, int opcodeIndex, LinkedList<Integer> state) {
    int parameterIndex = getIndexByMode(modeA, opcodeIndex + 1, state);

    int parameterValue = state.get(parameterIndex);

    relativeBase = relativeBase + parameterValue;
  }

  int jumpIfTrue(int modeA, int modeB, int opcodeIndex, LinkedList<Integer> state) {
    int firstParameter = getIndexByMode(opcodeIndex + 1, modeA, state);
    int secondParameter = getIndexByMode(opcodeIndex + 2, modeB, state);

    if (state.get(firstParameter) != 0) {
      return state.get(secondParameter);
    } else {
      return opcodeIndex + 3;
    }
  }

  void isLessThan(int modeA, int modeB, int modeC, int opcodeIndex, LinkedList<Integer> state) {
    int firstParameter = getIndexByMode(opcodeIndex + 1, modeA, state);
    int secondParameter = getIndexByMode(opcodeIndex + 2, modeB, state);
    int thirdParameter = getIndexByMode(opcodeIndex + 3, modeC, state);

    if (state.get(firstParameter) < state.get(secondParameter)) {
      state.set(thirdParameter, 1);
    } else {
      state.set(thirdParameter, 0);
    }
  }

  void isEquals(int modeA, int modeB, int modeC, int opcodeIndex, LinkedList<Integer> state) {
    int firstParameter = getIndexByMode(opcodeIndex + 1, modeA, state);
    int secondParameter = getIndexByMode(opcodeIndex + 2, modeB, state);
    int thirdParameter = getIndexByMode(opcodeIndex + 3, modeC, state);

    if (state.get(firstParameter).equals(state.get(secondParameter))) {
      state.set(thirdParameter, 1);
    } else {
      state.set(thirdParameter, 0);
    }
  }

  int jumpIfFalse(int modeA, int modeB, int opcodeIndex, LinkedList<Integer> state) {
    int firstParameter = getIndexByMode(opcodeIndex + 1, modeA, state);
    int secondParameter = getIndexByMode(opcodeIndex + 2, modeB, state);

    if (state.get(firstParameter) == 0) {
      return state.get(secondParameter);
    } else {
      return opcodeIndex + 3;
    }
  }

  public boolean inProgress() {
    return !halted;
  }

  public void setInProgress(boolean inProgress) {
    this.halted = !inProgress;
  }
}
