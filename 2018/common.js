const fs = require('fs');

export const readFile = (file) => {
  const text = fs.readFileSync(`./data/${file}`).toString('utf-8');
  return text.split('\n');
};
