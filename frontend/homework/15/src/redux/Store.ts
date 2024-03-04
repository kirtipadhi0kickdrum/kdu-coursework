import { configureStore } from "@reduxjs/toolkit";
import { rootReducer } from "./Reducers";
import storage from "redux-persist/lib/storage";
import { persistReducer, persistStore } from "redux-persist";

const persistConfig = {
    key: 'root',
    storage: storage
}

const persistedReducer = persistReducer(persistConfig, rootReducer)

const todoListStore = configureStore({
    reducer: {
        todo: persistedReducer, 
    }
});

const persistor = persistStore(todoListStore)
export { todoListStore, persistor, configureStore };
export type TodoListState = ReturnType<typeof todoListStore.getState>;