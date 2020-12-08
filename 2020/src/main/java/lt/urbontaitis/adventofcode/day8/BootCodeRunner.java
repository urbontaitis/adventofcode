package lt.urbontaitis.adventofcode.day8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BootCodeRunner {

  private final List<Program> inputData;

  public BootCodeRunner(List<String> inputData) {
    this.inputData = inputData.stream().map(this::transform).collect(Collectors.toList());
  }

  private Program transform(String rawInstruction) {
   var instruction = rawInstruction.split(" ", 2);

    return new Program(Operation.valueOf(instruction[0].toUpperCase()), Integer.valueOf(instruction[1]));
  }

  enum Operation {
    ACC,
    JMP,
    NOP
  }

  record Program(Operation operation, Integer argument) {}

  int run() {
    int accumulator = 0;
    Set<Integer> instructions = new HashSet<>();
    for(int i = 0; i < inputData.size();) {
      Program program = inputData.get(i);
      if(instructions.contains(i)) {
        return accumulator;
      }
      instructions.add(i);

      switch (program.operation) {
        case ACC -> { accumulator+= program.argument; i++; }
        case JMP -> i += program.argument;
        case NOP -> i++;
      }
    }
    return accumulator;
  }

  int runSelfHealing() {
    int i = 0;
    while(i <= inputData.size()) {
      List<Program> inputDataClone = new ArrayList<>(inputData);
      Program program = inputDataClone.get(i);
      switch (program.operation) {
        case JMP -> inputDataClone.set(i, new Program(Operation.NOP, program.argument()));
        case NOP -> inputDataClone.set(i, new Program(Operation.JMP, program.argument()));
      }

      if (program != inputDataClone.get(i)) {
        Optional<Integer> accumulator = terminatesWithValue(inputDataClone);
        if(accumulator.isPresent()) {
          return accumulator.get();
        }
      }
      i++;
    }

    throw new IllegalStateException("Failed to find a correction in the instructions");
  }

  Optional<Integer> terminatesWithValue(List<Program> input) {
    int accumulator = 0;
    int i = 0;
    Set<Integer> instructions = new HashSet<>();
    while(true) {
      if (i >= input.size()) {
        return Optional.of(accumulator);
      }
      if(instructions.contains(i)) {
        return Optional.empty();
      }
      instructions.add(i);

      Program program = input.get(i);
      switch (program.operation) {
        case ACC -> { accumulator+= program.argument; i++; }
        case JMP -> i += program.argument;
        case NOP -> i++;
      }
    }
  }
}
