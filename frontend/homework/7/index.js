const express = require('express')
const cors = require('cors')
const http = require('http')
const socketIo = require('socket.io')

const app = express();
const server = http.createServer(app)
const io = new socketIo.Server(server, {
    cors:{
        origin: "http://127.0.0.1:5500"
    }
})


app.use(cors());
app.use(express.json())

io.on("connection", (socket) => {
    console.log("New user connected")
    socket.on("message", (payload) => {
        console.log('Payload', payload)
        io.except(socket.id).emit('new-message', payload)
    })
})

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
    console.log(`Listening on port ${PORT}`)
})