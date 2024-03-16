import React from 'react'
import { Header } from './Header'
import { List } from './List'
import { AddItem } from './AddItem'
import { useState } from 'react'
import { SearchItem } from './SearchItem'
import './TodoList.css'

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
    
  const handleDeleteItem = (id: number) => {
    const newList = list.filter((item) => item.id !== id);
    setList(newList);
};
  
  return (
    <>
    <div id="main-container">
      
      <SearchItem list={list} setList={setList}/>
      
      <div id="details-container">
        <AddItem list={list} setList={setList}/>
        <div className='todo-list'>
        <Header />
        <List listItems={list} onDeleteItem={handleDeleteItem}/>
        </div>
      </div>
      
    </div>
      
    </>
    
  )
}
