package lt.urbontaitis.adventofcode.day13;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class BusScheduler {

    private final List<String> inputData;

    public BusScheduler(String inputData) {
        this.inputData = Arrays.asList(inputData.split(","));
    }

    Integer theEarliestBusIdMultipliedByTheWaitTime() {
        var items = inputData.stream().filter(s -> !"x".equals(s)).map(Integer::valueOf).collect(Collectors.toList());
        var startTime = items.remove(0);
        var earliestDepartureTime = Integer.MAX_VALUE;
        var earliestBusId = 0;
        for (var bus : items) {
            var loopTime = startTime / bus + 1;
            var departTime = bus * loopTime;
            if (departTime < earliestDepartureTime) {
                earliestDepartureTime = departTime;
                earliestBusId = bus;
            }
        }

        return earliestBusId * (earliestDepartureTime - startTime);
    }

    record Pair(Long first, Long second) {
        BigInteger getFirst() {
            return BigInteger.valueOf(first);
        }
        BigInteger getSecond() {
            return BigInteger.valueOf(second);
        }
    }

    private BigInteger crt(List<Pair> pairs) {
        var modulo = BigInteger.ONE;
        for (var pair : pairs) {
            modulo = modulo.multiply(pair.getSecond());
        }
        var total = BigInteger.ZERO;
        for (var pair : pairs) {
            var first = pair.getFirst();
            var second = pair.getSecond();
            var b = modulo.divide(second);
            int exponent = second.intValue() - 2;
            var remainder = b.pow(exponent).mod(second);
            var multiplied = first.multiply(b).multiply(remainder);
            total = total.add(multiplied);
            total = total.mod(modulo);
        }

        return total;
    }

    BigInteger findTheEarliestTimestamp() {
        var items = new ArrayList<>(inputData);
        items.remove(0);

        var pairs = new ArrayList<Pair>();
        for (int i = 0; i < items.size(); i++) {
            var rawBusId = items.get(i);
            if (!"x".equals(rawBusId)) {
                var busId = Long.parseLong(rawBusId);
                pairs.add(new Pair(busId - i, busId));
            }
        }

        return crt(pairs);
    }
}
