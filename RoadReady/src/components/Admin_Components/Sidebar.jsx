import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";



const Sidebar = () => {

   const AdminApi = "http://localhost:8080/api/admin/adminprofile"
   const[admin,setAdmin] = useState()

   useEffect(()=>{
      const config = {
         headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
      }
      const getAdmin = async() =>{
          const response =  await axios.get(AdminApi, config)
          setAdmin(response.data)
          console.log(response)
      }
      getAdmin()
   },[])
    return (
         <aside className="admin-sidebar">
            {/* <!-- Profile Section --> */}
            <div className="profile-section">
                <div className="avatar-wrapper">
                    <div className="admin-avatar">AD</div>
                </div>
                {
                     admin !== undefined ? 
                        <div>
                           <h3 className="admin-name">{admin.firstname}{admin.lastname}</h3>
                <p className="admin-role">{admin.jobTitle}</p>
                        </div>: ""
                     
                }
            </div>

            {/* <!-- Navigation Items --> */}
            <nav className="sidebar-nav">
                 <Link to="" className="nav-item">
                    {/* Changed fas fa-tachometer-alt to bi bi-speedometer2 */}
                    <i className="bi bi-speedometer2"></i>
                    <span>Dashboard</span>
                 </Link>
                  <Link to="/admin/profile" className="nav-item">
                    {/* Changed fas fa-tachometer-alt to bi bi-speedometer2 */}
                   <i className="bi bi-person-circle"></i>


                    <span>Profile</span>
                 </Link>
                 <Link to="/admin/manageCars" className="nav-item">
                    {/* Changed fas fa-car to bi bi-car-front */}
                    <i className="bi bi-car-front"></i>
                    <span>Manage Cars</span>
                 </Link>
                 <Link to="/admin/manageCoupon" className="nav-item">
                    {/* Changed fas fa-ticket-alt to bi bi-ticket-perforated */}
                    <i className="bi bi-ticket-perforated"></i>
                    <span>Manage Coupons</span>
                 </Link>
                 <Link to="/admin/manageAgent" className="nav-item">
                    {/* Changed fas fa-user-tie to bi bi-person-badge */}
                    <i className="bi bi-person-badge"></i>
                    <span>Manage Agents</span>
                 </Link>
                  <Link to="/admin/manageBooking" className="nav-item">
                    <i className="bi bi-calendar-check"></i>
                    <span>Manage Bookings</span>
                 </Link>
              
            </nav>
         </aside>
    );
};

export default Sidebar;
