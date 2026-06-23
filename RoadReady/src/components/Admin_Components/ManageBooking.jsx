import axios from "axios"
import { useEffect, useState } from "react"

const AdminBookings = () => {

    const bookingsApi = "http://localhost:8080/api/admin/getUnassignedAgent?"
    const assignAgentApi = "http://localhost:8080/api/admin/assign/"
    const getAvailableAgentsApi = "http://localhost:8080/api/admin/getAgents/"

    const [bookings, setBookings] = useState([])
    const [currentPage, setCurrentPage] = useState(0)
    const [totalPages, setTotalPages] = useState(0)
    const [totalRecords, setTotalRecords] = useState(0)
    const [size, setSize] = useState(5)

    const [bookingId, setBookingId] = useState()
    const [agentId, setAgentId] = useState()
    const [availableAgents, setAvailableAgents] = useState([])

    const [errMsg, setErrMsg] = useState()
    const [successMsg, setSuccessMsg] = useState()

    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }

    useEffect(() => {
        const getAll = async () => {
            try {
                const response = await axios.get(`${bookingsApi}&page=${currentPage}&size=${size}`, config)
                console.log(response.data)
                setBookings(response.data.bookingDetails || [])
                setTotalPages(response.data.totalPages || 0)
                setTotalRecords(response.data.totalRecords || 0)
            }
            catch (err) {
                console.log(err)
            }
        }
        getAll()
    }, [currentPage])

    const fetchAgents = async (id) => {
        setBookingId(id)
        setAgentId("")
        setErrMsg(undefined)
        setSuccessMsg(undefined)
        
        
        try {
            const response = await axios.get(getAvailableAgentsApi+bookingId, config)
            console.log(response.data)
            setAvailableAgents(response.data)
        }
        catch (err) {
            console.log(err)
            setAvailableAgents([])
        }
    }

    const assignAgent = async (e) => {
        e.preventDefault()
        setSuccessMsg(undefined)
        setErrMsg(undefined)

        

        try {
            const response = await axios.post(assignAgentApi +bookingId+"/"+agentId, {},config)
            console.log(response)
            
            let tempBookings = [...bookings].filter(b => b.bookingId !== bookingId)
            setBookings([...tempBookings])
            setTotalRecords(totalRecords - 1)
            
            setSuccessMsg("Agent Assigned Successfully")
            setErrMsg(undefined)
            
            
        }
        catch (err) {
            console.log(err)
            setSuccessMsg(undefined)
            setErrMsg("Failed To Assign Agent, Try Again")
        }
    }

    return (

        <div className="dashboard-main">
            <div className="d-flex align-items-center justify-content-between mb-4">
                <h2 className="fw-bold">Booking Management</h2>
                <span className="badge bg-primary fs-6">
                    Total: {totalRecords}
                </span>
            </div>

            {/* Assign Agent Modal */}
            <div className="modal fade" id="assignAgentModal" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div className="modal-dialog modal-dialog-centered modal-sm">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="staticBackdropLabel">Assign Agent</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <form onSubmit={(e) => assignAgent(e)}>
                                {
                                    errMsg !== undefined ?
                                        <div className="alert alert-danger">{errMsg}</div> : ""
                                }
                                {
                                    successMsg !== undefined ?
                                        <div className="alert alert-success">{successMsg}</div> : ""
                                }
                                <div className="row g-3">

                                    <div className="col-md-12 mb-3">
                                        <label className="form-label text-muted small fw-bold">Select Agent</label>
                                        <select 
                                            className="form-select" 
                                            onChange={(e) => setAgentId(e.target.value)} 
                                            required
                                            value={agentId}
                                        >
                                            <option value="">Choose an agent...</option>
                                            {
                                                availableAgents.map((agent) => (
                                                    <option key={agent.agentId} value={agent.agentId}>
                                                        {agent.firstname}
                                                    </option>
                                                ))
                                            }
                                        </select>
                                        {availableAgents.length === 0 && (
                                            <small className="text-muted d-block mt-1">No agents available</small>
                                        )}
                                    </div>

                                </div>

                                <div className="modal-footer">
                                    <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    <button type="submit" className="btn btn-primary">Assign Agent</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div className="row">
                {
                    bookings.map((booking) => (
                        <div className="col-md-4 mb-4" key={booking.bookingId}>
                            <div className="card h-100">
                                <div className="card-body">
                                    <div className="d-flex justify-content-between align-items-start mb-2">
                                        <h5 className="card-title mb-0">Booking #{booking.bookingId}</h5>
                                        <span className="badge bg-success">CONFIRMED</span>
                                    </div>
                                    <p className="card-text">
                                        <small className="text-muted">Customer ID:</small> {booking.customerId}
                                    </p>
                                    <p className="card-text">
                                        <small className="text-muted">Car:</small> {booking.brand} {booking.model}
                                    </p>
                                    <p className="card-text">
                                        <small className="text-muted">Pickup:</small> {new Date(booking.pickupDateTime).toLocaleString()}
                                    </p>
                                    <p className="card-text">
                                        <small className="text-muted">Dropoff:</small> {new Date(booking.dropdownDateTime).toLocaleString()}
                                    </p>
                                    <p className="card-text">
                                        <small className="text-muted">Total Amount:</small> ₹{booking.totalAmount?.toFixed(2)}
                                    </p>
                                    <p className="card-text mb-0">
                                        <small className="text-muted">KYC Status:</small> 
                                        <span className={"badge ms-2 " + (booking.kyc_status === 'VERIFIED' ? 'bg-success' : 'bg-warning')}>
                                            {booking.kyc_status}
                                        </span>
                                    </p>
                                </div>
                                <div className="card-footer bg-transparent border-top-0">
                                    <button 
                                        className="btn btn-primary w-100"
                                        data-bs-toggle="modal"
                                        data-bs-target="#assignAgentModal"
                                        onClick={() => fetchAgents(booking.bookingId)}
                                    >
                                        <i className="bi bi-person-plus me-1"></i> Assign Agent
                                    </button>
                                </div>
                            </div>
                        </div>
                    ))
                }
            </div>

            {totalPages > 1 && (
                <div className="d-flex justify-content-center mt-4">
                    <nav>
                        <ul className="pagination">
                            <li className={"page-item " + (currentPage === 0 ? "disabled" : "")}>
                                <button className="page-link" onClick={() => setCurrentPage(currentPage - 1)}>Previous</button>
                            </li>
                            {[Array(totalPages)].map((_, index) => (
                                <li key={index} className={"page-item " + (currentPage === index ? "active" : "")}>
                                    <button className="page-link" onClick={() => setCurrentPage(index)}>{index + 1}</button>
                                </li>
                            ))}
                            <li className={"page-item " + (currentPage === totalPages - 1 ? "disabled" : "")}>
                                <button className="page-link" onClick={() => setCurrentPage(currentPage + 1)}>Next</button>
                            </li>
                        </ul>
                    </nav>
                </div>
            )}
        </div>
    )

}
export default AdminBookings