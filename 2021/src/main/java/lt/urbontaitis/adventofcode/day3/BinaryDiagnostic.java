package lt.urbontaitis.adventofcode.day3;

import java.util.List;
import java.util.function.BiFunction;

public class BinaryDiagnostic {

    private final List<char[]> data;

    public BinaryDiagnostic(String inputData) {
        data = inputData.lines().map(String::toCharArray).toList();
    }

    public Integer calculatePowerConsumption() {
        var bitLength = data.get(0).length;
        var gamma = 0;
        var epsilon = 0;

        for (int i = 0; i < bitLength; i++) {
            int j = i;
            gamma <<= 1;
            epsilon <<= 1;
            var oneCount = (int) data.stream().filter(c -> c[j] == '1').count();
            var zeroCount = data.size() - oneCount;
            if (oneCount > zeroCount) {
                gamma += 1;
            } else {
                epsilon += 1;
            }
        }

        return gamma * epsilon;
    }

    private Integer bitLoop(List<char[]> numbers, BiFunction<Integer, Integer, Character> bitSelector) {
        var tempArray = numbers;
        var bitLength = numbers.get(0).length;
        for (int i = 0; i < bitLength && tempArray.size() > 1; ++i) {
            int j = i;
            int oneCount = (int) tempArray.stream().filter(c -> c[j] == '1').count();
            int zeroCount = tempArray.size() - oneCount;
            char expectedChar = bitSelector.apply(oneCount, zeroCount);
            tempArray = tempArray.stream().filter(c -> c[j] == expectedChar).toList();
        }

        return Integer.valueOf(new String(tempArray.get(0)), 2);
    }

    public Integer calculateLifeSupportRating() {
        int oxygen = bitLoop(data, (ones, zeros) -> ones >= zeros ? '1' : '0');
        int co2 = bitLoop(data, (ones, zeros) -> ones >= zeros ? '0' : '1');

        return oxygen * co2;
    }
}
