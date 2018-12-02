const fs = require('fs');

export const loadInput = () => {
  const text = fs.readFileSync('./data/day1.txt').toString('utf-8');
  return text.split('\n').map(t => Math.trunc(t));
};

export const frequencyResult = frequencies => frequencies.reduce((x, y) => x + y);

export const calculateFrequencies = (startFrequency, frequencies) => {
  let lastFrequency = startFrequency;
  const secondFreq = new Array(frequencies.length - 1);
  // eslint-disable-next-line no-plusplus
  for (let i = 0; i < frequencies.length; i++) {
    lastFrequency += frequencies[i];
    secondFreq.push(lastFrequency);
  }

  return secondFreq.filter(x => true);
};


export const firstFrequency = (frequencies) => {
  const initialFrequencies = calculateFrequencies(0, frequencies);
  let freqSum = initialFrequencies[frequencies.length - 1];

  let possibleFreqIndex = -1;
  // eslint-disable-next-line no-plusplus
  for (let c = 0; c < 100000; c++) {
    const nextFrequencies = calculateFrequencies(freqSum, frequencies);

    // eslint-disable-next-line no-plusplus
    for (let i = 0; i < initialFrequencies.length; i++) {
      // eslint-disable-next-line no-plusplus
      for (let j = 0; j < nextFrequencies.length; j++) {
        // console.log(`comparing: ${initialFrequencies[i]} === ${nextFrequencies[j]}`)
        if (initialFrequencies[i] === nextFrequencies[j]) {
          if (i > 0 && i < frequencies.length) {
            if (possibleFreqIndex === -1) {
              possibleFreqIndex = i;
            } else if (i < possibleFreqIndex) {
              possibleFreqIndex = i;
            }
          }
        }
      }
    }

    if (possibleFreqIndex !== -1) {
      return initialFrequencies[possibleFreqIndex];
    } else {
      freqSum = nextFrequencies[frequencies.length - 1];
    }
  }

  return undefined;
};
