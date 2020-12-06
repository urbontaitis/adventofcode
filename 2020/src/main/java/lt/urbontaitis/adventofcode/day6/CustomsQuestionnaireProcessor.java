package lt.urbontaitis.adventofcode.day6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomsQuestionnaireProcessor {

  private final List<String> inputData;

  public CustomsQuestionnaireProcessor(String inputData) {
    this.inputData = Arrays.stream(inputData.split(",,")).collect(Collectors.toList());
  }

  private long countUniqueAnswers(String answers) {
    return answers.chars().filter(c -> c != ',').distinct().count();
  }

  Long sumOfAllYesAnswers() {
    return inputData.stream().mapToLong(this::countUniqueAnswers).sum();
  }

  private long countAnswersInGroup(String[] answers) {
    return answers[0]
        .chars()
        .filter(c -> Arrays.stream(answers).allMatch(answer -> answer.indexOf(c) >= 0))
        .count();
  }

  Long sumOfGroupYesAnswers() {
    return inputData.stream()
        .map(group -> group.split(","))
        .mapToLong(this::countAnswersInGroup)
        .sum();
  }
}
