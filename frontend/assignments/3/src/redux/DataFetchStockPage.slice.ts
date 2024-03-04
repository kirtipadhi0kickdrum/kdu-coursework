import { PayloadAction,   createSlice } from "@reduxjs/toolkit";
import { RootState } from './Store';


export interface IStock {
    stock_name:   string;
    stock_symbol: string;
    base_price:   number;
}


interface StockTypeState{
    allStocks: IStock[]
}

const initialState: StockTypeState = {
    allStocks: []
}




// export const fetchStock = async (): Promise<IStock[]> => {
//     try {
//       const response = await fetch('https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/stocks.json');
//       if (!response.ok) {
//         throw new Error('Failed to fetch stock data');
//       }
//       const data: { stockData: IStock[] } = await response.json();
//       return data.stockData;
//     } catch (error) {
//       console.error('Error parsing the API', error);
//       throw error;
//     }
//   };
  

// export const fetchStockAsync = createAsyncThunk('fetchData', async () => {
//     try{
//         const response = await fetchStock()
//         return response
//     }
//     catch(error)
//     {
//         console.error('error parsing the data', error)
//         throw error
//     }
// })

const stocksSlice = createSlice({
    name: 'stocks',
    initialState,
    reducers:{
        addStock: (state, action: PayloadAction<IStock[]>) =>{
            state.allStocks = action.payload
        },
    },
    // extraReducers: (builder) => {
    //     builder.addCase(fetchStockAsync.fulfilled, (state, action) => {
    //         state.allStocks = action.payload
    //     })
    // }
})

export const addStock = stocksSlice.actions
export const getStockSelector = (state: RootState) => state.addStock.allStocks
export default stocksSlice.reducer

