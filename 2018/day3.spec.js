import {
  loadInput, countOverlapping, findNonOverlapingClaimId, mapToObject,
} from './day3';

describe('Day 3', () => {
  describe('Test data from Advent Of Code', () => {
    test('load puzzle input as array', () => {
      expect(loadInput())
        .toBeInstanceOf(Array);
    });

    test('count overlapping claims', () => {
      const claims = loadInput();

      expect(countOverlapping(claims)).toBe(120408);
    });

    test('find non overlaping claim id', () => {
      const claims = loadInput();

      expect(findNonOverlapingClaimId(claims)).toBe(1276);
    });
  });

  describe('Transformations with rows', () => {
    test('map a row "#123 @ 3,2: 5x4" to the object', () => {
      const row = '#123 @ 3,2: 5x4';
      const expected = {
        id: 123, left: 3, top: 2, wide: 5, tall: 4,
      };

      expect(mapToObject(row)).toEqual(expected);
    });
    test('map a row "#1270 @ 776,510: 24x29" to the object', () => {
      const row = '#1270 @ 776,510: 24x29';
      const expected = {
        id: 1270, left: 776, top: 510, wide: 24, tall: 29,
      };

      expect(mapToObject(row)).toEqual(expected);
    });
  });

  describe('Claims', () => {
    const CLAIMS = [
      {
        id: 1, left: 1, top: 3, wide: 4, tall: 4,
      },
      {
        id: 2, left: 3, top: 1, wide: 4, tall: 4,
      }, {
        id: 3, left: 5, top: 5, wide: 2, tall: 2,
      }];

    test('count overlapping claims', () => {
      expect(countOverlapping(CLAIMS)).toBe(4);
    });

    test('find non overlapping claim id', () => {
      expect(findNonOverlapingClaimId(CLAIMS)).toBe(3);
    });
  });
});
