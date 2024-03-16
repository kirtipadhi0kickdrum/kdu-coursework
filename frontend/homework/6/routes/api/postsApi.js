const express = require('express');
const posts = require('../../tweets/posts');
const router = express.Router();


// get all posts
router.get('/', (req, res) => {
    res.json(posts);
});

// //get posts by id
router.get('/:id', (req, res) => {
    const found = posts.some(post => post.id === parseInt(req.params.id))

    if(found)
    {
        res.json(posts.filter(post => post.id === parseInt(req.params.id)))

    }
    else{
        res.status(404).json({message: `There are no posts with the id of ${req.params.id}`})
    }
})


// create a post 
router.post('/', (req, res) => {
    const postsLength = posts.length;
    const newPost = {
        id: postsLength+1,
        name: req.body.name,
        content: req.body.content
    }

    if(!newPost.name)
    {
        return res.status(404).json({message: 'Name column is empty'})
    }
    else if(!newPost.content)
    {
        return res.status(404).json({message: 'you cannot tweet without content'})
    }

    posts.push(newPost);
    res.json(posts[postsLength]);
})




module.exports = router;