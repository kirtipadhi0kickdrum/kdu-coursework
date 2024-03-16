
import { Header } from './Header'
import { List } from './List'
import { AddItem } from './AddItem'

import { SearchItem } from './SearchItem'
import './TodoList.css'
import { Provider } from 'react-redux'
import store from '../redux/Store'


export function TodoList() {

    
  
  return (
    <Provider store={store}>
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
    </Provider>
    
    
  )
}


