import { readFile } from './common';

export const mapToObject = (row) => {
  const temp = row.split('] ');
  return { date: temp[0].substring(1), event: temp[1] };
};

export const loadInput = () => readFile('day4.txt').map(r => mapToObject(r));

export const sortEventsByDate = events => events.sort((x, y) => (new Date(x.date) - new Date(y.date)));

export const guardsSchedule = (events) => {
  const guard = {};

  let id;
  let start = 0;
  events.forEach((el) => {
    const { date, event } = el;
    const time = Math.trunc(date.substring(14));

    if (event.includes('Guard')) {
      const pattern = /(\d)+/g;
      id = pattern.exec(event)[0];

      if (guard[id] === undefined) {
        guard[id] = { length: 0, minutes: [] };
      }
    } else if (event === 'falls asleep') {
      start = time;
    } else {
      guard[id].length += time - start;
      const curentMinutes = [...guard[id].minutes];
      const minutesRange = [...Array(time - start).keys()].map(i => i + start);
      guard[id].minutes = curentMinutes.concat(minutesRange);
    }
  });
  return guard;
};

export const findLongestSleepId = (guards) => {
  const sortedGuards = Object.keys(guards).sort((x, y) => guards[y].length - guards[x].length);
  return Number(sortedGuards[0]);
};

export const mostCommonSleepTime = (minutes) => {
  const counted = minutes.reduce((acc, curr) => {
    if (curr in acc) {
      acc[curr]++;
    } else {
      acc[curr] = 1;
    }

    return acc;
  }, {});

  const mode = Object.keys(counted).reduce((a, b) => (counted[a] > counted[b] ? a : b), 0);

  return Number(mode);
};

export const calculatePart1Answer = (events) => {
  const sortedEvents = sortEventsByDate(events);
  const guardSchedule = guardsSchedule(sortedEvents);
  const guardId = findLongestSleepId(guardSchedule);
  const sleepTime = mostCommonSleepTime(guardSchedule[guardId].minutes);

  return guardId * sleepTime;
};

export const calculatePart2Answer = (events) => {
  const sortedEvents = sortEventsByDate(events);
  const guardSchedule = guardsSchedule(sortedEvents);

  let maxSleepTime = 0;
  let answer = 0;
  Object.keys(guardSchedule).forEach((guardId) => {
    const guard = guardSchedule[guardId];
    const sleepTime = mostCommonSleepTime(guard.minutes);
    if (sleepTime > maxSleepTime) {
      maxSleepTime = sleepTime;
      answer = guardId * maxSleepTime;
    }
  });

  return answer;
};
