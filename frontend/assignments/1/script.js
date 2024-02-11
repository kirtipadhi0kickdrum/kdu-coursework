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
}




function createPost() {
    
    const userInput = document.getElementById('textarea').value;

 
    if (userInput.trim() === '') {
        alert('Please enter something before posting.');
        return;
    }

    
    const newPost = document.createElement('div');
    newPost.id = 'post-2'; 
    newPost.className = 'posts';
    newPost.style.display = 'grid';
    newPost.style.width = 'auto';
    newPost.style.border = '1px white solid';
    newPost.style.padding = '10px';
    newPost.style.gridTemplateColumns = '1fr 3fr 1fr';
    newPost.style.gridTemplateRows = '1fr 2fr 1fr';

   
    const imageContainer = document.createElement('div');
    imageContainer.id = 'image';
    imageContainer.style.gridRowStart = '1';
    imageContainer.style.gridRowEnd = '4';
    imageContainer.style.marginRight = '5px';

    const profileImage = document.createElement('img');
    profileImage.style.width = '50px';
    profileImage.style.height = '50px';
    profileImage.style.borderRadius = '50%';
    profileImage.src = 'twiiter base line images/twiiter base line images/Profile/profile pic.png';
    profileImage.alt = '';

    imageContainer.appendChild(profileImage);


    const nameContainer = document.createElement('div');
    nameContainer.id = 'name';
    nameContainer.style.gridColumnStart = '2';
    nameContainer.style.gridColumnEnd = '4';

    const nameParagraph = document.createElement('p');
    nameParagraph.innerHTML = '<b>Nitesh Gupta</b> @nit_hck . 1s';

    nameContainer.appendChild(nameParagraph);


    const textContainer = document.createElement('div');
    textContainer.id = 'text';
    textContainer.style.gridRowStart = '2';
    textContainer.style.gridRowEnd = '3';
    textContainer.style.gridColumnStart = '2';
    textContainer.style.gridColumnEnd = '4';

    const textParagraph = document.createElement('p');
    textParagraph.textContent = userInput;

    textContainer.appendChild(textParagraph);




    // Create the icons container
    const iconsContainer = document.createElement('div');
    iconsContainer.id = 'icons';
    iconsContainer.style.gridRowStart = '3';
    iconsContainer.style.gridRowEnd = '4';
    iconsContainer.style.gridColumnStart = '2';
    iconsContainer.style.gridColumnEnd = '3';
    iconsContainer.style.display = 'grid';
    iconsContainer.style.gridTemplateColumns = '1fr 1fr 1fr 1fr';
    iconsContainer.style.gridColumnGap = '60px';


    // icon - 1
    const svgElement1 = document.createElement('svg');
    svgElement1.setAttribute('viewBox', '0 0 24 24');
    svgElement1.setAttribute('aria-hidden', 'true');
    svgElement1.setAttribute('class', 'r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi');
    svgElement1.style.color = 'lightgrey';
    const gElement1 = document.createElement('g');
    gElement1.setAttribute('fill', 'lightgrey');
    const pathElement1 = document.createElement('path');
    pathElement1.setAttribute('d', 'M1.751 10c0-4.42 3.584-8 8.005-8h4.366c4.49 0 8.129 3.64 8.129 8.13 0 2.96-1.607 5.68-4.196 7.11l-8.054 4.46v-3.69h-.067c-4.49.1-8.183-3.51-8.183-8.01zm8.005-6c-3.317 0-6.005 2.69-6.005 6 0 3.37 2.77 6.08 6.138 6.01l.351-.01h1.761v2.3l5.087-2.81c1.951-1.08 3.163-3.13 3.163-5.36 0-3.39-2.744-6.13-6.129-6.13H9.756');
    gElement1.appendChild(pathElement1);
    svgElement1.appendChild(gElement1);
    iconsContainer.appendChild(svgElement1);


    // icon -2 
    const svgElement2 = document.createElement('svg');
    svgElement2.setAttribute('viewBox', '0 0 24 24');
    svgElement2.setAttribute('aria-hidden', 'true');
    svgElement2.setAttribute('class', 'r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi');
    svgElement2.style.color = 'lightgrey';
    const gElement2 = document.createElement('g');
    gElement2.setAttribute('fill', 'lightgrey');
    const pathElement2 = document.createElement('path');
    pathElement2.setAttribute('d', 'M4.5 3.88l4.432 4.14-1.364 1.46L5.5 7.55V16c0 1.1.896 2 2 2H13v2H7.5c-2.209 0-4-1.79-4-4V7.55L1.432 9.48.068 8.02 4.5 3.88zM16.5 6H11V4h5.5c2.209 0 4 1.79 4 4v8.45l2.068-1.93 1.364 1.46-4.432 4.14-4.432-4.14 1.364-1.46 2.068 1.93V8c0-1.1-.896-2-2-2z');
    gElement2.appendChild(pathElement2);
    svgElement2.appendChild(gElement2);
    iconsContainer.appendChild(svgElement2);



    // icon - 3
    const clickableSvgContainer = document.createElement('div');
    clickableSvgContainer.id = 'clickableSvgContainer';
    clickableSvgContainer.style.display = 'grid';
    clickableSvgContainer.style.gridTemplateColumns = '1fr 1fr';
    const clickableSvg = document.createElement('svg');
    clickableSvg.id = 'clickableSvg';
    clickableSvg.setAttribute('data-count', '0');
    clickableSvg.setAttribute('viewBox', '0 0 24 24');
    clickableSvg.setAttribute('aria-hidden', 'true');
    clickableSvg.setAttribute('class', 'r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi');
    const gElement = document.createElement('g');
    gElement.setAttribute('fill', 'lightgrey');
    const pathElement = document.createElement('path');
    pathElement.setAttribute('d', 'M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z');
    const countText = document.createElement('text');
    countText.id = 'countText';
    countText.setAttribute('x', '12');
    countText.setAttribute('y', '20');
    countText.setAttribute('text-anchor', 'middle');
    countText.setAttribute('font-size', '10');
    countText.setAttribute('fill', 'black');
    countText.textContent = '0';
    gElement.appendChild(pathElement);
    clickableSvg.appendChild(gElement);
    clickableSvgContainer.appendChild(clickableSvg);
    clickableSvgContainer.appendChild(countText);
    iconsContainer.appendChild(clickableSvgContainer);

   
    // icon - 4
    const svgElement4 = document.createElement('svg');
    svgElement4.setAttribute('viewBox', '0 0 24 24');
    svgElement4.setAttribute('aria-hidden', 'true');
    svgElement4.setAttribute('class', 'r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi');
    svgElement4.style.color = 'lightgrey';
    const gElement4 = document.createElement('g');
    gElement4.setAttribute('fill', 'lightgrey');
    const pathElement4 = document.createElement('path');
    pathElement4.setAttribute('d', 'M8.75 21V3h2v18h-2zM18 21V8.5h2V21h-2zM4 21l.004-10h2L6 21H4zm9.248 0v-7h2v7h-2z');
    gElement4.appendChild(pathElement4);
    svgElement4.appendChild(gElement4);
    iconsContainer.appendChild(svgElement4);








    // Create the save icon container
    const saveIconContainer = document.createElement('div');
    saveIconContainer.id = 'save-icon';
    saveIconContainer.style.gridRowStart = '3';
    saveIconContainer.style.gridRowEnd = '4';
    saveIconContainer.style.gridColumnStart = '4';
    saveIconContainer.style.gridColumnEnd = '4';
    saveIconContainer.style.display = 'grid';
    saveIconContainer.style.gridColumnGap = '10px';
    saveIconContainer.style.gridTemplateColumns = '1fr 1fr';



    // icon -5
    const svgElement5 = document.createElement('svg');
    svgElement5.setAttribute('viewBox', '0 0 24 24');
    svgElement5.setAttribute('aria-hidden', 'true');
    svgElement5.setAttribute('class', 'r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1nao33i r-lwhw9o r-cnnz9e');
    svgElement5.style.color = 'lightgrey';
    const gElement5 = document.createElement('g');
    gElement5.setAttribute('fill', 'lightgrey');
    const pathElement5 = document.createElement('path');
    pathElement5.setAttribute('d', 'M4 4.5C4 3.12 5.119 2 6.5 2h11C18.881 2 20 3.12 20 4.5v18.44l-8-5.71-8 5.71V4.5zM6.5 4c-.276 0-.5.22-.5.5v14.56l6-4.29 6 4.29V4.5c0-.28-.224-.5-.5-.5h-11');
    gElement5.appendChild(pathElement5);
    svgElement5.appendChild(gElement5);
    saveIconContainer.appendChild(svgElement5);



    //icon-6
    const svgElement6 = document.createElement('svg');
    svgElement6.setAttribute('viewBox', '0 0 24 24');
    svgElement6.setAttribute('aria-hidden', 'true');
    svgElement6.setAttribute('class', 'r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi');
    svgElement6.style.color = 'lightgrey';
    const gElement6 = document.createElement('g');
    gElement6.setAttribute('fill', 'lightgrey');
    const pathElement6 = document.createElement('path');
    pathElement6.setAttribute('d', 'M12 2.59l5.7 5.7-1.41 1.42L13 6.41V16h-2V6.41l-3.3 3.3-1.41-1.42L12 2.59zM21 15l-.02 3.51c0 1.38-1.12 2.49-2.5 2.49H5.5C4.11 21 3 19.88 3 18.5V15h2v3.5c0 .28.22.5.5.5h12.98c.28 0 .5-.22.5-.5L19 15h2');
    gElement6.appendChild(pathElement6);
    svgElement6.appendChild(gElement6);
    saveIconContainer.appendChild(svgElement6);




    newPost.appendChild(imageContainer);
    newPost.appendChild(nameContainer);
    newPost.appendChild(textContainer);
    newPost.appendChild(iconsContainer);
    newPost.appendChild(saveIconContainer);

   
    const postContainer = document.getElementById('post-container');
    postContainer.appendChild(newPost);

    document.getElementById('textarea').value = '';
}
