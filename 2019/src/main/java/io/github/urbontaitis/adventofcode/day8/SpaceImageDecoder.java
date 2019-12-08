package io.github.urbontaitis.adventofcode.day8;

import org.apache.commons.collections4.ListUtils;

import java.util.*;
import java.util.stream.Collectors;

class SpaceImageDecoder {

  private List<Integer> initialData;

  SpaceImageDecoder(String imageData) {
    this.initialData =
        Arrays.stream(imageData.split("")).map(Integer::valueOf).collect(Collectors.toList());
  }

  List<List<Integer>> decode(int width, int height) {
    return ListUtils.partition(initialData, width * height);
  }

  List<Integer> layerWithFewest0Digits(List<List<Integer>> layers) {
    List<List<Integer>> filtered =
        layers.stream()
            .sorted(
                (s1, s2) -> {
                  Integer first = (int) s1.stream().filter(i -> i == 0).count();
                  Integer second = (int) s2.stream().filter(i -> i == 0).count();
                  return first.compareTo(second);
                })
            .collect(Collectors.toList());

    return filtered.get(0);
  }

  public int multiplyDigits(List<Integer> integers) {
    Map<Integer, Integer> frequencyByDigits = new HashMap<>();
    integers.forEach(
        i -> {
          Integer count = frequencyByDigits.get(i);
          if (count == null) {
            count = 0;
          }
          frequencyByDigits.put(i, count + 1);
        });

    Integer number1 = frequencyByDigits.get(1);
    Integer number2 = frequencyByDigits.get(2);

    return number1 * number2;
  }

  // Transposition - https://stackoverflow.com/a/2942044
  public List<List<Integer>> transpose(List<List<Integer>> layers) {
    List<List<Integer>> result = new ArrayList<>();
    final int N = layers.get(0).size();
    for (int i = 0; i < N; i++) {
      List<Integer> col = new ArrayList<>();
      for (List<Integer> row : layers) {
        col.add(row.get(i));
      }
      result.add(col);
    }

    return result;
  }

  public List<Integer> filterLayers(List<List<Integer>> lists) {
    List<Integer> filtered = new ArrayList<>();

    for (List<Integer> l : lists) {
      for (Integer i : l) {
        if (i != 2) {
          filtered.add(i);
          break;
        }
      }
    }

    return filtered;
  }

  public List<String> createImage(Integer width, Integer height) {
    List<List<Integer>> decoded = decode(width, height);
    List<List<Integer>> grouped = transpose(decoded);
    List<Integer> filtered = filterLayers(grouped);

    return filtered.stream()
        .map(
            i -> {
              if (i == 1) {
                return "#";
              } else {
                return " ";
              }
            })
        .collect(Collectors.toList());
  }

  public boolean print(Integer width, Integer height) {
    List<String> imageList = createImage(width, height);
    List<List<String>> image = ListUtils.partition(imageList, width);

    image.forEach(i -> System.out.println(i));

    return true;
  }
}
