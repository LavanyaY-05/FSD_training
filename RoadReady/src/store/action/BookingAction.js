import axios from "axios"

const getConfig = ({
    headers: {
        'Authorization': "Bearer " + localStorage.getItem('token')
    }
});

const getAllApi = "http://localhost:8080/api/bookings/customer?status="

export const getAll = (status) =>{
    return async(dispatch) =>{
        const response = await axios.get(getAllApi + status,getConfig)
        let action = {
            type: 'GET_ALL',
            payload : response.data
        }
        dispatch(action)

    }
}