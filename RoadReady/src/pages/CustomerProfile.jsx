import { Outlet } from "react-router-dom";
import CustomerSideBar from "../components/Customer_Components/customerSideBar";
import NavbarCustomer from "../components/NavbarCustomer";

const CustomerProfile = () =>{
 
    return(
      <div> 
        <NavbarCustomer/>
            <div className="d-flex flex-grow-1 gap-3 align-items-stretch">
                    <CustomerSideBar />
                <div className="flex-grow-1">
                    <Outlet/>
                </div>
            </div>
      </div>
    )

}
export default CustomerProfile;