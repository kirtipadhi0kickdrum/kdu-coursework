import { createAsyncThunk } from '@reduxjs/toolkit';
import { IData } from '../App';

export const fetchItems = createAsyncThunk<IData[]>('items/fetchItems', async () => {
  try {
    const response = await fetch('https://fakestoreapi.com/products');
    if (!response.ok) {
        console.log("failed to fetch items");
      throw new Error('Failed to fetch items');
    }
    const data: IData[] = await response.json();
    console.log(data)
    return data;
  } catch (error) {
    throw new Error('Error fetching items: ' + (error as Error).message);
  }
});