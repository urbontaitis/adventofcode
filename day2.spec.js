import {
  loadInput, countThree, countTwice, containsTwice, checksum, containsThree,
  extractCommonLetters, hasOnlyOneDifference, commonLetters,
} from './day2';

/**
 * For example, if you see the following box IDs:

 abcdef contains no letters that appear exactly two or three times.
 bababc contains two a and three b, so it counts for both.
 abbcde contains two b, but no letter appears exactly three times.
 abcccd contains three c, but no letter appears exactly two times.
 aabcdd contains two a and two d, but it only counts once.
 abcdee contains two e.
 ababab contains three a and three b, but it only counts once.

 Of these box IDs,
 four of them contain a letter which appears exactly twice,
 and three of them contain a letter which appears exactly three times.

 Multiplying these together produces a checksum of 4 * 3 = 12.

 */

describe('Day 2', () => {
  describe('contains at least one pair of two letters', () => {
    test('abcdef contains no letters that appear exactly two or three times.', () => {
      const count = containsTwice('abcdef');
      expect(count).toBe(false);
    });
    test('bababc contains two a and three b, so it counts for both.', () => {
      const count = containsTwice('bababc');
      expect(count).toBe(true);
    });
    test('abbcde contains two b, but no letter appears exactly three times.', () => {
      const count = containsTwice('abbcde');
      expect(count).toBe(true);
    });
    test('abcccd contains three c, but no letter appears exactly two times.', () => {
      const count = containsTwice('abcccd');
      expect(count).toBe(false);
    });
    test('aabcdd contains two a and two d, but it only counts once.', () => {
      const count = containsTwice('aabcdd');
      expect(count).toBe(true);
    });
    test('abcdee contains two e.', () => {
      const count = containsTwice('abcdee');
      expect(count).toBe(true);
    });
    test('ababab contains three a and three b, but it only counts once.', () => {
      const count = containsTwice('ababab');
      expect(count).toBe(false);
    });
  });
  describe('contains at least one pair of three letters', () => {
    test('abcdef contains no letters that appear exactly two or three times.', () => {
      const count = containsThree('abcdef');
      expect(count).toBe(false);
    });
    test('bababc contains two a and three b, so it counts for both.', () => {
      const count = containsThree('bababc');
      expect(count).toBe(true);
    });
    test('abbcde contains two b, but no letter appears exactly three times.', () => {
      const count = containsThree('abbcde');
      expect(count).toBe(false);
    });
    test('abcccd contains three c, but no letter appears exactly two times.', () => {
      const count = containsThree('abcccd');
      expect(count).toBe(true);
    });
    test('aabcdd contains two a and two d, but it only counts once.', () => {
      const count = containsThree('aabcdd');
      expect(count).toBe(false);
    });
    test('abcdee contains two e.', () => {
      const count = containsThree('abcdee');
      expect(count).toBe(false);
    });
    test('ababab contains three a and three b, but it only counts once.', () => {
      const count = containsThree('ababab');
      expect(count).toBe(true);
    });
  });
  describe('count letters', () => {
    test('which appears exactly twice', () => {
      const ids = ['abcdef', 'bababc', 'abbcde', 'abcccd', 'aabcdd', 'abcdee', 'ababab'];
      const appearsTwice = countTwice(ids);
      expect(appearsTwice).toBe(4);
    });
    test('which appears exactly three times', () => {
      const ids = ['abcdef', 'bababc', 'abbcde', 'abcccd', 'aabcdd', 'abcdee', 'ababab'];
      const appearsTwice = countThree(ids);
      expect(appearsTwice).toBe(3);
    });
  });
  describe('the checksum for list of box IDs', () => {
    test('calculate checksum', () => {
      const ids = ['abcdef', 'bababc', 'abbcde', 'abcccd', 'aabcdd', 'abcdee', 'ababab'];
      expect(checksum(ids)).toBe(12);
    });
  });
  describe('Calculate checksum for mine list of box IDs', () => {
    test('load puzzle input as array', () => {
      expect(loadInput())
        .toBeInstanceOf(Array);
    });
    test('calculate checksum', () => {
      const ids = loadInput();
      expect(checksum(ids)).toBe(5456);
    });
    test('Find the box which differ by one character ', () => {
      const ids = loadInput();
      expect(commonLetters(ids)).toBe('megsdlpulxvinkatfoyzxcbvq');
    });
  });
  describe('Find the boxes which have IDs differ by exactly one character at the same position in both strings.', () => {
    test('Has only one difference between ids', () => {
      const box1 = 'fghij'.split('');
      const box2 = 'fguij'.split('');
      expect(hasOnlyOneDifference(box1, box2)).toBe(true);
    });
    test('Has more than one difference between ids', () => {
      const box1 = 'abcde'.split('');
      const box2 = 'axcye'.split('');
      expect(hasOnlyOneDifference(box1, box2)).toBe(false);
    });
    test('Find the difference between ids', () => {
      const box1 = 'fghij'.split('');
      const box2 = 'fguij'.split('');
      expect(extractCommonLetters(box1, box2)).toBe('fgij');
    });
    test('Find the difference between ids', () => {
      const box1 = 'abcde'.split('');
      const box2 = 'axcye'.split('');
      expect(extractCommonLetters(box1, box2)).toBe('ace');
    });
    test('Find the box which differ by exactly one character', () => {
      const ids = ['abcde', 'fghij', 'klmno', 'pqrst', 'fguij', 'axcye', 'wvxyz'];
      expect(commonLetters(ids)).toBe('fgij');
    });
  });
});
