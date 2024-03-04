import React, {  useState } from 'react'
import './AddItem.css'
import { useDispatch } from 'react-redux'
import { addItem } from '../redux/Actions'





export function AddItem() {


    const dispatch = useDispatch()
    const [inputText, setInputText] = useState('')

    const addItemToList = () => {
        dispatch(addItem({text: inputText}))
        setInputText('')
    }

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setInputText(event.target.value)
    }
        


    


  return (
    <div id='add-container'>
        <p id="add-item-title">Add Items</p>
        <div id="input-container">
            <input type="text" id='list-input' onChange={handleInputChange}/>
            <button id='add-item' onClick={addItemToList}>Submit</button>
        </div>
        
    </div>
    
  )
}
