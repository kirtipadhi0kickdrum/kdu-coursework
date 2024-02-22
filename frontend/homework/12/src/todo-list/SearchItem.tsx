import React, {  useContext, useState } from 'react'
import './SearchItem.css'
import { listContext } from './TodoList'

export function SearchItem() {
    const {list, setList} = useContext(listContext)
    const [searchText, setSearcText] = useState('')
    const [noItemsFound, setNoItemsFound] = useState(false)

    const handleSearchText = (event: React.ChangeEvent<HTMLInputElement>) => {
        const newSearchText = event.target.value
        setSearcText(newSearchText)

        const filteredList = list.filter((item) => 
        item.text.toLowerCase().includes(newSearchText.toLowerCase()));
        
        setNoItemsFound(filteredList.length === 0)
        
        setList(filteredList)
    }


  return (
    <div id="search-container">
            <p id='search-title'>Items Lister</p>
            <input type="text" id='searchInput' value={searchText} placeholder='  Search Items...' onChange={handleSearchText} />
            {noItemsFound && <p>No items found</p>}
        </div>
    
  )
}
