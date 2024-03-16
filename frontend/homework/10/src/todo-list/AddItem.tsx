import React, { Dispatch, SetStateAction, useState } from 'react'
import './AddItem.css'

interface IListItem
{
    id: number,
    text: string
}

interface IListProp
{
    list: IListItem[],
    setList: Dispatch<SetStateAction<IListItem[]>>
    
}


export function AddItem({list, setList}:IListProp) {
    const [inputText, setInputText] = useState('')

    const addItemToList = () => {
        const newItem: IListItem = {
            id: list.length + 1,
            text: inputText
        }

        setList([...list, newItem])
        setInputText('')
        
    }

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setInputText(event.target.value)
    }


  return (
    <>
    <div id='add-container'>
        <p id="add-item-title">Add Items</p>
        <div id="input-container">
            <input type="text" id='list-input' onChange={handleInputChange}/>
            <button id='add-item' onClick={addItemToList}>Submit</button>
        </div>
        
    </div>

    </>
    
  )
}
