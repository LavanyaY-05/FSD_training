import axios from "axios"
import { useEffect, useState } from "react"

const Inspection = () => {

    const preRentApi = "http://localhost:8080/api/inspection/pre-rent"
    const postRentApi = "http://localhost:8080/api/inspection/post-rent"
    const inspectApi = "http://localhost:8080/api/inspection/add/"

    const [activeTab, setActiveTab] = useState("PRE")
    const [tasks, setTasks] = useState([])
    const [currentPage, setCurrentPage] = useState(0)
    const [totalPage, setTotalPage] = useState(0)
    const [size, setSize] = useState(6)

    const [selectedBooking, setSelectedBooking] = useState(null)
    const [bookingId, setBookingId] = useState(0)

    const [fuelLevel, setFuelLevel] = useState()
    const [odometer, setOdometer] = useState()
    const [condition, setCondition] = useState()
    const [hasDamage, setHasDamage] = useState(false)
    const [damageReport, setDamageReport] = useState()

    const [errMsg, setErrMsg] = useState()
    const [successMsg, setSuccessMsg] = useState()

    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }

    const getTasks = async () => {
        const url = activeTab === "PRE" ? preRentApi : postRentApi
        try {
            const response = await axios.get(url + `?page=${currentPage}&size=${size}`, config)
            setTasks(response?.data?.bookingDetails)
            setTotalPage(response?.data?.totalPages)
        } catch (err) {
            console.log(err)
        }
    }

    useEffect(() => {
        getTasks()
    }, [activeTab, currentPage])

    const switchTab = (tab) => {
        setActiveTab(tab)
        setCurrentPage(0)
    }



    const submitInspection = async (e) => {
        e.preventDefault()
        setErrMsg(undefined)
        setSuccessMsg(undefined)

        const body = {
            inspectionType: activeTab === "PRE" ? "PRE_RENT" : "POST_RENT",
            fuelLevel: fuelLevel,
            odometer: odometer,
            condition: condition,
            hasDamage: hasDamage,
            damageReport: damageReport
        }

        try {
            await axios.post(inspectApi + bookingId, body, config)
            setSuccessMsg("Inspection saved")
            let temp = [...tasks].filter(t => t.bookingDetails !== bookingId)
            setTasks(temp)
        } catch (err) {
            console.log(err)
            setFuelLevel(undefined)
            setOdometer(undefined)
            setCondition(undefined)
            setHasDamage(false)
            setDamageReport(undefined)
            setErrMsg(undefined)
            setSuccessMsg(undefined)
            setErrMsg("Failed to save inspection")
        }
    }

    return (
        <div className="dashboard-main">
            <h2 className="fw-bold mb-4">Agent Tasks</h2>

            <ul className="nav nav-tabs mb-4">
                <li className="nav-item ">
                    <button onClick={() => switchTab("PRE")}
                        className="text-white rounded-pill px-4 py-2 fw-semibold border-0"
                        style={{
                            backgroundColor: "#FF8400",
                        }}
                    >
                        Pre Rental
                    </button>
                </li>
                <li className="nav-item">
                    <button onClick={() => switchTab("POST")} className="text-white rounded-pill px-4 py-2 fw-semibold border-0"

                        style={{
                            backgroundColor: "#FF8400",
                        }}
                    >
                        Post Rental
                    </button>
                </li>
            </ul>

            <div className="row g-3">
                {
                    tasks.length === 0 ? <p className="text-muted">No tasks here.</p> :


                        tasks.map((b, index) => (
                            <div className="col-md-4" key={index}>
                                <div className="card h-100 shadow-sm">
                                    <div className="card-body">
                                        <h5 className="card-title">Booking #{b.bookingId}</h5>
                                        <p className="card-text mb-1"><strong>Car:</strong> {b.brand} {b.model}</p>
                                        <p className="card-text mb-1">
                                            <strong>{
                                                activeTab === "PRE" ?
                                                    "Pickup:" : "Drop:"}
                                            </strong>
                                            {activeTab === "PRE" ?
                                                <p>{b.pickupDateTime.split("T")[0]} at {b.pickupDateTime.split("T")[1].slice(0, 5)} </p>
                                                : <p>{b.dropdownDateTime.split("T")[0]} at {b.dropdownDateTime.split("T")[1].slice(0, 5)} </p>}

                                        </p>

                                        <button
                                            className="btn btn-primary btn-sm w-100"
                                            data-bs-toggle="modal"
                                            data-bs-target="#inspectionModal"
                                            onClick={() => setBookingId(b.bookingId)}
                                        >
                                            Start Inspection
                                        </button>
                                    </div>
                                </div>
                            </div>
                        ))
                }

            </div>

            <nav className="mt-4">
                <ul className="pagination" style={{ display: 'flex', justifyContent: 'center', listStyle: 'none', padding: 0 }}>
                    <li className="page-item">
                        <button className="page-link" disabled={currentPage === 0} onClick={() => setCurrentPage(currentPage - 1)}>Previous</button>
                    </li>
                    {
                        Array.from({ length: totalPage }).map((_, index) => (
                            <li className="page-item" key={index}>
                                <button className="page-link" onClick={() => setCurrentPage(index)}>{index + 1}</button>
                            </li>
                        ))
                    }
                    <li className="page-item">
                        <button className="page-link" disabled={currentPage === totalPage - 1} onClick={() => setCurrentPage(currentPage + 1)}>Next</button>
                    </li>
                </ul>
            </nav>

            <div className="modal fade" id="inspectionModal" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">
                                {activeTab === "PRE" ? "Pre Rental" : "Post Rental"} Inspection - Booking #{selectedBooking?.id}
                            </h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div className="modal-body">
                            <form onSubmit={(e) => submitInspection(e)}>
                                {errMsg && <div className="alert alert-danger">{errMsg}</div>}
                                {successMsg && <div className="alert alert-primary">{successMsg}</div>}

                                <div className="mb-3">
                                    <label className="form-label">Fuel Level (%)</label>
                                    <input type="number" className="form-control" onChange={(e) => setFuelLevel(e.target.value)} required />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Odometer (km)</label>
                                    <input type="number" className="form-control" onChange={(e) => setOdometer(e.target.value)} required />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Car Condition</label>
                                    <textarea className="form-control" rows="2" onChange={(e) => setCondition(e.target.value)} required></textarea>
                                </div>

                                <div className="form-check mb-3">
                                    <input type="checkbox" className="form-check-input" onChange={(e) => setHasDamage(e.target.checked)} />
                                    <label className="form-check-label">Has Damage</label>
                                </div>

                                {
                                    hasDamage &&
                                    <div className="mb-3">
                                        <label className="form-label">Damage Report</label>
                                        <textarea className="form-control" rows="2" onChange={(e) => setDamageReport(e.target.value)}></textarea>
                                    </div>
                                }

                                <div className="modal-footer">
                                    <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    <button type="submit" className="btn btn-primary">Mark Complete</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Inspection