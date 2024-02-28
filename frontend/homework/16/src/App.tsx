import React, {  createContext, useEffect, useState } from 'react'
import './App.scss'
import { ProductList } from './Product/ProductList';
import { Header } from './Product/Header';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { ProductDetails } from './Product/ProductDetails';
import { Provider, useDispatch } from 'react-redux';
import store, { AppDispatch } from './redux/Store';
import { fetchItems } from './redux/ItemsThunk';
import { setError } from './redux/SnackbarSlice';


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


export interface IData{
      id:          number;
      title:       string;
      price:       number;
      description: string;
      category:    ICategory;
      image:       string;
      rating:      IRating;
}

export interface ItemsState{
  items: IData[]
  loading: boolean
  error: string | null
  
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
  const dispatch: AppDispatch = useDispatch();
  const [loading, setLoading] = useState(false)

  const [productData, setProductData] = useState<IData[]>([])

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true); 
        const actionResult = await dispatch(fetchItems());
  
        if (fetchItems.fulfilled.match(actionResult)) {
          const data = actionResult.payload;
          setProductData(data);
        } else {
          dispatch(setError("Failed to fetch items"));
        }
      } catch (error) {
        console.error("Error fetching items:", error);
        dispatch(setError("Failed to fetch items"));  
      } finally {
        setLoading(false);  
      }
    };
  
    fetchData();
  }, [dispatch, setProductData]);


  return (
    <Provider store={store}>
      <BrowserRouter>
        <div id='main-container'>
          
          <ProductContext.Provider value={{ productData, setProductData }}>
            <div id='header'>
              <Header />
            </div>
            <div id='title'>
              <p id='title-text'>KDU MARKETPLACE</p>
            </div>
            {loading && (
            <div id='loading-indicator'>
              <div className="loader"></div>
            </div>
            )}
            <Routes>
              <Route path='' element={<ProductList />} />
              <Route path='/product/:productId' element={<ProductDetails />} />
            </Routes>
          </ProductContext.Provider>
        </div>
      </BrowserRouter>
    </Provider>
    
    
      
    
    
  )
}
