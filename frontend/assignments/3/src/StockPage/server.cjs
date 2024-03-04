/* eslint-disable @typescript-eslint/no-var-requires */
/* eslint-disable no-undef */
const express = require('express');
const http = require('http');
const { Server: SocketIoServer } = require('socket.io');
const cors = require('cors')

const app = express();
app.use(cors())
const server = http.createServer(app);
const io = new SocketIoServer(server);

const PORT = process.env.PORT || 3001;

io.on('connection', (socket) => {
  console.log('A user connected');


  socket.on('updatePrice', (newPrice) => {
    io.emit('priceUpdate', newPrice); 
  });

  socket.on('disconnect', () => {
    console.log('User disconnected');
  });
});

server.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
