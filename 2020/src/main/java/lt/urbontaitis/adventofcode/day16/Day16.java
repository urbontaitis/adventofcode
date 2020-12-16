package lt.urbontaitis.adventofcode.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day16 {

  private final Pattern rulePattern = Pattern.compile("(.*): (\\d+)\\-(\\d+) or (\\d+)\\-(\\d+)");
  private final List<Rule> rules;
  private final List<List<Integer>> nearbyTickets;
  private final List<Integer> yourTicket;

  public Day16(List<String> inputData) {
    this.rules =
        Arrays.stream(inputData.get(0).split("\\n"))
            .map(this::transformRules)
            .collect(Collectors.toList());
    this.yourTicket = transformTickets(inputData.get(1)).get(0);
    this.nearbyTickets = transformTickets(inputData.get(2));
  }

  private List<List<Integer>> transformTickets(String rawTickets) {
    var tickets = new ArrayList<>(Arrays.asList(rawTickets.split("\\n")));
    tickets.remove(0);
    return tickets.stream().map(t -> Arrays.stream(t.split(",")).map(Integer::valueOf).collect(Collectors.toList())).collect(Collectors.toList());
  }

  private Rule transformRules(String rawRule) {
    var matcher = rulePattern.matcher(rawRule);
    if (!matcher.find()) {
      throw new IllegalStateException("Unknown rule format: " + rawRule);
    }
    int range1Start = Integer.parseInt(matcher.group(2));
    int range1End = Integer.parseInt(matcher.group(3)) + 1;
    int range2Start = Integer.parseInt(matcher.group(4));
    int range2End = Integer.parseInt(matcher.group(5)) + 1;

    return new Rule(
        matcher.group(1),
        IntStream.range(range1Start, range1End).boxed().collect(Collectors.toList()),
        IntStream.range(range2Start, range2End).boxed().collect(Collectors.toList()));
  }

  record Rule(String name, List<Integer> range1, List<Integer> range2) {}

  record Ticket(List<Integer> numbers) { }

  private Set<Integer> validNumbers() {
     return rules.stream().map(i -> Stream.of(i.range1(), i.range2())
        .flatMap(Collection::stream).collect(Collectors.toSet()))
        .flatMap(Collection::stream).collect(Collectors.toSet());
  }

  Integer doSomething() {
    var validNumbers = validNumbers();

    var answer = 0;
    for(var ticket : nearbyTickets) {
      for(var number : ticket) {
        if (!validNumbers.contains(number)) {
          answer += number;
        }
      }
    }

    return answer;
  }

  Long doSomething2(String ticketField) {
    var validNumbers = validNumbers();
    var goodNearby = new ArrayList<List<Integer>>();
    for (var ticket : nearbyTickets) {
      var isValid = true;
      for( var number : ticket) {
        if(!validNumbers.contains(number)) {
          isValid = false;
          break;
        }
      }
      if (isValid) {
        goodNearby.add(ticket);
      }
    }

    System.out.println(goodNearby);

    Map<Integer, String> fieldPositions = new HashMap<>();
    for(var ticket : goodNearby) {
      for(var number : ticket) {
        rules.stream().filter(rule -> { number >= })
      }
    }
//    var ruleOrderCandidates = new ArrayList<>();

    return null;
  }


  Integer getTicketField(String fieldName) {


    return null;
  }
}
