let addButton = document.getElementById("input-button");

function appendList()
{
    let contentBox = document.getElementById("input-text").value;
    let list = document.getElementById("unordered-list");
    let listItem= document.createElement("li");
    listItem.textContent = contentBox;

    if(contentBox === "")
    {
        return;
    }
    list.appendChild(listItem);
    let del = document.createElement("button");
    del.textContent = "delete";
    listItem.appendChild(del);

    del.addEventListener("click", () =>{
        list.removeChild(listItem);
    })
    
}

addButton.addEventListener("click", appendList);