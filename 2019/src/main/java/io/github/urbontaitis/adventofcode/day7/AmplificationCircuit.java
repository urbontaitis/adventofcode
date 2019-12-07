package io.github.urbontaitis.adventofcode.day7;

import io.github.urbontaitis.adventofcode.day5.IntcodeUpgrade;

import java.util.*;
import java.util.stream.Collectors;

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
    int maxThruster = 0;
    List<List<Integer>> allSequences = generateSequences();

    for(List<Integer> sequences : allSequences) {
      int thruster = calculateThruster(sequences);
      if (thruster > maxThruster) {
        maxThruster = thruster;
      }
    }

    return maxThruster;
  }

  private List<List<Integer>> generateSequences() {
    String[] base = "01234".split("");
    List<String> strings = new ArrayList<>();
    permute(base, base.length, strings);

    List<List<Integer>> result = new ArrayList<>();

    for (String s : strings) {
      List<Integer> integers =
          Arrays.asList(s.split(",")).stream().map(Integer::valueOf).collect(Collectors.toList());
      result.add(integers);
    }

    return result;
  }

  private static void swap(String[] base, int right, int left) {
    String temp = base[right];
    base[right] = base[left];
    base[left] = temp;
  }

  public static void permute(String[] base, int currentPosition, List<String> permutations) {
    if (currentPosition == 1) {
      permutations.add(String.join(",", base));
    } else {
      for (int i = 0; i < currentPosition; i++) {
        permute(base, currentPosition - 1, permutations);

        if (currentPosition % 2 == 1) {
          swap(base, 0, currentPosition - 1);
        } else {
          swap(base, i, currentPosition - 1);
        }
      }
    }
  }
}
