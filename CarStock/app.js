const express =  require('express');
const app = express();
// (fs) służy do obsługi operacji plikowych 
const fs = require('fs');
app.use(express.static(__dirname));//funkcja konczy serwerowania plikow

app.get('/:id', (req, res) => {
    const carsData = fs.readFileSync('./data.json');
    console.log("siema")
    const cars = JSON.parse(carsData);
    const car = cars.find(car => car.id == req.params.id); //   pozwala na pobieranie wartości z parametrów ścieżki URL (id) przez funkcje parms

    if (!car) {
        res.send('Samochód nie został znaleziony'); // zwraca błąd gdy samochód nie został znaleziony
    } else {
        const car_id = document.querySelector("mid-box1");
        const car_model = document.querySelector("mid-box2");
        const car_modelYear = document.querySelector("mid-box3");
        const car_power = document.querySelector("mid-box4");
        const car_color = document.querySelector("mid-box5");
       // res.send(print_car=car.id); // wyświetla informacje o samochodzie
         res.send(car_id=car.id);
         //res.send(`car_id: ${car.model}, rok produkcji: ${car.modelYear}, moc: ${car.power}, kolor: ${car.color}`);
    }
});

app.listen(3000, () => {
    console.log("serwer dziala na procie 3000");
});




