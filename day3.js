import { readFile } from './common';

export const mapToObject = (row) => {
  const temp = row.split(/\s/);

  const id = Math.trunc(temp[0].match(/[0-9]+/)[0]);

  const coordinates = temp[2].split(',');
  const left = Math.trunc(coordinates[0]);
  const top = Math.trunc(coordinates[1].replace(':', ''));

  const area = temp[3].split('x');
  const wide = Math.trunc(area[0]);
  const tall = Math.trunc(area[1]);

  return {
    id, left, top, wide, tall,
  };
};

export const loadInput = () => readFile('day3.txt').map(mapToObject);

export const countOverlapping = (claims) => {
  const tiles = {};
  claims.forEach((c) => {
    for (let x = +c.left; x < +c.left + +c.wide; ++x) {
      for (let y = +c.top; y < +c.top + +c.tall; ++y) {
        tiles[`${x},${y}`] = (tiles[`${x},${y}`] || 0) + 1;
      }
    }
  });
  return Object.values(tiles).filter(t => t > 1).length;
};

const isOverlaps = (x, y) => ((x.left < y.left + y.wide) && (x.top < y.top + y.tall)
  && (y.left < x.left + x.wide) && (y.top < x.top + x.tall));

export const findNonOverlapingClaimId = (claims) => {
  for (let i = 0; i < claims.length; ++i) {
    let loneClaim = true;
    for (let j = 0; j < claims.length; ++j) {
      if (i === j) {
        continue;
      }
      if (isOverlaps(claims[i], claims[j])) {
        loneClaim = false;
        break;
      }
    }
    if (loneClaim) {
      return claims[i].id;
    }
  }
  return undefined;
};
