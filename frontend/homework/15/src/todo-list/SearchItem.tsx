import React from 'react'
import './SearchItem.css'
import { useDispatch, useSelector } from 'react-redux'
import { setSearchText } from '../redux/Actions'
import { TodoListState } from '../redux/Store'

export function SearchItem() {
    const dispatch = useDispatch()
    const {list, searchText} = useSelector((state: TodoListState) => state.todo)

    const handleSearchText = (event: React.ChangeEvent<HTMLInputElement>) => {
        const newSearchText = event.target.value
        dispatch(setSearchText(newSearchText))
    }

    const filteredList = list.filter((item) =>
      item.text.toLowerCase().includes(searchText.toLowerCase())
    )


  return (
    <div id="search-container">
            <p id='search-title'>Items Lister</p>
            <input type="text" id='searchInput' value={searchText} placeholder='  Search Items...' onChange={handleSearchText} />
            {filteredList.length === 0 && <p>No items found</p>}
        </div>
    
  )
}
