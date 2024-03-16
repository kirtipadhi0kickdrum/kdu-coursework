
import "./ListItem.css"


interface ListItemProps {
    text: string;
    onDelete: () => void
}




export function ListItem({text, onDelete}:ListItemProps)
{
    
    return(
        <li id="list-item">
            {text}
            <button onClick={onDelete} id="delete-button">X</button>
        </li>
    )
}