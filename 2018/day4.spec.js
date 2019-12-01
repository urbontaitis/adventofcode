import {
  loadInput, calculatePart1Answer, calculatePart2Answer,
  mapToObject, sortEventsByDate, guardsSchedule,
  findLongestSleepId, mostCommonSleepTime,
} from './day4';

const PUZZLE_ARRAY_DATA = [
  '[1518-11-01 00:00] Guard #10 begins shift',
  '[1518-11-01 00:05] falls asleep',
  '[1518-11-01 00:25] wakes up',
  '[1518-11-01 00:30] falls asleep',
  '[1518-11-01 00:55] wakes up',
  '[1518-11-01 23:58] Guard #99 begins shift',
  '[1518-11-02 00:40] falls asleep',
  '[1518-11-02 00:50] wakes up',
  '[1518-11-03 00:05] Guard #10 begins shift',
  '[1518-11-03 00:24] falls asleep',
  '[1518-11-03 00:29] wakes up',
  '[1518-11-04 00:02] Guard #99 begins shift',
  '[1518-11-04 00:36] falls asleep',
  '[1518-11-04 00:46] wakes up',
  '[1518-11-05 00:03] Guard #99 begins shift',
  '[1518-11-05 00:45] falls asleep',
  '[1518-11-05 00:55] wakes up',
];

describe('Day 4', () => {
  describe('Test data from Advent Of Code', () => {
    test('load puzzle input as array', () => {
      expect(loadInput())
        .toBeInstanceOf(Array);
    });

    test('Calculate P1 answer', () => {
      const events = loadInput();

      expect(calculatePart1Answer(events)).toBe(12504);
    });

    test.skip('Calculate P2 answer', () => {
      const events = loadInput();

      expect(calculatePart2Answer(events)).toBe(139543);
    });
  });

  test('Transform row to the object', () => {
    const row = '[1518-05-30 00:04] Guard #2417 begins shift';
    const expected = { date: '1518-05-30 00:04', event: 'Guard #2417 begins shift' };
    expect(mapToObject(row)).toEqual(expected);
  });

  test('Sort events by date', () => {
    const events = [
      { date: '1518-10-20 00:48', event: 'wakes up' },
      { date: '1518-05-30 00:04', event: 'Guard #2417 begins shift' },
      { date: '1518-08-12 00:14', event: 'falls asleep' },
    ];

    const expected = [
      { date: '1518-05-30 00:04', event: 'Guard #2417 begins shift' },
      { date: '1518-08-12 00:14', event: 'falls asleep' },
      { date: '1518-10-20 00:48', event: 'wakes up' },
    ];

    expect(sortEventsByDate(events)).toEqual(expected);
  });

  test('The guards sleep times and longest sleep', () => {
    const testData = [
      { date: '1518-11-01 00:00', event: 'Guard #10 begins shift' },
      { date: '1518-11-01 00:05', event: 'falls asleep' },
      { date: '1518-11-01 00:10', event: 'wakes up' },
      { date: '1518-11-01 23:58', event: 'Guard #99 begins shift' },
      { date: '1518-11-02 00:10', event: 'falls asleep' },
      { date: '1518-11-02 00:11', event: 'wakes up' },
    ];

    const expected = {
      10: {
        length: 5,
        minutes: [5, 6, 7, 8, 9],
      },
      99: {
        length: 1,
        minutes: [10],
      },
    };

    expect(guardsSchedule(testData)).toEqual(expected);
  });

  test('The guards sleep times and longest sleep - multiple days', () => {
    const testData = PUZZLE_ARRAY_DATA.map(d => mapToObject(d));

    const expected = {
      10: {
        length: 50,
        minutes: [5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 24, 25, 26, 27, 28],
      },
      99: {
        length: 30,
        minutes: [40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54],
      },
    };

    expect(guardsSchedule(testData)).toEqual(expected);
  });

  test('Sleep longest', () => {
    const guards = {
      99: {
        length: 10,
        minutes: [30, 40],
      },
      10: {
        length: 45,
        minutes: [0, 45],
      },
    };

    expect(findLongestSleepId(guards)).toBe(10);
  });

  test('Most common sleep time', () => {
    const minutes = [5, 10, 5, 11];
    expect(mostCommonSleepTime(minutes)).toBe(5);
  });

  test('Calculate demo puzzle answer', () => {
    const events = PUZZLE_ARRAY_DATA.map(d => mapToObject(d));

    expect(calculatePart1Answer(events)).toBe(240);
  });

  test('find the guard which is most frequently asleep on the same minute', () => {
    const events = PUZZLE_ARRAY_DATA.map(d => mapToObject(d));

    expect(calculatePart2Answer(events)).toBe(4455);
  });
});
