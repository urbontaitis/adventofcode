package lt.urbontaitis.adventofcode.day3

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class MapScannerSpec extends Specification {

    def "The count should be 7"() {
        given:
        def inputData = [
                "..##.......",
                "#...#...#..",
                ".#....#..#.",
                "..#.#...#.#",
                ".#...##..#.",
                "..#.##.....",
                ".#.#.#....#",
                ".#........#",
                "#.##...#...",
                "#...##....#",
                ".#..#...#.#"]
        MapScanner testObject = new MapScanner(inputData)

        when:
        def response = testObject.countTrees(new MapScanner.Slope(3, 1))

        then:
        response == 7
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day3/input.txt")
        MapScanner testObject = new MapScanner(inputData)

        when:
        def response = testObject.countTrees(new MapScanner.Slope(3, 1))

        then:
        response == 228
    }

    def "When count all the trees and multiply together then number should be 336"() {
        given:
        def inputData = [
                "..##.......",
                "#...#...#..",
                ".#....#..#.",
                "..#.#...#.#",
                ".#...##..#.",
                "..#.##.....",
                ".#.#.#....#",
                ".#........#",
                "#.##...#...",
                "#...##....#",
                ".#..#...#.#"]
        MapScanner testObject = new MapScanner(inputData)
        def slopes = [new MapScanner.Slope(1, 1),
                      new MapScanner.Slope(3, 1),
                      new MapScanner.Slope(5, 1),
                      new MapScanner.Slope(7, 1),
                      new MapScanner.Slope(1, 2)]

        when:
        def response = testObject.countTotalTrees(slopes)

        then:
        response == 336
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day3/input.txt")
        MapScanner testObject = new MapScanner(inputData)
        def slopes = [new MapScanner.Slope(1, 1),
                      new MapScanner.Slope(3, 1),
                      new MapScanner.Slope(5, 1),
                      new MapScanner.Slope(7, 1),
                      new MapScanner.Slope(1, 2)]

        when:
        def response = testObject.countTotalTrees(slopes)

        then:
        response == 6818112000
    }

}
