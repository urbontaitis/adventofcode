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
        width | height | imageData                 | expectedLayers
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
        width | height | imageData                 | expectedLayer
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
        width | height | imageData                 | expectedNumber
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
}
