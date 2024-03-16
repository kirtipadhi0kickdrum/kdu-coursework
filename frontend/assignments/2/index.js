const express = require('express')
const bodyParser = require('body-parser')
const cors = require('cors')
const http = require('http')
const socketIo = require('socket.io')
const multer = require('multer')



const app = express()
const server = http.createServer(app)
const io = new socketIo.Server(server, {
    cors:{
        origin: "http://127.0.0.1:5500"
    }
})

app.use(bodyParser.json())
app.use(cors())
app.use(express.json())




io.on('connection', (socket) => {
    console.log('New user connected');

    socket.on('message', (message) => {
        console.log('Message:', message);
      
        socket.broadcast.emit('new-message', { message, sender: socket.id });
    });
});

// io.on('connection', (socket) => {
//     console.log('New user connected');

//     socket.on('join-room') {
//         const roomId = 1;
//         socket.join(roomId);

//         if (!rooms[roomId]) {
//             rooms[roomId] = {
//                 users: [socket.id],
//                 messages: [],
//             };
//         } else {
//             rooms[roomId].users.push(socket.id);
//         }

//         io.to(socket.id).emit('room-details', rooms[roomId]);
//     };

//     socket.on('message', (payload) => {
//         const { roomId, message } = payload;

//         if (!rooms[roomId]) {
//             return; // Room doesn't exist
//         }

//         rooms[roomId].messages.push({ user: socket.id, message });
//         io.to(roomId).emit('new-message', { user: socket.id, message });
//     });

//     socket.on('disconnect', () => {
//         for (const roomId in rooms) {
//             rooms[roomId].users = rooms[roomId].users.filter((user) => user !== socket.id);

//             if (rooms[roomId].users.length === 0) {
//                 delete rooms[roomId]; // Remove the room if no users are left
//             }
//         }
//     });
// });

const users = [
    {
        id: "1",
        user_name: "kirti",
        user_email_id:"kirti@gmail.com",
        password: "password",
        profile_url: "https://kirti.com/profile"
    },
    {
        id:"2",
        user_name: "naruto",
        user_email_id: "naruto@gmail.com",
        password: "password",
        profile_url:"https://naruto.com/profile"
    }
]

app.post('/api/user/login', (req, res) => {
    const { username, password } = req.body;

    const user = users.find(u => u.user_name === username && u.password === password)

    if(user)
    {
        res.status(200).json({message: 'Login successful', redirectUrl: '/index.html'});
    }
    else{
        res.status(401).json({message: 'Invalid cresentials'});
    }
})


const postsDatabase = [
    {
        id: 1,
        content: 'https://plus.unsplash.com/premium_photo-1707674002376-a736b180986d?w=1000&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyfHx8ZW58MHx8fHx8'
    },
    {
        id: 2,
        content: 'this kirti hi guys'
    },
    {
        id: 3,
        content: 'https://images.unsplash.com/photo-1708022808984-b8056f0ea8ae?w=1000&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw0fHx8ZW58MHx8fHx8'
    },
    {
        id: 4,
        content: 'https://plus.unsplash.com/premium_photo-1676977395508-9eec938ef7ee?w=1000&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw3fHx8ZW58MHx8fHx8'
    },
    {
        id: 5,
        content: 'https://plus.unsplash.com/premium_photo-1664094921465-40ae9484ab97?w=1000&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwxMnx8fGVufDB8fHx8fA%3D%3D'
    },
    {
        id: 6,
        content: 'valar morghulis'
    }
]

const storage = multer.memoryStorage()
const upload = multer({storage: storage})



app.post('/api/posts',  (req, res) => {
    try{
        const postContent = req.body.content
        

        if(!postContent )
        {
            return res.status(400).send({error: 'Content or image is required.'})
        }

        const postId = postsDatabase.length + 1
        const post = {
            id: postId,
            content: postContent,
        
        }

        postsDatabase.unshift(post)
        console.log(postsDatabase)

        res.status(200).send({message: 'Post created successfully!', postId:postId})
    }
    catch(error)
    {
        console.error(error);
        res.status(500).send({error: 'Internal server error'})
    }
})


app.get('/api/posts', (req, res) => {
    const page = parseInt(req.query.page)||1
    const size = parseInt(req.query.size)||5
    const startIndex = (page -1)*size
    const endIndex = startIndex + size

    const sortedPosts = postsDatabase.sort((a, b) => b.id - a.id)

    const pagedPosts = sortedPosts.slice(startIndex, endIndex)
    res.json(pagedPosts)
})


app.get('/api/post/:id', (req, res) => {
    const postId = parseInt(req.params.id);

    
    const post = postsDatabase.find(post => post.id === postId);

    if (!post) {
        
        res.status(404).json({ error: 'Post not found' });
    } else {
        
        res.json(post);
    }
});


const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
    console.log(`Server us running on ${PORT}`)
})