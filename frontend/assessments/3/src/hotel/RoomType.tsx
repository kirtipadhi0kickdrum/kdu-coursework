import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import {
    
    fetchRoomsAsync,
    selectRoom,
    selectDates,
    selectAddOns,
    calculateTotalCost
} from '../redux/RoomSlice'
import {RootState} from '../redux/Store'




export function RoomType() {
    const dispatch = useDispatch()
    const availableRooms = useSelector((state: RootState) => state.roomType.availableRooms)
    const selectedRoom = useSelector((state:RootState) => state.roomType.selectedRoom)
    const selectedDate = useSelector((state: RootState) => state.roomType.selectedDates)
    const selectedAddOn = useSelector((state: RootState) => state.roomType.selectedAddOns)
    const totalCost = useSelector((state: RootState) => state.roomType.totalCost)

    const [selectedRoomId, setSelectedRoomId] = useState<number | null>(null)

    const handleRoomChange = (roomId: number)=> {
        const room = availableRooms.find((r) => r.id === roomId)
        if(room)
        {
            dispatch(selectRoom(room))
            setSelectedRoomId(roomId)
        }
    }

    const handleDateChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target
        dispatch(selectDates({
            ...selectDates, [name]: new Date(value),
            startDate: new Date(value),
            endDate: new Date(value)
        }))
    }

    const handleAddOnChange = (addOnName: string, checked: boolean) => {
        if(checked)
        {
            const addOn = selectedRoom?.addOns.find((addOn) => addOn.name === addOnName)
            if(addOn)
            {
                dispatch(selectAddOns([...selectedAddOn, addOn]))
            }
        }
        else{
            const updatedAddOns = selectedAddOn.filter((addOn) => addOn.name !== addOnName)
            dispatch(selectAddOns(updatedAddOns))
        }
    }

    useEffect(() => {
        dispatch(fetchRoomsAsync()) 
    }, [dispatch])

    useEffect(() => {
        dispatch(calculateTotalCost())
    }, [dispatch, selectedAddOn, selectedRoom, selectedDate])
  return (
    <div style={{backgroundColor: 'white', color: 'black', justifyContent: 'center', display:'flex' , flexDirection: 'column', width: '100%', height:'100%', alignItems: 'center'}}>
        <h2>Hotel Booking</h2>
        <div style={{ display: 'flex', flexDirection: 'column' }}>
        {availableRooms.map((room) => (
           
            <div
                key={room.id}
                style={{
                    border: '1px solid #ccc',
                    padding: '10px',
                    marginBottom: '10px',
                    cursor: 'pointer',
                    backgroundColor: selectedRoomId === room.id ? '#eee' : 'white',
                }}
                onClick={() => handleRoomChange(room.id)}>
                {room.name}
            </div>
        ))}
    </div>

        <h2>Dates</h2>
        <label>Start Date:</label>
        <input type="date" name='startDate' value={selectedDate.startDate ? selectedDate.startDate.toISOString().substr(0, 10) : ''} onChange={handleDateChange} />
        <label>End Date:</label>
        <input type="date" name='endDate' value={selectedDate.endDate?.toISOString().split('T')[0] || ''} onChange={handleDateChange}/>

        {selectedRoom && (
            <div>
                <h2>Add Ons</h2>
                {selectedRoom.addOns.map((addOn) => (
                    <div key={addOn.name}>
                        <input type="checkbox" id={addOn.name} checked={selectedAddOn.some((selectedAddOn) => selectedAddOn.name === addOn.name)} 
                        onChange={(e) => handleAddOnChange(addOn.name, e.target.checked)}/>
                        <label htmlFor={addOn.name}>
                            {addOn.name} - {addOn.cost} {addOn.currency}
                        </label>
                    </div>
                ))}
            </div>
        )}

        <h2>Total Cost</h2>
        <p>{totalCost} INR</p>
    </div>
  )
}
