import { loadInput, frequencyResult, firstFrequency } from './day1';

describe('Day 1', () => {
  describe.skip('Load puzzle data', () => {
    test('load puzzle input as array', () => {
      expect(loadInput()).toBeInstanceOf(Array);
    });

    test('load all array items', () => {
      expect(loadInput().length).toBe(991);
    });

    // FIXME calculates incorrect value
    test.skip('frequency result of real data', () => {
      const frequencies = loadInput();
      expect(firstFrequency(frequencies)).toBe(256);
    });

    test('frequency result of real data', () => {
      const frequencies = loadInput();
      expect(frequencyResult(frequencies)).toBe(433);
    });
  });

  describe('Sum frequencies', () => {
    test('frequency result of positive numbers', () => {
      const frequencies = [1, 1, 1];
      expect(frequencyResult(frequencies)).toBe(3);
    });

    test('frequency result of negative numbers', () => {
      const frequencies = [-1, -2, -3];
      expect(frequencyResult(frequencies)).toBe(-6);
    });

    test('frequency result of postive and negative numbers', () => {
      const frequencies = [1, 1, -2];
      expect(frequencyResult(frequencies)).toBe(0);
    });
  });

  describe('First frequency it reaches twice', () => {
    test('+1, -1 first reaches 0 twice.', () => {
      const frequencies = [1, -1];
      expect(firstFrequency(frequencies)).toBe(0);
    });

    test('+3, +3, +4, -2, -4 first reaches 10 twice', () => {
      const frequencies = [3, 3, 4, -2, -4];
      expect(firstFrequency(frequencies)).toBe(10);
    });

    test('-6, +3, +8, +5, -6 first reaches 5 twice.', () => {
      const frequencies = [-6, 3, 8, 5, -6];
      expect(firstFrequency(frequencies)).toBe(5);
    });

    test('+7, +7, -2, -7, -4 first reaches 14 twice.', () => {
      const frequencies = [7, 7, -2, -7, -4];
      expect(firstFrequency(frequencies)).toBe(14);
    });
  });
});
