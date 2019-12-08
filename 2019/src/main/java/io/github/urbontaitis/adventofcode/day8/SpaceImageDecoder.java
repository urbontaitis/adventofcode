package io.github.urbontaitis.adventofcode.day8;

import org.apache.commons.collections4.ListUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class SpaceImageDecoder {

    private List<Integer> initialData;

    SpaceImageDecoder(String imageData) {
        this.initialData = Arrays.stream(imageData.split("")).map(Integer::valueOf).collect(Collectors.toList());
    }

    List<List<Integer>> decode(int width, int height) {
        return ListUtils.partition(initialData, width * height);
    }

    List<Integer> layerWithFewest0Digits(List<List<Integer>> layers) {
        List<List<Integer> >filtered = layers.stream().sorted((s1, s2) -> {
            Integer first = (int) s1.stream().filter(i -> i == 0).count();
            Integer second = (int) s2.stream().filter(i -> i == 0).count();
            return first.compareTo(second);
        }).collect(Collectors.toList());

        return filtered.get(0);
    }


    public int multiplyDigits(List<Integer> integers) {
        Map<Integer, Integer> frequencyByDigits = new HashMap<>();
        integers.forEach(i -> {
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
}
