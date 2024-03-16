import { ApiQuote } from "./types/quotes.types"
import './Quotes.scss'

interface QuoteProps
{
    quote: ApiQuote
    filterItem: (tag: string) => void
}

export function Quote({quote, filterItem}:QuoteProps)
{
    return(
        <div id="quote-container">
            
            <p id="quote-content">
                {quote.content}
            </p>
            <div id="author-date">
                <p id="author">~{quote.author}</p>
                <p id="date">{quote.dateAdded.toString()}</p>
            </div>
            
            <div id="tag-container">
                {quote.tags.map((q) => {
                    return(
                        <>
                            <button id="tag" onClick={() => filterItem(q)}>{q}</button>
                        </>   
                    )
                })}
            </div>
        </div>
    )
}