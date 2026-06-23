import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useLocation, useParams } from "react-router-dom";
import NavbarCustomer from "../NavbarCustomer";

const Booking = () => {
    const location = useLocation();
    const { id } = useParams();
    const [car, setCar] = useState(null);
    const { pickup, dropdown } = location.state
    const [pickupDateTime, setPickupDateTime] = useState(pickup)
    const [dropdownDateTime, setDropdownDateTime] = useState(dropdown)
    const [pickupLocation, setPickupLocation] = useState()
    const [dropdownLocation, setDropdownLocation] = useState()
    const [deliveryType, setDeliveryType] = useState('SELF_PICKUP')
    const [couponCode, setCouponCode] = useState()
    const [hours, setHours] = useState(0)
    const [showSuccessModal, setShowSuccessModal] = useState(false)
    const [errMsg, setErrMsg] = useState()
    const [successMsg, setSucessMsg] = useState()
    const [couponSuccess, setCouponSuccess] = useState()
    const [couponErr, setCouponErr] = useState()
    const [errPickUpLoc, setErrPickUPLoc] = useState();
    const [errDropDownLoc, setErrDropDownLoc] = useState();

    const [errPickUp, setErrPickUP] = useState();
    const [errDropDown, setErrDropDown] = useState();
    const [errLoc, setErrLoc] = useState();

    const [differentOption, setDifferentOption] = useState(false)

    const [booking, setBooking] = useState();
    const api = "http://localhost:8080/api/car/get/"
    const couponApi = "http://localhost:8080/api/coupons/validate?couponCode="
    const hoursApi = "http://localhost:8080/api/bookings/hours?pickupDate="
    const bookApi = "http://localhost:8080/api/bookings/add/"





    useEffect(() => {
        const getCar = async () => {
            try {
                const carRes = await axios.get(api + id);
                setCar(carRes.data)
                setPickupLocation(carRes.data?.address)
                setDropdownLocation(carRes.data?.address)
            }
            catch (err) {
                console.log(err);
            }
        }

        const getHours = async () => {
            try {
                const response = await axios.get(`${hoursApi}${pickupDateTime}&dropdownDate=${dropdownDateTime}`);
                setHours(response.data)
            }
            catch (err) {
                console.log(err);
            }
        }

        getCar()
        getHours()
    }, [pickupDateTime, dropdownDateTime]);

    const applyCoupon = async (e) => {
        e.preventDefault()
        const config = {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
        }
        try {
            const response = await axios.get(couponApi + couponCode, config)
            setCouponSuccess(response.data.success + "!!! You Saved " + response.data.amount)
            setCouponErr(undefined)
        }
        catch (err) {
            setCouponSuccess(undefined)
            setCouponErr(err?.response?.data?.message)
        }
    }

    const book = async (e) => {
        e.preventDefault()
        const config = {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
        }
        let body = {
            "pickupDateTime": pickupDateTime,
            "dropdownDateTime": dropdownDateTime,
            "pickupLocation": pickupLocation,
            "dropdownLocation": dropdownLocation,
            "deliveryType": deliveryType,
            "couponCode": couponCode || ""
        }
        try {
            const response = await axios.post(bookApi + id, body, config)
            console.log(response)
            setBooking(response.data)
            setErrPickUP(undefined);
            setErrDropDown(undefined);
            setErrPickUP(undefined);
            setErrMsg(undefined)

            setErrDropDown(undefined);

            setShowSuccessModal(true)
        }
        catch (err) {
            console.log(err)
            setErrMsg(err?.response?.data?.message)
            setErrPickUP(err?.response?.data?.pickUpDate);
            setErrDropDown(err?.response?.data?.dropDownDate);
            setErrPickUP(err?.response?.data?.pickupLocation);
            setErrDropDown(err?.response?.data?.dropdownLocation);

        }



    }

    return (
        <div>
            <div className="container my-4" style={{ maxWidth: '850px' }}>

                {/* COMPACT TOP CAR BANNER (Read-Only) */}
                <div className="card p-3 mb-4 bg-light border-0 shadow-sm">
                    <div className="row align-items-center text-center text-md-start g-3">
                        <div className="col-md-5">
                            <span className="badge bg-dark text-uppercase mb-1">{car?.type}</span>
                            <h4 className="fw-bold mb-0 text-dark">{car?.brand} {car?.model}</h4>
                            <small className="text-muted">{car?.seats} Seats • {car?.carTransmission.toLowerCase()} • {car?.fuelType.toLowerCase()}</small>
                        </div>
                        <div className="col-md-4 text-md-center border-start border-end border-2 border-white-50">
                            <small className="text-muted d-block uppercase fw-semibold">Garage Location</small>
                            <span className="fw-semibold text-dark">{car?.location}</span>
                        </div>
                        <div className="col-md-3 text-md-end">
                            <small className="text-muted d-block small text-uppercase fw-semibold">Hourly Rate</small>
                            <span className="fs-2 fw-bold text-orange">₹{car?.pricePerHour}</span>
                            <span className="text-muted small fw-bold">/hr</span>
                        </div>
                    </div>
                </div>
                {/* MAIN INPUT FORM (Full-Width Card) */}
                <div className="card p-4 shadow-sm border-0">
                    <h5 className="fw-bold mb-4 text-dark border-bottom pb-2">Configure Your Rental Options</h5>

                    {
                        errMsg !== undefined ? <div className="alert alert-danger" role="alert">
                            {errMsg}
                        </div> : ""
                    }

                    <div className="row g-3">
                        <div className="col-md-6">
                            <label className="form-label small fw-bold text-secondary">Pickup Date & Time</label>
                            <input
                                type="datetime-local"
                                className="form-control"
                                value={pickupDateTime}
                                onChange={(e) => setPickupDateTime(e.target.value)}
                            />
                            <div style={{ height: '24px', marginTop: '4px' }}>
                                {errPickUp !== undefined ?
                                    <div className="text-danger small fw-semibold">
                                        <i className="bi bi-exclamation-triangle-fill me-1"></i>
                                        {errPickUp}
                                    </div> : ""
                                }
                            </div>
                        </div>

                        <div className="col-md-6">
                            <label className="form-label small fw-bold text-secondary">Dropoff Date & Time</label>
                            <input
                                type="datetime-local"
                                className="form-control"
                                value={dropdownDateTime}
                                onChange={(e) => setDropdownDateTime(e.target.value)}
                            />
                            <div style={{ height: '24px', marginTop: '4px' }}>
                                {errDropDown !== undefined ?
                                    <div className="text-danger small fw-semibold">
                                        <i className="bi bi-exclamation-triangle-fill me-1"></i>
                                        {errDropDown}
                                    </div> : ""
                                }
                            </div>
                        </div>

                        {
                            hours !== 0 ?
                                <div className="col-md-12">
                                    <div className="form-control bg-light fw-bold text-dark text-center py-2">
                                        Total Duration : {hours} hrs
                                    </div>
                                </div> : ""
                        }



                        <div className="col-md-12">
                            <label className="form-label small fw-bold text-secondary">Delivery Service Protocol</label>
                            <select
                                className="form-select"
                                value={deliveryType}
                                onChange={(e) => setDeliveryType(e.target.value)}
                            >
                                <option value="SELF_PICKUP" >Store Pickup (Free)</option>
                                <option value="HOME_DELIVERY">Home Delivery (&#8377; 50.00)</option>
                            </select>
                        </div>


                        <div className="row g-3">
                            {/* 1. Pickup Location Input */}
                            <div className="col-md-6">
                                <label className="form-label small fw-bold text-secondary">Pickup Address Location</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    value={pickupLocation}
                                    onChange={(e) => setPickupLocation(e.target.value)}

                                />
                                <div style={{ height: '24px', marginTop: '4px' }}>
                                    {errPickUpLoc !== undefined ?
                                        <div className="text-danger small fw-semibold">
                                            <i className="bi bi-exclamation-triangle-fill me-1"></i>
                                            {errDropDown}
                                        </div> : ""
                                    }
                                </div>
                            </div>


                            {/* 3. Dropoff Location Input */}
                            <div className="col-md-6">
                                <label className="form-label small fw-bold text-secondary">Dropoff Address Location</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    value={dropdownLocation}
                                    onChange={differentOption ? (e) => setDropdownLocation(e.target.value) : undefined}
                                />
                                <div style={{ height: '24px', marginTop: '4px' }}>
                                    {errDropDownLoc !== undefined ?
                                        <div className="text-danger small fw-semibold">
                                            <i className="bi bi-exclamation-triangle-fill me-1"></i>
                                            {errDropDown}
                                        </div> : ""
                                    }
                                </div>
                            </div>
                        </div>




                        {/* Inline Coupon Code Section */}
                        <div className="col-12 mt-3">
                            <label className="form-label small fw-bold text-secondary">Apply Coupon</label>
                            <div className="input-group">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Enter coupon promo code"
                                    value={couponCode}
                                    onChange={(e) => setCouponCode(e.target.value)}
                                />
                                <button className="btn btn-dark px-4" type="button" onClick={(e) => applyCoupon(e)}>Apply Coupon</button>
                            </div>
                            {
                                couponSuccess !== undefined ?
                                    <div className="text-success small fw-semibold mt-1 d-block">
                                        {couponSuccess}</div> : ""
                            }
                            {
                                couponErr !== undefined ?
                                    <div className="text-danger small fw-semibold mt-1 d-block">
                                        {couponErr}</div> : ""
                            }
                        </div>

                        <div className="col-12 mt-4 pt-2 text-end">
                            <button

                                onClick={(e) => book(e)}
                            >
                                Book Now
                            </button>
                        </div>
                        {showSuccessModal && (
                            <div className="modal fade show d-block" tabIndex="-1" style={{ backgroundColor: 'rgba(0,0,0,0.5)' }}>
                                <div className="modal-dialog modal-dialog-centered"> {/* Removed modal-sm to give details breathing room */}
                                    <div className="modal-content shadow border-0">

                                        {/* Header */}
                                        <div className="modal-header bg-success text-white">
                                            <h5 className="modal-title fw-bold">Booking Summary</h5>
                                            <button type="button" className="btn-close btn-close-white" onClick={() => setShowSuccessModal(false)}></button>
                                        </div>

                                        {/* Body: Summary Lists */}
                                        <div className="modal-body p-4">

                                            {/* 1. Route Details */}
                                            <h6 className="text-secondary fw-bold text-uppercase small mb-2">Trip Summary</h6>
                                            <div className="bg-light p-3 rounded mb-3 small border">
                                                <div className="mb-2">📍 <strong>Pickup:</strong> {booking?.pickupLocation}</div>
                                                <div className="mb-0">🏁 <strong>Drop-off:</strong> {booking?.dropdownLocation}</div>
                                            </div>

                                            {/* 2. Timing Details */}
                                            <h6 className="text-secondary fw-bold text-uppercase small mb-2">Duration</h6>
                                            <div className="bg-light p-3 rounded mb-3 small border d-flex justify-content-between">
                                                <span>Rate: &#8377;{car?.pricePerHour}/hr</span>
                                                <span className="fw-bold text-dark">{hours} Hours</span>
                                            </div>

                                            {/* 3. Pricing Breakdown */}
                                            <h6 className="text-secondary fw-bold text-uppercase small mb-2">Billing Breakdown</h6>
                                            <ul className="list-group list-group-flush small border rounded">
                                                <li className="list-group-item d-flex justify-content-between align-items-center">
                                                    Original Price
                                                    <span>&#8377;{booking?.originalAmount}</span>
                                                </li>

                                                {/* Conditional Discount Display */}
                                                {couponCode && (
                                                    <li className="list-group-item d-flex justify-content-between align-items-center text-success fw-semibold">
                                                        Discount ({couponCode})
                                                        <span>- &#8377;{booking?.discountedAmount}</span>
                                                    </li>
                                                )}

                                                {/* Delivery Type Display */}
                                                <li className="list-group-item d-flex justify-content-between align-items-center">
                                                    Delivery Type
                                                    <span className="badge bg-secondary-subtle text-secondary-emphasis text-uppercase font-monospace">
                                                        {deliveryType === "HOME_DELIVERY" ? "Home Delivery" : "Self Pickup"}
                                                    </span>
                                                </li>

                                                {/* Conditional Delivery Charge Display */}
                                                {deliveryType === "HOME_DELIVERY" && (
                                                    <li className="list-group-item d-flex justify-content-between align-items-center">
                                                        Delivery Charge
                                                        <span>&#8377;{booking?.DeliveryCharge}</span>
                                                    </li>
                                                )}

                                                {/* Total Highlight */}
                                                <li className="list-group-item d-flex justify-content-between align-items-center bg-success-subtle text-success-emphasis fw-bold fs-6">
                                                    Total Paid Amount
                                                    <span>&#8377;{booking?.totalAmount}</span>
                                                </li>
                                            </ul>

                                        </div>

                                        {/* Footer Action */}
                                        <div className="modal-footer border-0 pt-0">
                                            <Link to={`/cars/confirmBooking/${booking?.bookingId}`}
                                                type="button"
                                                className="btn btn-success w-100 fw-bold"
                                                onClick={() => setShowSuccessModal(false)}
                                            >
                                                Confirm Booking
                                            </Link>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        )}

                    </div>
                </div>
            </div>

        </div>
    )
}

export default Booking