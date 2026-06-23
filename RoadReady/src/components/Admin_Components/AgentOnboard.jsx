import axios from "axios";
import { useState } from "react";

const AgentOnboard = () => {

    const [firstName, setFirstName] = useState()
    const [lastName, setLastName] = useState()
    const [email, setEmail] = useState()
    const [phone, setPhone] = useState()
    const [location, setLocation] = useState()
    const [username, setUsername] = useState()

    const [successMsg, setSucessMsg] = useState()
    const [errMsg, setErrMsg] = useState()
    const [errFirstMsg, setErrFirstMsg] = useState()
    const [errEmailMsg, setErrEmailMsg] = useState()
    const [errPhoneMsg, setErrPhoneMsg] = useState()
    const [errLocationMsg, setErrLocationMsg] = useState()
    const [errUsernameMsg, setErrUsernameMsg] = useState()

    const addAgentApi = "http://localhost:8080/api/agent/add"



    const addAgent = async (e) => {
        e.preventDefault();

        let body = {
            firstname: firstName,
            lastname: lastName,
            email: email,
            phoneNumber:phone,
            location: location,
            username: username,
        }

        const config = {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
        }

        try{
            const response = await axios.post(addAgentApi, body, config)
            setSucessMsg("Agent Onboarded Successfully")
            setFirstName('')
            setLastName('')
            setEmail('')
            setPhone('')
            setLocation('')
            setUsername('')
 
            setErrMsg(undefined)
            setErrFirstMsg(undefined)
            setErrEmailMsg(undefined)
            setErrPhoneMsg(undefined)
            setErrLocationMsg(undefined)
            setErrUsernameMsg(undefined)


        }
        catch(err){
            setSucessMsg(undefined)
            setErrMsg("Onboarding Failed "+ (err.response?.data?.message || ""))
            setErrFirstMsg(err.response?.data?.firstname)
            setErrEmailMsg(err.response?.data?.email)
            setErrPhoneMsg(err.response?.data?.phoneNumber)
            setErrLocationMsg(err.response?.data?.location)
            setErrUsernameMsg(err.response?.data?.username)
        }

    }

    return (

        <div className="container">
            <div className="row mt-4">
                <div className="col-sm-3"></div>
                <div className="col-md-6">

                    <div className="card">
                        <div className="card-header">
                            Onboard Agent
                        </div>
                        <div className="card-body">
                            <form onSubmit={(e) => addAgent(e)}>

                                {
                                    successMsg !== undefined?<div className="alert alert-success" role="alert">
                                            {successMsg}
                                        </div> : ""

                                }
                                {
                                    errMsg !== undefined?<div className="alert alert-danger" role="alert">
                                            {errMsg}
                                        </div> : ""

                                }

                                <div className="mb-4">
                                    <label>First Name</label>
                                    {
                                        errFirstMsg !== undefined ?
                                        <span style={{color:'red', fontSize : '14px'}}>{" " +errFirstMsg}</span>: ""
                                    }
                                    <input type="text" className="form-control"  
                                        onChange={(e) => setFirstName(e.target.value)} value={firstName} />
                                </div>

                                <div className="mb-4">
                                    <label>Last Name</label>
                                    
                                    <input type="text" className="form-control" onChange={(e) => setLastName(e.target.value)} value={lastName} />
                                </div >
                                <div className="mb-4">
                                    <label>Email</label>
                                    {
                                        errEmailMsg !== undefined ?
                                        <span style={{color:'red', fontSize : '14px'}}>{" " +errEmailMsg}</span>: ""
                                    }
                                    <input type="text" className="form-control"   onChange={(e) => setEmail(e.target.value)} value={email} />
                                </div>
                                <div className="mb-4">
                                    <label>Mobile Number</label>
                                    {
                                        errPhoneMsg !== undefined ?
                                        <span style={{color:'red', fontSize : '14px'}}>{" " +errPhoneMsg}</span>: ""
                                    }
                                    <input type="text" className="form-control"   onChange={(e) => setPhone(e.target.value)} value={phone} />
                                </div>
                                <div className="mb-4">
                                    <label>Location</label>
                                    {
                                        errLocationMsg !== undefined ?
                                        <span style={{color:'red', fontSize : '14px'}}>{" " +errLocationMsg}</span>: ""
                                    }
                                    <input type="text" className="form-control"   onChange={(e) => setLocation(e.target.value)} value={location} />
                                </div>
                                <div className="mb-4">
                                    <label>Username</label>
                                    {
                                        errUsernameMsg !== undefined ?
                                        <span style={{color:'red', fontSize : '14px'}}>{" " +errUsernameMsg}</span>: ""
                                    }
                                    <input type="text" className="form-control"   onChange={(e) => setUsername(e.target.value)} value={username} />
                                </div>
                                <div className="mb-4">
                                    <input type="submit" value="Add Agent To The System" className="btn btn-primary" />
                                </div>

                            </form>
                        </div>
                    </div>

                </div>
                <div className="col-sm-3"></div>

            </div>
        </div>
    )
}
export default AgentOnboard;