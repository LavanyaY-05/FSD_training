import axios from "axios"
import { useEffect, useState } from "react"

const AgentProfile = () => {

    const username = localStorage.getItem('username')

    const AgentApi = "http://localhost:8080/api/agent/agentprofile"
    const updateApi = "http://localhost:8080/api/agent/update"
    const passwordApi = "http://localhost:8080/api/auth/newpass"


    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState(' ')
    const [email, setEmail] = useState('')
    const [phone, setPhone] = useState('')
        const [loaction, setLocation] = useState()

    const [password, setPassword] = useState()

    const [uname, setUname] = useState(username)

    const [successMsg, setSucessMsg] = useState()
    const [errMsg, setErrMsg] = useState()
    const [errFirstMsg, setErrFirstMsg] = useState()
    const [errEmailMsg, setErrEmailMsg] = useState()
    const [errPhoneMsg, setErrPhoneMsg] = useState()
    const [errLocationMsg, setErrLocationMsg] = useState()

    const [passwordSuccessMsg, setPasswordSucessMsg] = useState()
    const [passwordErrMsg, setPasswordErrMsg] = useState()
    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }
    useEffect(() => {

        const getAgent = async () => {
            const response = await axios.get(AgentApi, config)
            console.log(response)

            setFirstName(response.data.firstname)
            setLastName(response.data.lastname)
            setEmail(response.data.email)
            setPhone(response.data.phoneNumber)
            setLocation(response.data.location)
            setUname(response.data.username)

        }
        getAgent()
    }, [])



    const changePassword = async (e) => {
        e.preventDefault()
        let body = {
            "newPassword": password
        }
        try {
            const response = await axios.post(passwordApi, body, config)
            setPasswordSucessMsg("Updated Successfully !!!")
            setPasswordErrMsg(undefined)

        }
        catch (err) {
            setPasswordErrMsg("Failed To Update")
            setPasswordSucessMsg(undefined)

        }

    }
    const update = async (e) => {
        e.preventDefault()
        let body = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            phoneNumber: phone

        }

        try {
            const response = await axios.put(updateApi, body, config)
            console.log(response.data)
            setSucessMsg("Updated Successfully !!!")
            setErrMsg(undefined)
            setErrFirstMsg(undefined)
            setErrEmailMsg(undefined)
            setErrLocationMsg(undefined)
            setErrPhoneMsg(undefined)

        }
        catch (err) {
            console.log(err)
            setErrMsg("Failed To Update")
            setSucessMsg(undefined)
            setErrFirstMsg(err?.response?.firstname)
            setErrEmailMsg(err?.response?.lastname)
            setErrLocationMsg(err?.response?.email)
            setErrPhoneMsg(err?.response?.phoneNumber)

        }


    }


    return (
        <div className="container">

            <div className="row mt-4">
                <div className="col-sm-3"></div>
                <div className="col-md-6">

                    <div className="card profile-card">
                        <div className="card-header">
                            Profile
                        </div>
                        <div className="card-body">
                            <form onSubmit={(e) => update(e)}>

                                {
                                    successMsg !== undefined ? <div className="alert alert-success" role="alert">
                                        {successMsg}
                                    </div> : ""

                                }
                                {
                                    errMsg !== undefined ? <div className="alert alert-danger" role="alert">
                                        {errMsg}
                                    </div> : ""

                                }

                                <div className="mb-4">
                                    <label className="profile-label">First Name :
                                        {
                                            errFirstMsg !== undefined ?
                                                <span style={{ color: 'red', fontSize: '14px' }}>{" " + errFirstMsg}</span> : ""
                                        }
                                    </label>
                                    <input type="text" className="form-control profile-input"
                                        onChange={(e) => setFirstName(e.target.value)} value={firstName} />
                                </div>

                                <div className="mb-4">
                                    <label className="profile-label">Last Name :</label>
                                    <input type="text" className="form-control profile-input" onChange={(e) => setLastName(e.target.value)} value={lastName} />
                                </div >
                                <div className="mb-4">
                                    <label className="profile-label">Email :
                                        {
                                            errEmailMsg !== undefined ?
                                                <span style={{ color: 'red', fontSize: '14px' }}>{" " + errEmailMsg}</span> : ""
                                        }
                                    </label>
                                    <input type="text" className="form-control profile-input" onChange={(e) => setEmail(e.target.value)} value={email} />
                                </div>
                                <div className="mb-4">
                                    <label className="profile-label">Phone Number :
                                        {
                                            errPhoneMsg !== undefined ?
                                                <span style={{ color: 'red', fontSize: '14px' }}>{" " + errPhoneMsg}</span> : ""
                                        }
                                    </label>
                                    <input type="text" className="form-control profile-input" onChange={(e) => setPhone(e.target.value)} value={phone} />
                                </div>
                                <div className="mb-4">
                                    <label className="profile-label">Location :
                                        {
                                            errLocationMsg !== undefined ?
                                                <span style={{ color: 'red', fontSize: '14px' }}>{" " + errLocationMsg}</span> : ""
                                        }
                                    </label>
                                    <input type="text" className="form-control profile-input" onChange={(e) => setLocation(e.target.value)} value={loaction} />
                                </div>
                                <div className="mb-4">
                                    <label className="profile-label">Username :</label>
                                    <input type="text" className="form-control profile-input" value={username} readOnly={true} />
                                </div>
                                <div className="mb-4 text-end">
                                    <input type="submit" value="Edit Profile" className="btn text-white rounded-pill px-4 py-2 fw-semibold" style={{ backgroundColor: '#f7941d', border: 'none' }} />
                                </div>

                            </form>
                        </div>
                    </div>

                    <div className="card profile-card mt-4">
                        <div className="card-header">
                            Password
                        </div>
                        <div className="card-body">
                            <form onSubmit={(e) => changePassword(e)}>
                                {
                                    passwordSuccessMsg !== undefined ? <div className="alert alert-success" role="alert">
                                        {passwordSuccessMsg}
                                    </div> : ""

                                }
                                {
                                    passwordErrMsg !== undefined ? <div className="alert alert-danger" role="alert">
                                        {passwordErrMsg}
                                    </div> : ""

                                }
                                <div className="mb-4">
                                    <label className="profile-label">Change Password :</label>
                                    <input type="password" className="form-control profile-input" onChange={(e) => setPassword(e.target.value)} />
                                </div>
                                 <div className="mb-4 text-end">
                                    <input type="submit" value="Change Password" className="btn text-white rounded-pill px-4 py-2 fw-semibold" style={{ backgroundColor: '#f7941d', border: 'none' }} />
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

export default AgentProfile

