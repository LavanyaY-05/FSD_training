import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux"
import { getByPage } from "../store/Action/CharacterAction"

const Characters = () =>{

    const dispatch = useDispatch()

    const [page,setPage] = useState(1)
    const {characters} = useSelector(state => state.characters)

    const [characterArray, setCharacterArray] = useState([])
    useEffect(()=>{
        dispatch(getByPage(page))
    },[page])
    useEffect(() => {
    setCharacterArray(characters)
}, [characters])


useEffect(()=>{
        const get = async (page) =>{
            dispatch(getByPage(page))
            setCharacterArray(characters)

        }
        get(page)
    },[page])




    return(
        <div>

            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Status</th>
                        <th scope="col">Species</th>
                        <th scope="col">Origin Name</th>
                        <th scope="col">Location</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        characterArray.map((c, index) => (
                            <tr key={index}>
                                <td>{c?.name}</td>
                                <td>{c?.status}</td>
                                <td>{c?.species}</td>
                                <td>{(c?.origin?.name)}</td>
                                <td>{c?.location?.name}</td>
                                

                            </tr>
                        ))
                    }

                </tbody>
            </table>
            <nav aria-label="Page navigation example">
                <ul className="pagination">
                    <li className="page-item"><button className="page-link"  disabled = {page ==0} onClick={(e) => setPage(page - 1)}>Previous</button></li>
                    

                    <li className="page-item"><button className="page-link"  onClick={(e) => setPage(page + 1)} >Next</button></li>
                </ul>
            </nav>
        </div>
    )
}
export default Characters