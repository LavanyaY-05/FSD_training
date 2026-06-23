import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const Login = () =>{

    const[username,setUsername] = useState()
    const[password,setPassword] = useState()
    const[errMsg, setErrMsg] = useState()
      
    const loginApi= "http://localhost:8080/api/auth/login"
    const userDetailsApi = "http://localhost:8080/api/auth/user-details"
    const navigate = useNavigate()


    const login =async(e) =>{
        e.preventDefault();
        console.log(username)

        const config = {
            headers:{
                'Authorization' : "Basic "+ window.btoa(username +":"+ password)
            }
        }

        try{
             const response = await axios.get(loginApi,config)
             let token = response.data.token
             localStorage.setItem('token',token)

             const config_userdetails = {
                headers:{
                    'Authorization' : "Bearer "+ token
                }
             }

             const userDetailResponse = await axios.get(userDetailsApi,config_userdetails)
             let role = userDetailResponse.data.role
             
             localStorage.setItem('username', userDetailResponse.data.username)
             switch(role){
                case 'CUSTOMER':
                    navigate("/customer")
                    break;
                case 'ADMIN':
                    navigate("/admin")
                    break;
                case 'AGENT':
                    navigate("/agent")
                    break;
                default:
                    setErrMsg("Invalid credendials")

             }

        }
        catch(err){

            setErrMsg("Invalid Credentials")

        }
        
    }




    return(
        <div>

      <div className="container-fluid bg-light py-5 min-vh-100 d-flex align-items-center justify-content-center">

        <div className="row shadow-sm border rounded-3 overflow-hidden bg-white" style={{ maxWidth: "720px", width: "100%" }}>

          {/* Left orange gradient panel */}
          <div
            className="col-5 d-none d-sm-block"
            style={{
              background: "linear-gradient(180deg, #ff8c00 0%, #ffb200 100%)",
              minHeight: "440px",
            }}
          ></div>

          {/* Right form panel */}
          <div className="col-12 col-sm-7 p-5 d-flex flex-column justify-content-center">

            <h3 className="text-center mb-4" style={{ color: "#ff8c00", fontWeight: "bold" }}>
              Login
            </h3>

            {errMsg !== undefined && errMsg !== "" && (
              <div className="alert alert-warning" role="alert">
                {errMsg}
              </div>
            )}

            <form onSubmit={(e) => login(e)}>

              <div className="mb-4">
                <label className="form-label text-muted small mb-1">Username:</label>
                <input
                  type="text"
                  className="form-control border-0 border-bottom rounded-0 px-0"
                  onChange={(e) => setUsername(e.target.value)}
                  required
                />
              </div>

              <div className="mb-4">
                <label className="form-label text-muted small mb-1">Password:</label>
                <input
                  type="password"
                  className="form-control border-0 border-bottom rounded-0 px-0"
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </div>

              <div className="mt-4">
                <input
                  type="submit"
                  value="LOGIN"
                  className="btn w-100 text-white fw-semibold"
                  style={{ backgroundColor: "#ff8c00" }}
                />
              </div>

              <div className="mt-3 text-center">
                <Link to="/register" className="text-decoration-none small fw-semibold" style={{ color: "#ff8c00" }}>
                  Dont Have An Account?
                </Link>
              </div>

            </form>
          </div>

        </div>

      </div>

    </div>
    )
}

export default Login;