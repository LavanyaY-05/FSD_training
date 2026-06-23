import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";



const AgentSideBar = () => {

    const AgentApi = "http://localhost:8080/api/agent/agentprofile"
    const [agent, setAgent] = useState()

    useEffect(() => {
        const config = {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
        }
        const getAgent = async () => {
            const response = await axios.get(AgentApi, config)
            setAgent(response.data)
            console.log(response)
        }
        getAgent()
    }, [])
    return (
        <aside className="admin-sidebar">
            {/* <!-- Profile Section --> */}
            <div className="profile-section">
                <div className="avatar-wrapper">
                    <div className="admin-avatar">AG</div>
                    
                </div>
                {
                    agent !== undefined ?
                        <div>
                            <h3 className="admin-name">{agent.firstname}{agent.lastname}</h3>
                            <p className="admin-role">{agent.location}</p>
                        </div> : ""

                }
            </div>

            {/* <!-- Navigation Items --> */}
            <nav className="sidebar-nav">
                <Link to="" className="nav-item active">
                    {/* Changed fas fa-tachometer-alt to bi bi-speedometer2 */}
                    <i className="bi bi-car-front"></i>
                    <span>Dashboard</span>
                </Link>
                <Link to="/agent/profile" className="nav-item">
                    {/* Changed fas fa-car to bi bi-car-front */}
                   <i className="bi bi-person-circle"></i>
                    <span>Profile</span>
                </Link>

            </nav>
        </aside>
    );
};

export default AgentSideBar;
