package io.github.urbontaitis.adventofcode.day2;

import java.util.LinkedList;
import java.util.List;

public class Intcode {

  private boolean halted = false;
  private Long relativeBase = 0L;
  private List<Long> state;

  public List<Long> getTemp() {
    return state;
  }

  public Intcode(List<Long> dataInput) {
    state = new LinkedList<>(dataInput);
  }

  public List<Long> getDataInput() {
    return state;
  }

  public Long executeState() {
    return executeAmplifier(1L, null);
  }

  Long getValueFromStateAt(Long index) {
    return state.get(index.intValue());
  }

  void setValueToStateAt(Long index, Long value) {
    state.set(index.intValue(), value);
  }

  protected List<Long> multiply(Long opcodeIndex) {
    Long firstIndex = getValueFromStateAt(opcodeIndex + 1);
    Long secondIndex = getValueFromStateAt(opcodeIndex + 2);
    Long resultIndex = getValueFromStateAt(opcodeIndex + 3);

    return multiply(firstIndex, secondIndex, resultIndex);
  }

  protected List<Long> multiply(Long firstIndex, Long secondIndex, Long resultIndex) {
    Long result = getValueFromStateAt(firstIndex) * getValueFromStateAt(secondIndex);
    setValueToStateAt(resultIndex, result);

    return state;
  }

  protected List<Long> sum(Long opcodeIndex) {
    Long firstIndex = getValueFromStateAt(opcodeIndex + 1);
    Long secondIndex = getValueFromStateAt(opcodeIndex + 2);
    Long resultIndex = getValueFromStateAt(opcodeIndex + 3);
    return sum(firstIndex, secondIndex, resultIndex);
  }

  protected List<Long> sum(Long firstIndex, Long secondIndex, Long resultIndex) {
    Long sum = getValueFromStateAt(firstIndex) + getValueFromStateAt(secondIndex);
    setValueToStateAt(resultIndex, sum);

    return state;
  }

  public Long restoreState(int firstValue, int secondValue) {
    setValueToStateAt(1L, Long.valueOf(firstValue));
    setValueToStateAt(2L, Long.valueOf(secondValue));

    return executeState();
  }

  List<Long> move(Long modeA, Long opcodeIndex, Long input) {
    Long firstParameter = getIndexByMode(opcodeIndex + 1, modeA);

    setValueToStateAt(firstParameter, input);
    return state;
  }

  Long output(Long modeA, Long opcodeIndex) {
    Long firstParameter = getIndexByMode(opcodeIndex + 1, modeA);

    return getValueFromStateAt(firstParameter);
  }

  List<Long> multiply(
      Long firstParameter, Long secondParameter, Long thirdParameter, Long opcodeIndex) {
    Long firstIndex = getIndexByMode(opcodeIndex + 1, firstParameter);
    Long secondIndex = getIndexByMode(opcodeIndex + 2, secondParameter);
    Long resultIndex = getIndexByMode(opcodeIndex + 3, thirdParameter);

    return multiply(firstIndex, secondIndex, resultIndex);
  }

  List<Long> sum(Long firstParameter, Long secondParameter, Long thirdParameter, Long opcodeIndex) {
    Long firstIndex = getIndexByMode(opcodeIndex + 1, firstParameter);
    Long secondIndex = getIndexByMode(opcodeIndex + 2, secondParameter);
    Long resultIndex = getIndexByMode(opcodeIndex + 3, thirdParameter);

    return sum(firstIndex, secondIndex, resultIndex);
  }

  private Long getIndexByMode(Long index, Long mode) {
    switch (mode.intValue()) {
      case 0:
        return getValueFromStateAt(index);
      case 1:
        return index;
      case 2:
        relativeBase += index;
        return relativeBase;
      default:
        throw new IllegalArgumentException("unsupported mode: " + mode);
    }
  }

  public Long diagnostic(Long input) {
    return executeAmplifier(input, null);
  }

