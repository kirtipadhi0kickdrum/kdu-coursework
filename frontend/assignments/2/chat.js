        function redirectToHome()
        {
            window.location.href = 'index.html'
        }

        function kirtiChat()
        {
            const chatProfile = document.getElementById('chat-profile-icon-kirti')
            chatProfile.style.backgroundColor = 'rgba(173, 216, 230, 0.3)'
            const charUsername = document.getElementById('chat-with-username')
            charUsername.innerText  = 'KIRTI_PADHI'
        }
    
        function narutoChat()
        {
            const chatProfile = document.getElementById('chat-profile-icon-naruto')
            chatProfile.style.backgroundColor = 'rgba(173, 216, 230, 0.3)'
            const charUsername = document.getElementById('chat-with-username')
            charUsername.innerText  = 'NARUTO_UZUMAKI'
        }

        document.addEventListener('DOMContentLoaded', () => {
            const socket = io('http://localhost:3000');
            const inputMessage = document.getElementById('input-message')
            const sendButton = document.getElementById('sendMessage')
            const messageSpace = document.getElementById('messages')

            function addMessage(who, message)
            {
                if(who === "you")
                {
                    const element = document.createElement('p')
                    element.innerHTML += `${message}`
                    element.classList.add('message-from-you')
                    messageSpace.appendChild(element)
                }
                else{
                    const element = document.createElement('p')
                    element.innerHTML += `${message}`
                    element.classList.add('message-from-user')
                    messageSpace.appendChild(element)
                }   
            }


            sendButton.addEventListener('click', () => {
                const message = inputMessage.value;
                socket.emit("message",message);
                addMessage("you", message);
                inputMessage.value  = ''
            });

            socket.on("new-message", (data) => {
                console.log('Recieved new message', data)
                addMessage("user", data.message);
            });
        });

        

function openChatSpace()
{
    const chatRoom = document.getElementById('chat-room')
    const chatSpace = document.getElementById('chat-space')
    const name = document.getElementById('chat-with-username')

    chatRoom.style.display = 'none'
    chatSpace.style.display = 'grid'
    name.innerText = 'KIRTI_PADHI'
}

function openChatRoom()
{
    const chatRoom = document.getElementById('chat-room')
    const chatSpace = document.getElementById('chat-space')

    chatSpace.style.display = 'none'
    chatRoom.style.display = 'flex'
    chatRoom.style.borderLeft = 'none'
    chatRoom.style.borderRight = 'none'
    
}