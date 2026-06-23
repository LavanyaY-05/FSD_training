import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";



const CustomerSideBar = () => {

   const CustomerApi = "http://localhost:8080/api/customers/customerprofile"
   const[Customer,setCustomer] = useState()

   useEffect(()=>{
      const config = {
         headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
      }
      const getCustomer = async() =>{
          const response =  await axios.get(CustomerApi, config)
          setCustomer(response.data)
          console.log(response)
      }
      getCustomer()
   },[])
    return (
         <aside className="admin-sidebar">
            {/* <!-- Profile Section --> */}
            <div className="profile-section">
                <div className="avatar-wrapper">
                    <div className="admin-avatar">CU</div>
                </div>
                {
                     Customer !== undefined ? 
                        <div>
                           <h3 className="admin-name">{Customer.firstname}{Customer.lastname}</h3>
                        </div>: ""
                     
                }
            </div>

            {/* <!-- Navigation Items --> */}
            <nav className="sidebar-nav">
                 <Link to="" className="nav-item active">
                    {/* Changed fas fa-tachometer-alt to bi bi-speedometer2 */}
                   <i className="bi bi-person-circle"></i>

                    <span>Profile<i className="bi bi-pencil"></i></span>
                 </Link>
                 <Link to="/profile/myBookings" className="nav-item">
                    {/* Changed fas fa-car to bi bi-car-front */}
                    <i className="bi bi-car-front"></i>
                    <span>My Bookings</span>
                 </Link>
                 <Link to="/profile/myReviews" className="nav-item">
                    {/* Changed fas fa-ticket-alt to bi bi-ticket-perforated */}
                    <i className="bi bi-ticket-perforated"></i>
                    <span>My Reviews</span>
                 </Link>
                 
            </nav>
         </aside>
    );
};

export default CustomerSideBar;
