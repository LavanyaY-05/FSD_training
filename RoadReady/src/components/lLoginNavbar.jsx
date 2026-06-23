import { Link, useNavigate } from "react-router-dom";

const LoginNavbar = () => {

    const navigate = useNavigate()
    const username = localStorage.getItem('username')
    const logout =() =>{
        localStorage.clear()
        navigate("/login")
    }
  return (
   <nav  className="top-navbar">
        <div  className="nav-container">
            <div  className="logo-area">
                <h1  className="logo-text">RoadReady</h1>
            </div>
            <div  className="nav-menu">
                 <Link   className="nav-link">Home </Link>
                <span  className="user-greeting">Welcome, {username}</span>
            <button className="btn btn-outline-danger" type="submit" onClick={()=>logout()}>Logout</button>
            </div>
        </div>
    </nav>

  )
};

export default LoginNavbar;




