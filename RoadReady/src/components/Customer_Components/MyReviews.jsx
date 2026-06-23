import axios from "axios"
import { useEffect, useState } from "react"

 const MyReviews = () =>{

    const api = "http://localhost:8080/api/review/by-customer"


    const[reviews,setReviews] = useState([])
    const[page,setPage] = useState(0)
        const[size,setSize] = useState(5)

        const[totalPage,setTotalPage] = useState([])


    const config = {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
    }

    useEffect(()=>{
        const getReviews = async() =>{
           try{
             const response = await axios.get(`${api}?page=${page}&size=${size}`,config)
             setReviews(response.data.reviews)
             console.log(response.data)
           }
           catch(err){
             console.log(err)
           }
        }
        getReviews()
    },[])



    return(
      <div className="dashboard-main py-4 px-3">
            {/* Top Header Row matching Dashboard Inventory layout */}
            <div className="container align-items-center" style={{ backgroundColor: 'white' }}>
                <div className="d-flex align-items-center justify-content-between m-4 flex-wrap gap-3">
                <div>
                    <h2 className="fw-bold mb-1" style={{ color: '#2d2d2d' }}>Customer Feedback & Reviews</h2>
                </div>
            </div>
            {
                reviews.map((r,index)=>(
                    <div className="row mx-0">
                <div className="col-12 col-md-8 col-lg-12 p-0"> 
                    
                    <div className="card shadow-sm border-0 rounded-4 mb-3" style={{ backgroundColor: 'white' }}>
                        <div className="card-body p-4">
                            
                            <div className="d-flex justify-content-between align-items-center mb-3 flex-wrap gap-2">
                                <div className="d-flex gap-1">


                                    {Array.from({ length: r.rating }).map((_, i) => (
                                        <i key={i} className="bi bi-star-fill" style={{ color: '#FFB800', fontSize: '1.1rem' }}></i>
                                    ))}
                                </div>
                                <span className="text-muted small fw-semibold">
                                    {r.createdAt.split('T')[0]}
                                </span>
                            </div>

                            {/* Comment Text with Explicit Line Breaking Utilities */}
                            <p className="card-text mb-0 lh-base text-break" style={{ color: '#4a4a4a', fontSize: '0.95rem', fontWeight: '400' }}>
                                {r.comment}
                            </p>
                        </div>
                    </div>

                </div>
            </div>

                ))
            }

         </div>
        </div>

    )
 }
 export default MyReviews