package io.github.urbontaitis.adventofcode.day5;

import io.github.urbontaitis.adventofcode.day2.Intcode;
import java.util.LinkedList;
import java.util.List;

public class IntcodeUpgrade extends Intcode {

  public IntcodeUpgrade(List<Integer> dataInput) {
    super(dataInput);
  }

  public LinkedList<Integer> move(int opcodeIndex, LinkedList<Integer> state, int input) {
    int moveToIndex = state.get(opcodeIndex + 1);
    state.set(moveToIndex, input);
    return state;
  }


  public Integer output(int opcodeIndex, LinkedList<Integer> state) {
    int outputIndex = state.get(opcodeIndex + 1);
    return state.get(outputIndex);
  }


  public int parameterMode(int opcodeIndex, LinkedList<Integer> state, int input) {
    String[] parameters = String.valueOf(state.get(opcodeIndex)).split("");
    String opcode = parameters[parameters.length - 1];
    int parameterCount = parameters.length - 2;
    int firstParameter = 0;
    int secondParameter = 0;
    int thirdParameter = 0;

    switch(parameterCount) {
      case 3:
        firstParameter = Integer.valueOf(parameters[2]);
        secondParameter = Integer.valueOf(parameters[1]);
        thirdParameter = Integer.valueOf(parameters[0]);
        break;
      case 2:
        firstParameter = Integer.valueOf(parameters[1]);
        secondParameter = Integer.valueOf(parameters[0]);
        break;
      case 1:
        firstParameter = Integer.valueOf(parameters[0]);
        break;
      default:
        throw new IllegalArgumentException("unsupported parameters count: " + parameterCount);
    }

    switch(opcode) {
      case "1":
        sum(firstParameter, secondParameter, thirdParameter, opcodeIndex, state);
        return 3;
      case "2":
        multiply(firstParameter, secondParameter, thirdParameter, opcodeIndex, state);
        return 3;
      case "3":
        move(opcodeIndex, state, input);
        return 1;
      case "4":
        int output = output(opcodeIndex, state);
        System.out.println("debug code: " + output);
        return 1;
      case "99":
        System.out.println("done... exit");
      default:
        throw new IllegalArgumentException("unsupported opcode: " + opcode);

    }

//    return state;
  }

  private LinkedList<Integer> multiply(int firstParameter, int secondParameter, int thirdParameter, int opcodeIndex, LinkedList<Integer> state) {
    int firstIndex = getIndexByMode(opcodeIndex + 1, firstParameter, state);
    int secondIndex = getIndexByMode(opcodeIndex + 2, secondParameter, state);
    int resultIndex = getIndexByMode(opcodeIndex + 3, thirdParameter, state);

    return multiply(firstIndex, secondIndex, resultIndex, state);
  }

  private LinkedList<Integer> sum(int firstParameter, int secondParameter, int thirdParameter, int opcodeIndex, LinkedList<Integer> state) {
    int firstIndex = getIndexByMode(opcodeIndex + 1, firstParameter, state);
    int secondIndex = getIndexByMode(opcodeIndex + 2, secondParameter, state);
    int resultIndex = getIndexByMode(opcodeIndex + 3, thirdParameter, state);

    return sum(firstIndex, secondIndex, resultIndex, state);
  }

  private int getIndexByMode(int index, int mode, LinkedList<Integer>  state) {
    switch (mode) {
      case 0:
        return state.get(index);
      case 1:
        return index;
      default:
        throw new IllegalArgumentException("unsupported mode: " + mode);
    }
  }


  public int diagnostic(Integer input) {
    LinkedList<Integer> finalState = new LinkedList<>();
    finalState.addAll(getDataInput());
    int out = 0;
    for (int i = 0; i < finalState.size(); i++) {
      Integer opcode = finalState.get(i);
      if (opcode == 1) {
        sum(i, finalState);
        i += 3;
      } else if (opcode == 2) {
        multiply(i, finalState);
        i += 3;
      } else if (opcode == 3)   {
        move(i, finalState, input);
        i += 1;
      } else if (opcode == 4) {
        out = output(i, finalState);
        i += 1;

      } else if (opcode > 0 && String.valueOf(opcode).length() >= 3) {
        int steps = parameterMode(i, finalState, input);
        i += steps;
      } else if (opcode == 99) {
        return out;
      } else {
        throw new IllegalStateException("unknown opcode: " + opcode);
      }
    }

    throw new IllegalStateException("Nothing is found :(");
  }
}
