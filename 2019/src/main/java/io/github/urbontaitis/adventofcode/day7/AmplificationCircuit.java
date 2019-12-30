package io.github.urbontaitis.adventofcode.day7;

import io.github.urbontaitis.adventofcode.day2.Intcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AmplificationCircuit {

  private final List<Long> state;

  public AmplificationCircuit(List<Long> state) {
    this.state = state;
  }

  public Long calculateThruster(List<Long> sequences) {
    Long thruster = 0L;
    return calculateThruster(thruster, sequences);
  }

  public Long calculateThruster(Long thruster, List<Long> sequences) {
    for (Long phase : sequences) {
      thruster = (new Intcode(state)).executeAmplifier(thruster, phase);
    }

    return thruster;
  }

  public Long findMaxThruster(String sequenceTemplate) {
    Long maxThruster = 0L;
    List<List<Long>> allSequences = generateSequences(sequenceTemplate);

    for (List<Long> sequences : allSequences) {
      Long thruster = calculateThruster(sequences);
      if (thruster > maxThruster) {
        maxThruster = thruster;
      }
    }

    return maxThruster;
  }

  public Long findMaxThrusterWithFeedBackLoop(String sequenceTemplate) {
    Long maxThruster = 0L;
    List<List<Long>> allSequences = generateSequences(sequenceTemplate);
    Long thruster = 0L;
    while (thruster > maxThruster) {
      for (List<Long> sequences : allSequences) {
        thruster = calculateThruster(thruster, sequences);
        if (thruster > maxThruster) {
          maxThruster = thruster;
        }
      }
    }

    return maxThruster;
  }

  private List<List<Long>> generateSequences(String sequenceTemplate) {
    String[] base = sequenceTemplate.split("");
    List<String> strings = new ArrayList<>();
    permute(base, base.length, strings);

    List<List<Long>> result = new ArrayList<>();

    for (String s : strings) {
      List<Long> integers =
          Arrays.stream(s.split(",")).map(Long::valueOf).collect(Collectors.toList());
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
