import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const CustomerRegister = () => {

    const [firstName, setFirstName] = useState()
    const [lastName, setLastName] = useState()
    const [email, setEmail] = useState()
    const [phone, setPhone] = useState()
    const [username, setUsername] = useState()
    const [password, setPassword] = useState()


    const [successMsg, setSucessMsg] = useState()
    const [errMsg, setErrMsg] = useState()
    const [errFirstMsg, setErrFirstMsg] = useState()
    const [errEmailMsg, setErrEmailMsg] = useState()
    const [errPhoneMsg, setErrPhoneMsg] = useState()
    const [errUsernameMsg, setErrUsernameMsg] = useState()
    const [errPasswordMsg, setErrPasswordMsg] = useState()


    const customerApi = "http://localhost:8080/api/customers/signUp"

    const navigate = useNavigate()
 
    const addAgent = async (e) => {
        e.preventDefault();

        let body = {
            
 "firstName" : firstName,
 "lastName":lastName,
 "email":email,
 "phoneNumber":phone,
 "username":username,
 "password":password

}

        

        try{
            const response = await axios.post(customerApi,body)

            setSucessMsg("Registered Successfully")
            setFirstName('')
            setLastName('')
            setEmail('')
            setPhone('')
            setUsername('')
            setPassword('')
 
            setErrMsg(undefined)
            setErrFirstMsg(undefined)
            setErrEmailMsg(undefined)
            setErrPhoneMsg(undefined)
            setErrUsernameMsg(undefined)
            setErrPasswordMsg(undefined)

            navigate("/login")


        }
        catch(err){
            setSucessMsg(undefined)
            setErrMsg("Registeration Failed "+ (err.response?.data?.message || ""))
            setErrFirstMsg(err.response?.data?.firstName)
            setErrEmailMsg(err.response?.data?.email)
            setErrPhoneMsg(err.response?.data?.phoneNumber)
            setErrUsernameMsg(err.response?.data?.username)
            setErrPasswordMsg(err.response?.data?.password)

        }
    }
    

    return (
<div className="d-flex align-items-center justify-content-center p-3 p-md-5" style={{ backgroundColor: '#f8f9fa', minHeight: '100vh' }}>
    <div className="container" style={{ maxWidth: '600px' }}>
        <div className="card shadow-sm border-0 rounded-4">
            <div className="card-body p-4 p-md-5">
                
                {/* Brand Header */}
                <h2 className="text-center fw-bold mb-5" style={{ color: '#FF8400' }}>
                    Registration
                </h2>

                <form onSubmit={(e) => addAgent(e)}>
                    {
                        successMsg !== undefined ? (
                            <div className="alert alert-success border-0 rounded-3" role="alert">
                                {successMsg}
                            </div>
                        ) : ""
                    }
                    
                    {
                        errMsg !== undefined ? (
                            <div className="alert alert-danger border-0 rounded-3" role="alert">
                                {errMsg}
                            </div>
                        ) : ""
                    }

                    {/* Row 1: Split Name Layout (2 Columns) */}
                    <div className="row g-3 mb-4">
                        <div className="col-sm-6">
                            <label className="form-label text-secondary small fw-semibold">First Name</label>
                            {
                                errFirstMsg !== undefined ?
                                <span className="ms-1" style={{ color: 'red', fontSize: '13px' }}>{errFirstMsg}</span> : ""
                            }
                            <input 
                                type="text" 
                                className="form-control bg-transparent rounded-0 border-0 border-bottom px-0"  
                                onChange={(e) => setFirstName(e.target.value)} 
                                value={firstName || ""} 
                                style={{ boxShadow: 'none', borderColor: '#ced4da' }}
                            />
                        </div>
                        <div className="col-sm-6">
                            <label className="form-label text-secondary small fw-semibold">Last Name</label>
                            <input 
                                type="text" 
                                className="form-control bg-transparent rounded-0 border-0 border-bottom px-0" 
                                onChange={(e) => setLastName(e.target.value)} 
                                value={lastName || ""} 
                                style={{ boxShadow: 'none', borderColor: '#ced4da' }}
                            />
                        </div>
                    </div>

                    {/* Email Input Row */}
                    <div className="mb-4">
                        <label className="form-label text-secondary small fw-semibold">Email</label>
                        {
                            errEmailMsg !== undefined ?
                            <span className="ms-1" style={{ color: 'red', fontSize: '13px' }}>{errEmailMsg}</span> : ""
                        }
                        <input 
                            type="text" 
                            className="form-control bg-transparent rounded-0 border-0 border-bottom px-0"   
                            onChange={(e) => setEmail(e.target.value)} 
                            value={email || ""} 
                            style={{ boxShadow: 'none', borderColor: '#ced4da' }}
                        />
                    </div>

                    {/* Mobile Number Input Row */}
                    <div className="mb-4">
                        <label className="form-label text-secondary small fw-semibold">Mobile Number</label>
                        {
                            errPhoneMsg !== undefined ?
                            <span className="ms-1" style={{ color: 'red', fontSize: '13px' }}>{errPhoneMsg}</span> : ""
                        }
                        <input 
                            type="text" 
                            className="form-control bg-transparent rounded-0 border-0 border-bottom px-0"   
                            onChange={(e) => setPhone(e.target.value)} 
                            value={phone || ""} 
                            style={{ boxShadow: 'none', borderColor: '#ced4da' }}
                        />
                    </div>

                    {/* Username Input Row */}
                    <div className="mb-4">
                        <label className="form-label text-secondary small fw-semibold">Username</label>
                        {
                            errUsernameMsg !== undefined ?
                            <span className="ms-1" style={{ color: 'red', fontSize: '13px' }}>{errUsernameMsg}</span> : ""
                        }
                        <input 
                            type="text" 
                            className="form-control bg-transparent rounded-0 border-0 border-bottom px-0"   
                            onChange={(e) => setUsername(e.target.value)} 
                            value={username || ""} 
                            style={{ boxShadow: 'none', borderColor: '#ced4da' }}
                        />
                    </div>

                    {/* Password Input Row */}
                    <div className="mb-5">
                        <label className="form-label text-secondary small fw-semibold">Password</label>
                        {
                            errPasswordMsg !== undefined ?
                            <span className="ms-1" style={{ color: 'red', fontSize: '13px' }}>{errPasswordMsg}</span> : ""
                        }
                        <input 
                            type="password" 
                            className="form-control bg-transparent rounded-0 border-0 border-bottom px-0"   
                            onChange={(e) => setPassword(e.target.value)} 
                            value={password || ""} 
                            style={{ boxShadow: 'none', borderColor: '#ced4da' }}
                        />
                    </div>

                    {/* Action Buttons & Links */}
                    <div className="mb-4">
                        <button 
                            type="submit" 
                            className="btn text-white w-100 fw-bold py-2 text-uppercase rounded-3"
                            style={{ backgroundColor: '#FF8400', letterSpacing: '0.5px', border: 'none' }}
                        >
                            Register
                        </button>
                    </div>

                    <div className="text-center mt-4">
                        <Link 
                            to="/login" 
                            className="text-decoration-none fw-semibold small" 
                            style={{ color: '#FF8400' }}
                        >
                            Already Have An Account?
                        </Link>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
    )
}
export default CustomerRegister;