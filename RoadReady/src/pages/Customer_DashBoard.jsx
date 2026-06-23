import { Outlet } from 'react-router-dom';
import '../assets/css/Admin_Style_code.css'
import Sidebar from "../components/Admin_Components/Sidebar";
import Widget from "../components/Admin_Components/widget";
import CustomerSideBar from '../components/Customer_Components/customerSideBar';
import NavbarCustomer from "../components/NavbarCustomer";
import CarSearch from '../components/Car_Components/CarSearch';

const CustomerDashboard = () =>{
 
    return(
      <div> 
        <NavbarCustomer/>
            <CarSearch/>
      </div>
    )

}
export default CustomerDashboard;