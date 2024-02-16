const express = require('express')
const cors = require('cors')
const http = require('http')
const socketIO = require('socket.io');
const app = express();
const server = http.createServer(app);
const io = new socketIO.Server(server,{
    cors:{
        origin: "http://127.0.0.1:5500",
    }
});

app.use(cors())
app.use(express.json())

let stockPrice = 0;

function priceChangeMaker()
{
    setInterval(() => {
        const priceChange = Math.floor((Math.random() * 1001) - 500) ;

        stockPrice += priceChange;

        io.emit('priceUpdate', {
            timeInterval: new Date().toLocaleTimeString(),
            priceChange: priceChange,
        })
    }, 1000);
}

app.get('/', (req, res) => {
    res.json({
        initialPrice: 100
    })
})
priceChangeMaker()






const PORT = process.env.PORT || 3000
server.listen(PORT, () => {
    console.log("listening on port 3000")
})




