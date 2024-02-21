import React, { useEffect, useState } from 'react';
import './App.scss'
import { ApiQuote } from './types/quotes.types';
import { Quote } from './Quotes';
import { BarLoader } from 'react-spinners';



function App() {

  const [allQuotes, setAllQuotes] = useState<ApiQuote[]>([])
  const [quotes, setQuotes] = useState<ApiQuote[]>([])
  const [filterTag, setFilterTag] = useState<string>('')
  const [search, setSearch] = useState<string>('')
  const [loading, setLoading] = useState<boolean>(false)
  

  // when search is updated, update the quotes as well
  useEffect(() => {
    let filteredQuote = allQuotes;
    if(filterTag)
    {
      filteredQuote = filteredQuote.filter(quote => quote.tags.includes(filterTag))
    }
    setQuotes(
      filteredQuote.filter((quote) => quote.content.toLowerCase().includes(search.toLowerCase())))
  }, [search, allQuotes, filterTag])


  const onSearchChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearch(e.target.value)
  }

  const handleRemoveTag = ():void => {
    setFilterTag('')
  }

  useEffect(()=>{
    // whatever code we put inside it runs only once 
    // make API call
    fetch("https://api.quotable.io/quotes/random?limit=3")
    .then((response) => response.json())
    .then((data:ApiQuote[])=>{setAllQuotes(data)})
  }, [])

  const generateNewKey = () => 
  {
    setLoading(true)
    fetch("https://api.quotable.io/quotes/random?limit=1")
    .then((response) => response.json())
    .then((data:ApiQuote[]) => setAllQuotes([data[0],...allQuotes]))
    .finally(()=>{
      setLoading(false)
    })
  }

  const filterItem = (tag: string): void => {
    setFilterTag(tag)
  }
  

  return (
    <>
    <div id="main-container">
      <div id="new-quote">
        <button id='new-quote-btn' onClick={generateNewKey} disabled={loading}>
          {loading? <BarLoader color='#ffffff'/>:'NEW QUOTE'}</button>
        <input type="text" value={search} onChange={onSearchChangeHandler} id='search-quote' placeholder='Search quote...'/>
      </div>
      
      <div id="filter-handle">
        <p id='tag'>{filterTag} <button id='del-btn' onClick={handleRemoveTag}>X</button></p>
        
        <hr />
      </div>
      
      {quotes.map((quote) => {
        return <Quote key={quote._id} quote={quote} filterItem={filterItem}/>
      })}
    </div>
      
    </>
  );
}

export default App;
