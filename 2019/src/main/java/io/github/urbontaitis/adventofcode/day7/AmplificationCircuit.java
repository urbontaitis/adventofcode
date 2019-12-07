package io.github.urbontaitis.adventofcode.day7;

import io.github.urbontaitis.adventofcode.day5.IntcodeUpgrade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AmplificationCircuit {

  private final List<Integer> state;

  public AmplificationCircuit(List<Integer> state) {
    this.state = state;
  }

  public Integer calculateThruster(List<Integer> sequences) {
    int thruster = 0;
    return calculateThruster(thruster, sequences);
  }

  public Integer calculateThruster(int thruster, List<Integer> sequences) {
    for (Integer phase : sequences) {
      thruster = (new IntcodeUpgrade(state)).executeAmplifier(thruster, phase);
    }

    return thruster;
  }

  public Integer findMaxThruster(String sequenceTemplate) {
    int maxThruster = 0;
    List<List<Integer>> allSequences = generateSequences(sequenceTemplate);

    for (List<Integer> sequences : allSequences) {
      int thruster = calculateThruster(sequences);
      if (thruster > maxThruster) {
        maxThruster = thruster;
      }
    }

    return maxThruster;
  }

  public Integer findMaxThrusterWithFeedBackLoop(String sequenceTemplate) {
    int maxThruster = 0;
    List<List<Integer>> allSequences = generateSequences(sequenceTemplate);
    int thruster = 0;
    while (thruster > maxThruster) {
      for (List<Integer> sequences : allSequences) {
        thruster = calculateThruster(thruster, sequences);
        if (thruster > maxThruster) {
          maxThruster = thruster;
        }
      }
    }

    return maxThruster;
  }

  private List<List<Integer>> generateSequences(String sequenceTemplate) {
    String[] base = sequenceTemplate.split("");
    List<String> strings = new ArrayList<>();
    permute(base, base.length, strings);

    List<List<Integer>> result = new ArrayList<>();

    for (String s : strings) {
      List<Integer> integers =
          Arrays.stream(s.split(",")).map(Integer::valueOf).collect(Collectors.toList());
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

  public Integer feedbackLoopTemp(List<Integer> sequences, Integer integer) {
    int maxThruster = 0;
//    int thruster = 0;
//    IntcodeUpgrade a = new IntcodeUpgrade(state);
//    thruster = a.executeAmplifier(thruster, sequences.get(0));
//    IntcodeUpgrade b = new IntcodeUpgrade(state);
//    thruster = b.executeAmplifier(thruster, sequences.get(1));
//    IntcodeUpgrade c = new IntcodeUpgrade(state);
//    thruster = c.executeAmplifier(thruster, sequences.get(2));
//    IntcodeUpgrade d = new IntcodeUpgrade(state);
//    thruster = d.executeAmplifier(thruster, sequences.get(3));
//    IntcodeUpgrade e = new IntcodeUpgrade(state);
//    thruster = e.executeAmplifier(thruster, sequences.get(4));




    return maxThruster;
  }
}
