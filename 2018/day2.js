import { readFile } from './common';

export const loadInput = () => readFile('day2.txt');

export const containsTwice = (letters) => {
  const letter = letters.split('');
  for (let i = 0; i < letter.length; i++) {
    let letterCounts = 0;
    const first = letter[i];
    for (let j = 0; j < letter.length; j++) {
      if (i !== j && first === letter[j]) {
        letterCounts += 1;
      }
    }
    if (letterCounts > 0 && letterCounts < 2) {
      return true;
    }
  }

  return false;
};

export const containsThree = (letters) => {
  const letter = letters.split('');
  for (let i = 0; i < letter.length; i++) {
    let letterCounts = 0;
    const first = letter[i];
    for (let j = 0; j < letter.length; j++) {
      if (i !== j && first === letter[j]) {
        letterCounts += 1;
      }
    }

    if (letterCounts === 2) {
      return true;
    }
  }

  return false;
};

export const countTwice = ids => ids.filter(id => containsTwice(id)).length;

export const countThree = ids => ids.filter(id => containsThree(id)).length;

export const checksum = (ids) => {
  const twoLettersCount = countTwice(ids);
  const threeLettersCount = countThree(ids);

  return twoLettersCount * threeLettersCount;
};

export const hasOnlyOneDifference = (box1, box2) => {
  let differenceCount = 0;
  for (let i = 0; i < box1.length; i++) {
    if (box1[i] !== box2[i]) {
      differenceCount++;
    }
  }
  return differenceCount <= 1;
};

export const extractCommonLetters = (box1, box2) => {
  let commonLetters = '';
  for (let i = 0; i < box1.length; i++) {
    if (box1[i] === box2[i]) {
      commonLetters += box1[i];
    }
  }
  return commonLetters;
};


export const commonLetters = (ids) => {
  for (let i = 0; i < ids.length; i++) {
    const box1 = ids[i].split('');
    for (let j = 0; j < ids.length; j++) {
      if (i !== j) {
        const box2 = ids[j].split('');
        if (hasOnlyOneDifference(box1, box2)) {
          return extractCommonLetters(box1, box2);
        }
      }
    }
  }

  return undefined;
};
