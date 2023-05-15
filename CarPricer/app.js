const express =  require('express');
const app = express();
const fs = require('fs');

app.get('/price', (req, res) => {
    const carPrice = fs.readFileSync('./price.json');
    const price = JSON.parse(carPrice);
    res.send(price);
});

app.listen(8000, () => {
    console.log("serwer dziala na procie 8000");
});