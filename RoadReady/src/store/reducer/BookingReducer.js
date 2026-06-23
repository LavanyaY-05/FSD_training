const initialState = {
    bookings: []
}

export const bookingReducer = (state= initialState, action)=>{
    if(action.type === 'GET_ALL'){
        return{
            ...state,
            bookings:action.payload
        }
    }
    
    return state
}