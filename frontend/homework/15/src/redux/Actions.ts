import { createAction } from "@reduxjs/toolkit";

export const addItem = createAction<{text: string}>('ADD_ITEM')
export const deleteItem = createAction<number>('DELETE_ITEM')
export const setSearchText = createAction<string>('SET_SEARCH_TEXT')