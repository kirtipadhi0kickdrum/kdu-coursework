import React from 'react'
import './List.css'

import { ListItem } from './ListItem';


interface IListItem{
    id: number;
    text: string;
}


interface ListProps{
    listItems : IListItem[];
    onDeleteItem: (id: number) => void
}
export function List({listItems, onDeleteItem}: ListProps) {

    
  return (

    <ul id='list'>
        {
            listItems.map((item) => {
                return(
                    <ListItem key={item.id} text={item.text} onDelete={() => onDeleteItem(item.id)}/>
                )
            })
        }
    </ul>
  )
}
