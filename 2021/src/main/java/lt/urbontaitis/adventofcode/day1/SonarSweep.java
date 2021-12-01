package lt.urbontaitis.adventofcode.day1;

import java.util.List;

public class SonarSweep {

    public Integer countDepthMeasurementIncrease(List<Integer> numbers) {
        int measurementCount = 0;
        for (int i = 0; i < numbers.size() - 1; i++) {
            var first = numbers.get(i);
            var second = numbers.get(i + 1);
            if (first < second) {
                measurementCount++;
            }
        }
        return measurementCount;
    }

    public Integer threeMeasurementsSlidingWindow(List<Integer> numbers) {
        int measurementCount = 0;
        for (int i = 0; i < numbers.size() - 3; i++) {
            var firstA = numbers.get(i);
            var secondA = numbers.get(i + 1);
            var thirdA = numbers.get(i + 2);
            var firstB = numbers.get(i + 1);
            var secondB = numbers.get(i + 2);
            var thirdB = numbers.get(i + 3);
            var A = firstA + secondA + thirdA;
            var B = firstB + secondB + thirdB;
            if (A < B) {
                measurementCount++;
            }
        }
        return measurementCount;
    }
}
