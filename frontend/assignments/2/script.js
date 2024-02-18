

function handleClick() {
    const clickableSvg = document.getElementById('clickableSvg');
    const countText = document.getElementById('countText');

    const currentCount = parseInt(clickableSvg.getAttribute('data-count'), 10);
    const newCount = currentCount + 1;

    clickableSvg.setAttribute('data-count', newCount);
    countText.textContent = newCount;

    clickableSvg.innerHTML = `
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 36 36" fill="rgb(225,55,125)">
            <path d="M35.885 11.833c0-5.45-4.418-9.868-9.867-9.868-3.308 0-6.227 1.633-8.018 4.129-1.791-2.496-4.71-4.129-8.017-4.129-5.45 0-9.868 4.417-9.868 9.868 0 .772.098 1.52.266 2.241C1.751 22.587 11.216 31.568 18 34.034c6.783-2.466 16.249-11.447 17.617-19.959.17-.721.268-1.469.268-2.242z"/>
        </svg>
    `;

    countText.style.display = 'block';
    countText.style.marginLeft = '4px';
}

async function createPost() {
    const userInput = document.getElementById('textarea').value;

    try{
        const response = await fetch('http://localhost:3000/api/posts', {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json',
            },
            body: JSON.stringify({content: userInput}),
        })

        if(!response.ok)
        {
            throw new Error(`Error: ${response.status} - ${response.statusText}`)
        }

        await fetchAndDisplayPosts()
    }
    catch(error)
    {
        alert(`Failed to create post. ${error.message}`)
    }
}

function handlePlainText(text) {
    var postElement = document.getElementById('post-1');
    var newPostElement = postElement.cloneNode(true);
    var textElement = newPostElement.querySelector('#text > p:first-child');
    textElement.textContent = text;
    const postContainer = document.getElementById('post-container');
    postContainer.appendChild(newPostElement);
    document.getElementById('textarea').value = '';
}






function handleInput() {
    const inputBox = document.getElementById('textarea');
    const cursorPos = inputBox.selectionStart;


    const fileInput = document.createElement('input');
    fileInput.type = 'file';
    fileInput.accept = 'image/*,video/*';

   
    fileInput.addEventListener('change', (event) => {
        const selectedFile = event.target.files[0];

        if (selectedFile) {
            const fileType = selectedFile.type.split('/')[0];

            if (fileType === 'image') {
                const imageUrl = URL.createObjectURL(selectedFile);
                const imageMarkdown = `${imageUrl}`;
                inputBox.setRangeText(imageMarkdown, cursorPos, cursorPos, 'end');
            } else if (fileType === 'video') {
                const videoUrl = URL.createObjectURL(selectedFile);
                const videoMarkdown = `[Video](${videoUrl})`;
                inputBox.setRangeText(videoMarkdown, cursorPos, cursorPos, 'end');
            }
        }
    });

    fileInput.click();
}

let pageNumber = 1;
const pageSize = 5;

async function fetchAndDisplayPosts()
{
    try{
        const response = await fetch('http://localhost:3000/api/posts')
        const posts = await response.json();
        const postContainer = document.getElementById('post-container');
        
       

        posts.forEach(post => {
            const urlRegex = /^(https?|ftp):\/\/[^\s/$.?#].[^\s]*$/i;
            if (urlRegex.test(post.content)) {
                
                const img = new Image();
                img.onload = function () {
                    
                    var postElement = document.getElementById('post-1');
                    var newPostElement = postElement.cloneNode(true);
        
                  
                    var textElement = newPostElement.querySelector('#text > p:first-child');
                    textElement.textContent = '';
        
                    
                    var imageElement = newPostElement.querySelector('#image-post');
                    imageElement.src = post.content;
                    imageElement.alt = 'User provided image';
        
                    postContainer.appendChild(newPostElement);
                    document.getElementById('textarea').value = '';
                };
                img.onerror = function () {
                   
                    handlePlainText(post.content);
                };
                img.src = post.content;
            }
            else {
                
                handlePlainText(post.content);
            }
        })
        if(posts.length > 0)
        {
            pageNumber++
        }
    }
    catch(error)
    {
        console.error(`Failed to fetch posts.`)
    }
}

function isBottomOfPage(container){
    const scrollTop = container.scrollTop
    const containerHeight = container.clientHeight
    const contentHeight = container.scrollHeight

    return scrollTop + containerHeight >= contentHeight - 10;
}

const postContainer = document.getElementById('post-container')
postContainer.addEventListener('scroll', () =>{
    if(isBottomOfPage(postContainer))
    {
        fetchAndDisplayPosts()
    }
})


function openPosts()
{
    const navbar = document.getElementById('profile-container')
    const postInput = document.getElementById('post-container')

    navbar.style.display = 'none'
    postInput.style.display = 'grid'
}



document.addEventListener('DOMContentLoaded', fetchAndDisplayPosts)