  public Long executeAmplifier(Long input, Long phase) {
    Long out = 0L;
    boolean usePhase = phase != null;

    Long i = 0L;
    while (inProgress()) {
      Long opcodeParameterized = getValueFromStateAt(i);
      Long opcode = opcodeParameterized % 100;
      Long modeA = opcodeParameterized / 100 % 10;
      Long modeB = opcodeParameterized / 1000 % 10;
      Long modeC = opcodeParameterized / 10000 % 10;

      if (opcode == 99) {
        setInProgress(false);
        return out;
      } else if (opcode == 1) {
        sum(modeA, modeB, modeC, i);
        i += 4;
      } else if (opcode == 2) {
        multiply(modeA, modeB, modeC, i);
        i += 4;
      } else if (opcode == 3) {
        Long inputToUse = usePhase ? phase : input;
        usePhase = false;
        move(modeA, i, inputToUse);
        i += 2;
      } else if (opcode == 4) {
        out = output(modeA, i);
        System.out.println("Output: " + out);
        i += 2;
      } else if (opcode == 5) {
        i = jumpIfTrue(modeA, modeB, i);
      } else if (opcode == 6) {
        i = jumpIfFalse(modeA, modeB, i);
      } else if (opcode == 7) {
        isLessThan(modeA, modeB, modeC, i);
        i += 4;
      } else if (opcode == 8) {
        isEquals(modeA, modeB, modeC, i);
        i += 4;
      } else if (opcode == 9) {
        adjustTheRelativeBase(modeA, i);
        i += 2;
      } else {
        throw new IllegalStateException("unknown opcode: " + opcode);
      }
    }

    throw new IllegalStateException("Nothing is found :(");
  }

  public Long getRelativeBase() {
    return relativeBase;
  }

  void adjustTheRelativeBase(Long modeA, Long opcodeIndex) {
    Long parameterIndex = getIndexByMode(modeA, opcodeIndex + 1);

    Long parameterValue = getValueFromStateAt(parameterIndex);

    relativeBase = relativeBase + parameterValue;
  }

  Long jumpIfTrue(Long modeA, Long modeB, Long opcodeIndex) {
    Long firstParameter = getIndexByMode(opcodeIndex + 1, modeA);
    Long secondParameter = getIndexByMode(opcodeIndex + 2, modeB);

    if (getValueFromStateAt(firstParameter) != 0) {
      return getValueFromStateAt(secondParameter);
    } else {
      return opcodeIndex + 3;
    }
  }

  void isLessThan(Long modeA, Long modeB, Long modeC, Long opcodeIndex) {
    Long firstParameter = getIndexByMode(opcodeIndex + 1, modeA);
    Long secondParameter = getIndexByMode(opcodeIndex + 2, modeB);
    Long thirdParameter = getIndexByMode(opcodeIndex + 3, modeC);

    if (getValueFromStateAt(firstParameter) < getValueFromStateAt(secondParameter)) {
      setValueToStateAt(thirdParameter, 1L);
    } else {
      setValueToStateAt(thirdParameter, 0L);
    }
  }

  void isEquals(Long modeA, Long modeB, Long modeC, Long opcodeIndex) {
    Long firstParameter = getIndexByMode(opcodeIndex + 1, modeA);
    Long secondParameter = getIndexByMode(opcodeIndex + 2, modeB);
    Long thirdParameter = getIndexByMode(opcodeIndex + 3, modeC);

    if (getValueFromStateAt(firstParameter).equals(getValueFromStateAt(secondParameter))) {
      setValueToStateAt(thirdParameter, 1L);
    } else {
      setValueToStateAt(thirdParameter, 0L);
    }
  }

  Long jumpIfFalse(Long modeA, Long modeB, Long opcodeIndex) {
    Long firstParameter = getIndexByMode(opcodeIndex + 1, modeA);
    Long secondParameter = getIndexByMode(opcodeIndex + 2, modeB);

    if (getValueFromStateAt(firstParameter) == 0) {
      return getValueFromStateAt(secondParameter);
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
