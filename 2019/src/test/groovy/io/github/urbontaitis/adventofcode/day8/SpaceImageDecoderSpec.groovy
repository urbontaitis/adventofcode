package io.github.urbontaitis.adventofcode.day8

import io.github.urbontaitis.adventofcode.FileReader
import spock.lang.Specification

class SpaceImageDecoderSpec extends Specification {


    def "Transform the image data into #width pixels wide and #height pixels tall"() {
        when:
        List<List<Integer>> layers = (new SpaceImageDecoder(imageData)).decode(width, height)

        then:
        layers == expectedLayers

        where:
        width | height | imageData      | expectedLayers
        3     | 2      | "123456789012" | [[1, 2, 3, 4, 5, 6], [7, 8, 9, 0, 1, 2]]
    }

    def "Layer which contains the fewest 0 digits"() {
        given:
        SpaceImageDecoder sid = new SpaceImageDecoder((imageData))
        List<List<Integer>> layers = sid.decode(width, height)

        when:
        List<Integer> fewestDigitsLayer = sid.layerWithFewest0Digits(layers)

        then:
        fewestDigitsLayer == expectedLayer

        where:
        width | height | imageData      | expectedLayer
        3     | 2      | "123456789012" | [1, 2, 3, 4, 5, 6]
    }

    def "Multiply the number of 1 digits by the number of 2 digits"() {
        given:
        SpaceImageDecoder sid = new SpaceImageDecoder((imageData))
        List<List<Integer>> layers = sid.decode(width, height)
        List<Integer> fewestDigitsLayer = sid.layerWithFewest0Digits(layers)

        when:
        int number = sid.multiplyDigits(fewestDigitsLayer)

        then:
        number == expectedNumber

        where:
        width | height | imageData      | expectedNumber
        3     | 2      | "123456789012" | 1
    }

    def "Read input data and transform the image"() {
        given:
        String dataInputPath = "day8/input.txt"
        String imageData = FileReader.readFile(dataInputPath)
        SpaceImageDecoder sid = new SpaceImageDecoder(imageData)
        List<List<Integer>> layers = sid.decode(width, height)
        List<Integer> fewestDigitsLayer = sid.layerWithFewest0Digits(layers)

        when:
        int number = sid.multiplyDigits(fewestDigitsLayer)

        then:
        number == expectedNumber

        where:
        width | height | expectedNumber
        25    | 6      | 1463
    }


    def "Convert #imageData layers into pixels #width x #height"() {
        given:
        SpaceImageDecoder sid = new SpaceImageDecoder((imageData))
        List<List<Integer>> layers = sid.decode(width, height)

        when:
        List<List<Integer>> grouped = sid.transpose(layers)

        then:
        grouped == expectedGrouped

        where:
        width | height | imageData          | expectedGrouped
        2     | 2      | "0222112222120000" | [[0, 1, 2, 0], [2, 1, 2, 0], [2, 2, 1, 0], [2, 2, 2, 0]]
    }

    def "Should remove transparent pixels"() {
        given:
        SpaceImageDecoder sid = new SpaceImageDecoder(imageData)
        List<List<Integer>> layers = sid.decode(width, height)
        List<List<Integer>> grouped = sid.transpose(layers)

        when:
        List<Integer> filtered = sid.firstVisiblePixel(grouped);

        then:
        filtered == expectedGrouped

        where:
        width | height | imageData          | expectedGrouped
        2     | 2      | "0222112222120000" | [0, 1, 1, 0]
    }

    def "Create image by #imageData"() {
        given:
        SpaceImageDecoder sid = new SpaceImageDecoder((imageData))

        when:
        List<String> image = sid.createImage(width, height)

        then:
        image == expectedImage

        where:
        width | height | imageData          | expectedImage
        2     | 2      | "0222112222120000" | [" ", "#", "#", " "]
    }


    def "Print image by #imageData"() {
        given:
        SpaceImageDecoder sid = new SpaceImageDecoder("0222112222120000")

        when:
        boolean isPrinted = sid.print(width, height)

        then:
        isPrinted

        where:
        width | height | imageData
        2     | 2      | "0222112222120000"
    }

    def "Print image by inputData"() {
        given:
        String dataInputPath = "day8/input.txt"
        String imageData = FileReader.readFile(dataInputPath)
        SpaceImageDecoder sid = new SpaceImageDecoder(imageData)

        when:
        boolean isPrinted = sid.print(width, height)

        then:
        isPrinted

        where:
        width | height
        25    | 6
    }
}
