const initialState = {
    characters : []
}

export const characterReducer = (state = initialState, action) =>{
    if(action.type === 'GET_BY_PAGE'){
        return{
            ...state,
            characters:action.payload
        }
    }

    return state
} 