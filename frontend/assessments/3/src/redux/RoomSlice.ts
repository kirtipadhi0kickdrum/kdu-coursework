import { PayloadAction,  createAsyncThunk, createSlice } from "@reduxjs/toolkit"

  
  
 
  export const fetchRooms = async (): Promise<IRoomType[]> => {
    try {
      const response = await fetch('https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json');
      if (!response.ok) {
        throw new Error('Failed to fetch rooms');
      }
      const data: { roomTypes: IRoomType[] } = await response.json();
      return data.roomTypes;
    } catch (error) {
      console.error('Error fetching rooms', error);
      throw error;
    }
  };


export const fetchRoomsAsync = createAsyncThunk('fetchData', async () => {
    try{
        const response = await fetchRooms()
        console.log(response)
        return response
    }
    catch(error)
    {
        console.error('error fetching data', error)
    }
   
})

export interface TopLevel {
    roomTypes: IRoomType[];
}

export interface IRoomType {
    id:           number;
    name:         string;
    costPerNight: string;
    currency:     string;
    addOns:       AddOn[];
}

export interface AddOn {
    name:     string;
    cost:     string;
    currency: string;
}



interface RoomTypeState {
    availableRooms: IRoomType[]
    selectedRoom: IRoomType | null;
    selectedDates: { startDate: null | Date; endDate: null | Date };
    selectedAddOns: AddOn[];
    totalCost: number;
  }
  
  const initialState: RoomTypeState = {
    availableRooms: [],
    selectedRoom: null,
    selectedDates: { startDate: null, endDate: null },
    selectedAddOns: [],
    totalCost: 0,
  };




const roomTypeSlice = createSlice({
    name: 'roomType',
    initialState,
    reducers: {
        setAvailableRooms: (state, action: PayloadAction<IRoomType[]>) => {
            state.availableRooms = action.payload || []
        },
        selectRoom: (state, action: PayloadAction<IRoomType>) => {
            state.selectedRoom = action.payload
        },
        selectDates: (state, action: PayloadAction<{startDate: Date; endDate: Date}>) => {
            state.selectedDates = action.payload
        },
        selectAddOns: (state, action: PayloadAction<AddOn[]>) => {
            state.selectedAddOns = action.payload
        },
        calculateTotalCost: (state) => {
            if(state.selectedRoom && state.selectedDates.startDate && state.selectedDates.endDate)
            {
                const roomCost = parseInt(state.selectedRoom.costPerNight, 10)
                const numberOfDays = Math.ceil(
                    (state.selectedDates.endDate.getTime() - state.selectedDates.startDate.getTime()) / (1000*60*60*24)
                )

                const addOnsCost = state.selectedAddOns.reduce(
                    (total, addOn) => total + parseInt(addOn.cost, 10), 0
                )

                const totalCost = (roomCost + addOnsCost) * numberOfDays
                state.totalCost = totalCost
            }
            else{
                state.totalCost = 0
            }

        }

        
    },
    extraReducers: (builder) => {
        builder
        .addCase(fetchRoomsAsync.fulfilled, (state, action) => {
            if(action.payload)
            {
                state.availableRooms = action.payload
            }
        })
    }

})

export default roomTypeSlice.reducer

export const {
    
    setAvailableRooms,
    selectRoom,
    selectDates,
    selectAddOns,
    calculateTotalCost
} = roomTypeSlice.actions


