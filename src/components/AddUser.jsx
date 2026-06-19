import axios from "axios"
import { useState } from "react"
import { Link } from "react-router-dom"

const AddUser = () => {

    const Api = "https://jsonplaceholder.typicode.com/users"

    const [name, setName] = useState()
    const [email, setEmail] = useState()
    const [phone, setPhone] = useState()
    const [company, setCompany] = useState()
    const [success, setSuccess] = useState()
    const [errMsg, setErrMsg] = useState()



    const addUsers = async (e) => {
        e.preventDefault()

        let body = {
            name: name,
            email: email,
            phone: phone,
            company: company
        }
        try {
            const response = await axios.post(Api, body)
            console.log(response)
            setSuccess("User Added To The System")
            setErrMsg(undefined)


        }
        catch (err) {
            console.log(err)
            setSuccess(undefined)
            setErrMsg("Failed , Try Again")
        }
    }


    return (

        <div className="bg-light" style={{ minHeight: "100vh" }}>

            <nav className="navbar navbar-dark bg-white shadow-sm">
                <div className="container d-flex  py-2">
                    <span className="navbar-brand fw-bold text-black mb-0">User Management System</span>

                </div>
            </nav>
            <div className="bg-light d-flex align-items-center justify-content-center" style={{ minHeight: "100vh" }}>
                <div className="card shadow-sm border-0 rounded-4" style={{ width: "100%", maxWidth: "500px" }}>

                    <div className="container">

                        <div className="d-flex align-items-center mb-4 mt-4 position-relative">
                            <Link to="/users" className="btn btn-primary">
                                <i className="bi bi-arrow-left me-1"></i> Back
                            </Link>
                            <h1 className="fw-bold mb-0 position-absolute start-50 translate-middle-x">
                                Add Users
                            </h1>
                        </div>

                        {
                            success !== undefined ?
                                <div className="toast-container position-fixed top-0 end-0 p-3" style={{ zIndex: 1055 }}>
                                    <div className="toast fade show" role="alert" aria-live="assertive" aria-atomic="true">
                                        <div className="toast-header bg-success">
                                            <button type="button" className="btn-close" onClick={() => setSuccess(undefined)} aria-label="Close"></button>
                                        </div>
                                        <div className="toast-body">
                                            {success}
                                        </div>
                                    </div>
                                </div>
                                : ""
                        }

                        <div className="card-body">
                            <form onSubmit={(e) => addUsers(e)}>
                                <div className="mt-4">
                                    <label>Name</label>
                                    <input type="text" className="form-control" onChange={(e) => setName(e.target.value)} />
                                </div>
                                <div className="mt-4">
                                    <label>Email</label>
                                    <input type="text" className="form-control" onChange={(e) => setEmail(e.target.value)} />
                                </div>
                                <div className="mt-4">
                                    <label>Phone</label>
                                    <input type="text" className="form-control" onChange={(e) => setPhone(e.target.value)} />
                                </div>
                                <div className="mt-4">
                                    <label>Company Name</label>
                                    <input type="text" className="form-control" onChange={(e) => setCompany(e.target.value)} />
                                </div>

                                <div className="mt-4">
                                    <input type="submit" className="btn btn-primary btn-lg rounded-3 form-control" />
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    )

}
export default AddUser