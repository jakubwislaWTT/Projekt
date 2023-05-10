const express =  require('express');
const app = express();
const cors = require('cors');
// (fs) służy do obsługi operacji plikowych 
const fs = require('fs');
app.use(cors());
app.get('/vehicle/:id', (req, res) => {
    const carsData = fs.readFileSync('./data.json');
    const cars = JSON.parse(carsData);
    const car = cars.find(car => car.id == req.params.id); //   pozwala na pobieranie wartości z parametrów ścieżki URL (id) przez funkcje parms
    res.send({
        id: car.id,
        model: car.model,
        modelYear: car.model,
        power: car.power,
        color: car.red,
        img: car.img
      });
});

app.get('/allVehicles', (req, res) => {
    const carsData = fs.readFileSync('./data.json');
    const cars = JSON.parse(carsData);
    res.send(cars);
});

app.listen(3000, () => {
    console.log("serwer dziala na procie 3000");
});
