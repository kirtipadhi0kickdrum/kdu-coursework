import { configureStore } from "@reduxjs/toolkit";
import addStock from './DataFetchStockPage.slice'
const store = configureStore({
    reducer: {
        addStock
    }
})
export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch;
export default store