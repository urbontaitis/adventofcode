package io.github.urbontaitis.adventofcode.day2;

import java.util.LinkedList;
import java.util.List;

public class Intcode {

    private final List<Integer> dataInput;

    public Intcode(List<Integer> dataInput) {
        this.dataInput = dataInput;
    }

    public List<Integer> getDataInput() {
        return dataInput;
    }

    public LinkedList<Integer> executeState(List<Integer> initialState) {
        LinkedList<Integer> finalState = new LinkedList<>();
        finalState.addAll(initialState);

        for (int i = 0; i < finalState.size(); i++) {
            Integer opcode = finalState.get(i);
            if (opcode == 1) {
                sum(i, finalState);
                i += 3;
            } else if (opcode == 2) {
                multiply(i, finalState);
                i += 3;
            } else if (opcode == 99) {
                break;
            } else {
                throw new IllegalStateException("unknown opcode: " + opcode);
            }
        }

        return finalState;
    }

    protected LinkedList<Integer> multiply(int opcodeIndex, LinkedList<Integer> finalState) {
        int firstIndex = finalState.get(opcodeIndex + 1);
        int secondIndex = finalState.get(opcodeIndex + 2);
        int resultIndex = finalState.get(opcodeIndex + 3);

        Integer sum = finalState.get(firstIndex) * finalState.get(secondIndex);
        finalState.set(resultIndex, sum);

        return finalState;
    }

    protected LinkedList<Integer> sum(int opcodeIndex, LinkedList<Integer> finalState) {
        int firstIndex = finalState.get(opcodeIndex + 1);
        int secondIndex = finalState.get(opcodeIndex + 2);
        int resultIndex = finalState.get(opcodeIndex + 3);

        Integer sum = finalState.get(firstIndex) + finalState.get(secondIndex);
        finalState.set(resultIndex, sum);

        return finalState;
    }

    public LinkedList<Integer> restoreState(int firstValue, int secondValue) {
        dataInput.set(1, firstValue);
        dataInput.set(2, secondValue);

        return executeState(dataInput);
    }

}
