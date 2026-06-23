import AgentOnboard from "../components/Admin_Components/AgentOnboard";
import NavbarAdmin from "../components/NavbarAdmin";
import Sidebar from "../components/Admin_Components/Sidebar";
import Widget from "../components/Admin_Components/widget";
import { Outlet } from "react-router-dom";

const AdminDashboard = () =>{
 
    return(
      <div> 
        <NavbarAdmin/>
            <div className="d-flex flex-grow-1 gap-3 align-items-stretch">
                    <Sidebar />
                <div className="flex-grow-1">
                    <Outlet/>
                </div>
            </div>
      </div>
    )

}
export default AdminDashboard;