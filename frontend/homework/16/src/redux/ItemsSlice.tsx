import { createSlice, PayloadAction } from '@reduxjs/toolkit';

import {  IData, ItemsState } from '../App';
import { fetchItems } from './ItemsThunk';

const initialState: ItemsState = {
  items: [],
  loading: false,
  error: null,
};

const itemsSlice = createSlice({
  name: 'items',
  initialState,
  reducers: {},
  extraReducers(builder)  {

    builder.addCase(fetchItems.pending, (state) => {3
      state.loading = true;
      state.error = null;
    }).addCase(fetchItems.fulfilled, (state, action: PayloadAction<IData[]>) => {
      state.loading = false;
      state.items = action.payload;
      state.error = null;
    })
    .addCase(fetchItems.rejected, (state, action) => {
        state.loading = false;
        state.error =action.error.message ?? 'an error occured'
    })

  },
});

export default itemsSlice.reducer;