import React, { useContext, useEffect, useRef, useState } from "react"
import { ProductContext } from "../App"
import './Header.scss'


export function Header() {
    const {setProductData, productData} = useContext(ProductContext)
    const searchInputRef = useRef<HTMLInputElement | null>(null)
    const [selectedCategory, setSelectedCategory] = useState<string | null>(null)
    const [selectedSortOrder, setSelectedSortOrder] = useState<string>('none')

    // eslint-disable-next-line react-hooks/exhaustive-deps
    const handleSearch = () => {
        if(searchInputRef.current)
        {
            const searchQuery = searchInputRef.current.value.toLowerCase();
            applyFilters(searchQuery, selectedCategory, selectedSortOrder)
        }
    }

    const handleCategoryChange = (e : React.ChangeEvent<HTMLSelectElement>) => {
        const selectedValue = e.target.value
        setSelectedCategory(selectedValue === 'all' ? null:selectedValue)
        applyFilters(searchInputRef.current?.value.toLowerCase(), selectedValue)
    }

    const handleSortOrderChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const selectedValue = e.target.value
        setSelectedSortOrder(selectedValue)
        applyFilters(searchInputRef.current.value.toLowerCase(), selectedCategory, selectedValue)
    }

    const applyFilters = (searchQuery: string, category: string | null, sortOrder: string) => {
        let filteredProducts = productData;

        filteredProducts = filteredProducts.filter((prod) => prod.title.toLowerCase().includes(searchQuery))

        if(category)
        {
            filteredProducts = filteredProducts.filter((prod) => prod.category === category)
        }

        if(sortOrder === 'ascending')
        {
            filteredProducts = filteredProducts.sort((a, b) => a.price - b.price)

        }
        else if(sortOrder === 'descending')
        {
            filteredProducts = filteredProducts.sort((a, b) => b.price - a.price)
        }
        setProductData([...filteredProducts])
    }


    useEffect(() => {
        if(searchInputRef.current)
        {
            searchInputRef.current.addEventListener('input', handleSearch)
        }

        return() => {
            if(searchInputRef.current)
            {
                // eslint-disable-next-line react-hooks/exhaustive-deps
                searchInputRef.current.removeEventListener('input', handleSearch)
            }
        }
    }, [setProductData, selectedCategory, handleSearch, selectedSortOrder])
 
  return (
    <div id="header-container">
        <div id="search">
            <input type="text" name="" id="search-input" ref={searchInputRef}/>
        </div>
        <div id="filter">
            <div id="filter-tag">
                <label htmlFor="category-select">Filter: </label>
                <select name="category" id="category-select" onChange={handleCategoryChange} value={selectedCategory || 'all'}>
                    <option value="all">All Categories</option>
                    <option value="electronics">Electronics</option>
                    <option value="jewelery">Jewelery</option>
                    <option value="men's clothing">Men's Clothing</option>
                    <option value="women's clothing">Women's Clothing</option>
                </select>
            </div>
            <div id="sort">
                <label htmlFor="sort-select">Sort: </label>
                <select name="sort" id="sort-select" onChange={handleSortOrderChange} value={selectedSortOrder}>
                    <option value="none">None</option>
                    <option value="ascending">Ascending</option>
                    <option value="descending">Descending</option>
                </select>
            </div>
        </div>
    </div>
  )
}
