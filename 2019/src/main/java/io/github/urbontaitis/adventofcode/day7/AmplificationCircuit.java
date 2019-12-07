package io.github.urbontaitis.adventofcode.day7;

import io.github.urbontaitis.adventofcode.day5.IntcodeUpgrade;

import java.util.List;

public class AmplificationCircuit {

    private final List<Integer> state;

    public AmplificationCircuit(List<Integer> state) {
        this.state = state;
    }

    public Integer calculateThruster(List<Integer> sequences) {
        int thruster = 0;
        for (Integer phase : sequences) {
            thruster = (new IntcodeUpgrade(state)).executeAmplifier(thruster, phase);
        }

        return thruster;
    }

    public Integer findMaxThruster() {
        return 0;
    }
}
