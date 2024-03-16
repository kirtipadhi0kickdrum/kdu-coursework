import { configureStore } from "@reduxjs/toolkit";
import { rootReducer } from "./Reducers";

const todoListStore = configureStore({
    reducer: {
        todo: rootReducer, 
    }
});

export default todoListStore;

export type TodoListState = ReturnType<typeof todoListStore.getState>;