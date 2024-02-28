
import { Header } from './Header'
import { List } from './List'
import { AddItem } from './AddItem'

import { SearchItem } from './SearchItem'
import './TodoList.css'
import { Provider } from 'react-redux'
import  { persistor, todoListStore } from '../redux/Store'
import { PersistGate } from 'redux-persist/integration/react'


export function TodoList() {

    
  
  return (
    <Provider store={todoListStore}>
      <PersistGate loading={null} persistor={persistor}>
      <div id="main-container">
          <SearchItem />
          <div id="details-container">
            <AddItem />
            <div className='todo-list'>
            <Header />
            <List />
            </div>
          </div>
      </div>
      </PersistGate>
        
    </Provider>
    
    
  )
}


