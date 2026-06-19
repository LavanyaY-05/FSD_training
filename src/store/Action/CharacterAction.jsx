import axios from "axios"

const api = "https://rickandmortyapi.com/api/character/?page="

export const getByPage = (page) =>{

    return async(dispatch) =>{
        const response = await axios.get(api+page)
        let action = {
            type: 'GET_BY_PAGE',
            payload: response.data.results
        }
        dispatch(action)
    }

}