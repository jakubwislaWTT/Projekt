const express =  require('express');
const app = express();
// (fs) służy do obsługi operacji plikowych 
const fs = require('fs');
app.use(express.static(__dirname));//funkcja konczy serwerowania plikow

app.get('/index.html/:id', (req, res) => {
    const carsData = fs.readFileSync('./data.json');
    console.log("siema")
    const cars = JSON.parse(carsData);
    const car = cars.find(car => car.id == req.params.id); //   pozwala na pobieranie wartości z parametrów ścieżki URL (id) przez funkcje parms

    if (!car) {
        res.send('Samochód nie został znaleziony'); // zwraca błąd gdy samochód nie został znaleziony
    } else {
        let car_id = document.querySelector("mid-box1");
       // res.send(print_car=car.id); // wyświetla informacje o samochodzie
         res.send(`Model: ${car.model}, rok produkcji: ${car.modelYear}, moc: ${car.power}, kolor: ${car.color}`);
    }


});
app.listen(3000, () => {
    console.log("serwer dziala na procie 3000");
});
