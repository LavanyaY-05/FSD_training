import axios from "axios";
import { useEffect, useState } from "react"
import { Link, useNavigate, useParams } from "react-router-dom"

const ConfirmBooking = () => {


  const {id}= useParams()
  const [booking,setBooking] = useState();
    const confirmBookingapi = "http://localhost:8080/api/bookings/confirm/"

    const config = {
      headers:{
        'Authorization' : "Bearer " + localStorage.getItem('token')
      }
    }

    useEffect(()=>{
      const confirmBook = async()=>{
        try{
          const response = await axios.post(confirmBookingapi + id,{},config)
          setBooking(response.data)
        }
        catch(err){
          console.log(err)
        }
      }
      confirmBook()
    },[])
  



  return (
    <div className="container py-5">
      <div className="card mx-auto p-4 shadow-sm">

        <h4 className="text-center fw-bold mb-1">Booking Confirmed!</h4>
        <p className="text-center text-muted mb-3">Booking ID: {booking?.bookingId}</p>

        <div className="border rounded p-3 bg-light mb-3">
          <div className="d-flex gap-3 mb-2">
            <div className="car-thumb"></div>
            <div>
              <div className="fw-bold">{booking?.brand} {booking?.model}</div>
            </div>
          </div>
          <div className="small mb-1">🗓️ Pickup {booking?.pickupDateTime.split("T")[0]}  {booking?.pickupDateTime.split("T")[1]}</div>
          <div className="small mb-1">🗓️ DropDown {booking?.dropdownDateTime.split("T")[0]}  {booking?.dropdownDateTime.split("T")[1]}</div>

           
          <div className="small mb-1">📍 {booking?.pickupLocation}</div>
          <div className="small fw-bold">Total Paid: {booking?.totalAmount}</div>
        </div>

        <div className="d-flex gap-2 mb-2">
          <Link to= "/profile/myBookings"className="btn btn-orange flex-fill py-2">View My Bookings</Link>
          <Link to ="/cars"className="btn btn-outline-orange flex-fill py-2">Back to Home</Link>
        </div>

      </div>
    </div>
  )
}
export default ConfirmBooking