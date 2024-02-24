import React, {  createContext, useEffect, useState } from 'react'
import './App.scss'
import axios from 'axios';
import { ProductList } from './Product/ProductList';
import { Header } from './Product/Header';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { ProductDetails } from './Product/ProductDetails';
// import { Router } from 'react-router-dom';

interface IRating{
    rate:  number;
    count: number;
}

const enum ICategory{
    Electronics = "electronics",
    Jewelery = "jewelery",
    MenSClothing = "men's clothing",
    WomenSClothing = "women's clothing",
}


interface IData{
      id:          number;
      title:       string;
      price:       number;
      description: string;
      category:    ICategory;
      image:       string;
      rating:      IRating;
}

interface IProductContext{
  productData: IData[]
  setProductData: React.Dispatch<React.SetStateAction<IData[]>>
}

export const ProductContext = createContext<IProductContext>({
  productData: [],
  setProductData: () => {}
})


export function App() {

  const [productData, setProductData] = useState<IData[]>([])
  

  useEffect(() => {
    const fetchData = async () => {
      try{
        const response = await axios.get<IData[]>('https://fakestoreapi.com/products')
        setProductData(response.data)
      }
      catch(error)
      {
        console.error('error fetching data:', error)
      }
    }
    fetchData()
  }, [])

  return (
    <BrowserRouter>
      <div id='main-container'>
      <ProductContext.Provider value={{productData, setProductData}}>
          <div id='header'>
            <Header/>
          </div>
          <div id="title">
            <p id='title-text'>KDU MARKETPLACE</p>
          </div>
          <Routes>
            <Route path='' element={<ProductList/>}/>
            <Route path='/product/:productId' element={<ProductDetails/>}/>
            
          </Routes>
          
      </ProductContext.Provider>
    </div>
    </BrowserRouter>
    
      
    
    
  )
}
