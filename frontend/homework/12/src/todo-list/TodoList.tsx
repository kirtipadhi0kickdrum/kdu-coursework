import React, { createContext, useMemo } from 'react'
import { Header } from './Header'
import { List } from './List'
import { AddItem } from './AddItem'
import { useState } from 'react'
import { SearchItem } from './SearchItem'
import './TodoList.css'


interface IListItem{
  id: number,
  text: string
}

interface IlistContext{
  listItem: IListItem
  list: IListItem[]
  setList: React.Dispatch<React.SetStateAction<IListItem[]>>
  handleDeleteItem: (id:number) => void
}

export const listContext = createContext<IlistContext>({
  listItem:{
    id: 1,
    text: ""
  },
  list: [
    {
      id: 1, 
      text: "List Item 1"
    },
    {
      id: 2,
      text: "List Item 2"
    }
  ],
  setList: () => {},
  handleDeleteItem: () => {}
})

export function TodoList() {
    const [list, setList] = useState([
      {
          id: 1,
          text: "List Item 1"
      },
      {
          id: 2,
          text: "List Item 2"
      }
  ])

  const listItem = useMemo(
    () => ({
      id:0,
      text:''
    }),[] 
  )
    
  


  const handleDeleteItem = (id: number) => {
    const newList = list.filter((item) => item.id !== id);
    setList(newList);
};
  
  return (
    <div id="main-container">
      <listContext.Provider value={{listItem, list, setList, handleDeleteItem}}>
      
        <SearchItem />
        
        <div id="details-container">
          <AddItem />
          <div className='todo-list'>
          <Header />
          <List />
          </div>
        </div>
      </listContext.Provider>
      
    </div>
    
  )
}


