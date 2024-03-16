import axios from "axios";
import { Action } from "redux";
import { IData } from "../App";
import { ThunkAction } from "redux-thunk";
import { IAppState } from "./Reducer";

export const fetch_success = 'fetch_success'
export const fetch_failure = 'fetch_failure'


export const fetchProductsSuccess = (data: IData[]) => ({
    type: fetch_success as typeof fetch_success,
    payload: data
})

export const fetchProductsFailure = (error: string) => ({
    type: fetch_failure as typeof fetch_failure,
    payload: error
})






export const fetchProducts = (): ThunkAction<void, IAppState, unknown, Action<string>> => {
    return async (dispatch) => {
      try {
        const response = await axios.get<IData[]>('https://fakestoreapi.com/products');
        dispatch(fetchProductsSuccess(response.data));
      } catch (error) {
        console.error('error fetching data: ', error);
        dispatch(fetchProductsFailure('Error fetching data'));
      }
    };
  };



