import { createReducer } from "@reduxjs/toolkit"
import { addItem, deleteItem, setSearchText } from "./Actions"


export interface IListItem{
    id:number,
    text:string
}

interface IAppState{
    list: IListItem[],
    searchText: string
}

const initialState: IAppState = {
    list: [
        {id:1, text:'List Item 1'},
        {id:2, text:'List Item 2'}
    ],
    searchText: ''
}

export const rootReducer = createReducer(initialState, (builder) => {
    builder
        .addCase(addItem, (state, action) => {
            const newItem: IListItem = {
                id:state.list.length + 1,
                text: action.payload.text,
            }
            state.list.push(newItem)
        })
        .addCase(deleteItem, (state, action) => {
            state.list = state.list.filter((item) => item.id !== action.payload)
        })
        .addCase(setSearchText, (state, action) => {
            state.searchText = action.payload
        })
})

