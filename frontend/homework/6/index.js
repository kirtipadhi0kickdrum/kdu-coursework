const express = require('express');
const path = require('path')
const app = express();
const posts = require('./tweets/posts')

//Body parser middleware
app.use(express.json())
app.use(express.urlencoded({extended: false}));

app.use('/api/posts', require('./routes/api/postsApi'))

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => console.log(`Server started on port ${PORT}`))

