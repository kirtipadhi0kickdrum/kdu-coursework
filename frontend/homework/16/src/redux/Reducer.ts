
import { PayloadAction } from "@reduxjs/toolkit";
import { fetch_success, fetch_failure} from "./Action";
import { IData } from "../App";
export interface IAppState{
    productData: IData[]
    error: string
}

const initialState: IAppState = {
    productData: [],
    error: ''
}

const reducer = (state = initialState, action: PayloadAction<IData[]>): IAppState => {
    switch(action.type)
    {
        case fetch_success:
            return{
                ...state, productData: action.payload,
                error: ''
            }
        case fetch_failure:
            return{
                ...state, productData: [], error: action.payload.toString()
            }
        default:
            return state
    }
}

export default reducer