import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getAll } from "../../store/action/BookingAction";
import axios from "axios";

const AllBookings = () => {

    const dispatch = useDispatch();
    const [activeTab, setActiveTab] = useState("ALL")
    const [id, setId] = useState()
    const [cancelInfo, setCancelInfo] = useState()
    const [successMsg, setSuccessMsg] = useState()
    const [errMsg, setErrMsg] = useState()
    const tabs = ["ALL", "PENDING", "CONFIRMED","ACTIVE", "CANCELLED", "COMPLETED"];
    const [cancelTab, setCancelTab] = useState(true)
    const [apiErrMsg, setApiErrMsg] = useState()
const [rating, setRating] = useState("");
const [comment, setComment] = useState("");


    const { bookings } = useSelector(state => state.bookings);
    useEffect(() => {
        dispatch(getAll(activeTab));
    }, [activeTab, cancelTab]);

    const config = {
            headers: {
                'Authorization': "Bearer " + localStorage.getItem('token')
            }
        }

    const cancel = async (e) => {
        e.preventDefault()
        
        let body = {
            "cancellationInfo": cancelInfo
        }

        try {
            const response = await axios.put(`http://localhost:8080/api/bookings/customer/${id}/cancel`, body, config)
            setCancelTab(false)
            setSuccessMsg("Booking Cancelled Successfully")
            setErrMsg(undefined)
            console.log(response)
        }
        catch (err) {
            console.log(err)
            setSuccessMsg(undefined)
            setErrMsg(err?.response?.data || "Falied to Book")

        }

    }

    const review = async(e) =>{

e.preventDefault()
        
        let body = {
            "rating": rating,
            "comment":comment
        }

        try {
            const response = await axios.post(`http://localhost:8080/api/review/add/${id}`, body, config)
            setSuccessMsg("Review Added Successfully")
            setErrMsg(undefined)
            console.log(response)
        }
        catch (err) {
            console.log(err)
            setSuccessMsg(undefined)
            setErrMsg(err?.response?.data || "Falied to Added Review, Try Again")

        }

    }

    return (
        <div className="py-5 px-4" style={{ backgroundColor: "#F8F9FA", minHeight: "100vh", color: "#4A4A4A" }}>
            <div className="max-width-container mx-auto" style={{ maxWidth: "900px" }}>

                <div className="mb-4">
                    <h3 className="fw-bold m-0 text-dark tracking-tight">My Bookings</h3>
                </div>

                <div className="d-flex border-bottom mb-4 overflow-auto scrollbar-hidden" style={{ borderColor: "#E0E0E0" }}>
                    {tabs.map((tab) => (
                        <button
                            key={tab}
                            type="button"
                            className="btn border-0 position-relative fw-bold px-4 py-2 text-uppercase transition-all"
                            style={{
                                color: activeTab === tab ? "#FF8400" : "#757575",
                                background: "none",
                               fontSize: "14px",
                                letterSpacing: "0.5px"
                            }} 
                            onClick={() => setActiveTab(tab)}
                        >
                            {tab.toLowerCase()}
                            {/* Active Bottom Highlight bar */}
                            {activeTab === tab && (
                                <div
                                    className="position-absolute bottom-0 start-0 end-0"
                                    style={{ height: "3px", backgroundColor: "#FF8400", borderRadius: "3px 3px 0 0" }}
                                />
                            )}
                        </button>
                    ))}
                </div>


                <div className="d-flex flex-column gap-4">
                    {bookings.map((b, index) => (
                        <div
                            className="card border-0 rounded-4 shadow-sm bg-white overflow-hidden transition-transform"
                            key={index}
                            style={{ borderLeft: "5px solid #FF8400" }}
                        >
                            <div className="p-4">

                                {/* Top Row Block Header */}
                                <div className="d-flex justify-content-between align-items-start mb-2">
                                    <div>
                                        <h5 className="mb-1 fw-bold text-dark text-capitalize" style={{ fontSize: "19px" }}>
                                            {b?.carBrand} {b?.carModel}
                                        </h5>
                                        <div className="text-muted font-monospace text-uppercase" style={{ fontSize: "11px", letterSpacing: "0.5px" }}>
                                            {b?.carType} <span className="text-light-greymx mx-1">•</span> Model Year {b?.modelYear}
                                        </div>
                                    </div>
                                    <span
                                        className="badge px-3 py-2 text-uppercase border fw-bold"
                                        style={{ backgroundColor: "#FFF4E6", color: "#FF8400", borderColor: "#FFE0B2" }}
                                    >
                                        {b?.bookingStatus}
                                    </span>
                                </div>

                                {/* Middle Segment: Timing Route Grid Data Details */}
                                <div className="row g-3 py-3 my-2 border-top border-bottom" style={{ borderColor: "#F1F1F1" }}>
                                    <div className="col-sm-6">
                                        <div className="text-muted small text-uppercase font-monospace fw-semibold" style={{ fontSize: "11px" }}>Pickup Location</div>
                                        <div className="fw-bold text-dark small mt-1">{b?.pickUpLocation}</div>
                                        <div className="text-muted small mt-0.5" style={{ fontSize: "12px" }}>
                                            {b?.pickUpDateTime?.replace("T", "  |  ")}
                                        </div>
                                    </div>
                                    <div className="col-sm-6 border-start-sm" style={{ borderLeft: "1px solid #F1F1F1" }}>
                                        <div className="text-muted small text-uppercase font-monospace fw-semibold ps-sm-3" style={{ fontSize: "11px" }}>Dropoff Location</div>
                                        <div className="fw-bold text-dark small mt-1 ps-sm-3">{b?.dropDownLocation}</div>
                                        <div className="text-muted small mt-0.5 ps-sm-3" style={{ fontSize: "12px" }}>
                                            {b?.dropDownDateTime?.replace("T", "  |  ")}
                                        </div>
                                    </div>
                                </div>



                                {/* Footer Details: Delivery Configurations & Cost */}
                                <div className="d-flex justify-content-between align-items-center mt-3 pt-1">
                                    <div className="d-flex align-items-center gap-2 text-secondary small">
                                        <span className="fw-semibold font-monospace text-uppercase" style={{ fontSize: "12px", color: "#6C757D" }}>
                                            {b?.deliveryType === "HOME_DELIVERY" ? "Home Delivery" : "Self Pickup"}
                                        </span>
                                    </div>
                                    <div className="text-end">
                                        <div className="text-muted text-uppercase font-monospace" style={{ fontSize: "10px" }}>Total Price</div>
                                        <div className="fw-bold fs-4" style={{ color: "#FF8400" }}>
                                            ₹{b?.totalPrice}
                                        </div>
                                    </div>
                                </div>


                                {
                                    b.bookingStatus == 'PENDING' || b.bookingStatus == 'CONFIRMED' ?
                                        <div className="d-flex justify-content-between align-items-center mt-3 pt-1">
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onClick={(e) => setId(b.id)}>
                                                Cancel</button>
                                        </div> : ""}
                                {
                                    b.bookingStatus == 'COMPLETED' ?
                                        <div className="d-flex justify-content-between align-items-center mt-3 pt-1">
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#reviewModal" onClick={(e) => setId(b.id)}>
                                                REVIEW</button>
                                        </div> : ""}

                            </div>
                        </div>
                    ))}
                </div>


                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Cancel Booking</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                {successMsg !== undefined ?
                                    <div className="alert alert-success">
                                        {successMsg}
                                    </div>
                                    : " "}
                                {
                                    errMsg !== undefined ?
                                        <div className="alert alert-success">
                                            {errMsg}
                                        </div> : " "}
                                <div>
                                    <label>Reason For Cancellation</label>
                                    <input type="text" onChange={(e) => setCancelInfo(e.target.value)} />
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" onClick={(e) => cancel(e)}>Cancel Booking</button>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="modal fade" id="reviewModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Review</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                   {successMsg && <div className="alert alert-success">{successMsg}</div>}
                {errMsg && <div className="alert alert-danger">{errMsg}</div>}

                <div className="mb-3">
                    <label className="form-label">Rating (1 - 5)</label>
                    <input
                        type="number"
                        className="form-control"
                        min="1"
                        max="5"
                        value={rating}
                        onChange={(e) => setRating(e.target.value)}
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label">Comment</label>
                    <textarea
                        className="form-control"
                        rows="3"
                        maxLength="500"
                        placeholder="Write your experience..."
                        value={comment}
                        onChange={(e) => setComment(e.target.value)}
                    />
                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" onClick={(e) => review(e)}>Add Review</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default AllBookings