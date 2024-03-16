import React, { useContext } from 'react'
import './List.css'

import { ListItem } from './ListItem';
import { listContext } from './TodoList';

export function List() {
    const {list, handleDeleteItem} = useContext(listContext)
    
  return (

    <ul id='list'>
        {
            list.map((item) => {
                return(
                    <ListItem key={item.id} text={item.text} onDelete={() => handleDeleteItem(item.id)}/>
                )
            })
        }
    </ul>
  )
}
