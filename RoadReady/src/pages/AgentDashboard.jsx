import { Outlet } from "react-router-dom";
import AgentSideBar from "../components/AgentComponent/AgentSideBar";
import NavbarAgent from "../components/NavbarAgent";

const AgentDashboard = () =>{
 
    return(
      <div> 
        <NavbarAgent/>
            <div className="d-flex flex-grow-1 gap-3 align-items-stretch">
                    <AgentSideBar/>
                <div className="flex-grow-1">
                    <Outlet/>
                </div>
            </div>
      </div>
    )

}
export default AgentDashboard;