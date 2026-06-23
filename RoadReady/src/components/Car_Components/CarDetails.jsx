import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useLocation, useParams } from "react-router-dom";
import NavbarCustomer from "../NavbarCustomer";

const CarDetails = () => {
  const { car_id } = useParams();
  const [car, setCar] = useState(null);
  const [reviews, setReviews] = useState([]);
  const [avgRating, setAvgRating] = useState(0);

  const location = useLocation();
  const { pickupDateTime, dropdownDateTime } = location.state;
  const [pickup, setPickup] = useState(pickupDateTime)
  const [dropDown, setDropDown] = useState(dropdownDateTime)


  const api = "http://localhost:8080/api/car/get/";
  const reviewapi = "http://localhost:8080/api/review/car/";
  const avgRatingApi = "http://localhost:8080/api/review/avgRating/";

  useEffect(() => {
    const getCar = async () => {
      try {
        const carRes = await axios.get(api + car_id);
        setCar(carRes.data);
      } catch (err) {
        console.log(err);
      }
    };

    const review = async () => {
      try {
        const reviewres = await axios.get(reviewapi + car_id);
        setReviews(reviewres.data.reviews || []);
      } catch (err) {
        console.log(err);
      }
    };

    const rating = async () => {
      try {
        const ratingRes = await axios.get(avgRatingApi + car_id);
        setAvgRating(ratingRes.data);
      } catch (err) {
        console.log(err);
      }
    };
    review();
    getCar();
    rating();
  }, [car_id]);



  return (
    <div style={{ backgroundColor: '#f8f9fa', minHeight: '100vh' }}>

      <div className="container py-5">
        <div className="row g-4">

          {/* LEFT SIDE: 3/4 Width */}
          <div className="col-lg-9">

            {/* Image Showcase - Enhanced */}
            <div className="card shadow-sm mb-4 border-0 rounded-4 overflow-hidden">
              <div className="bg-white p-4 d-flex align-items-center justify-content-center" style={{ minHeight: '350px' }}>
                <img
                  src={`/Images/${car?.imageUrl}`}
                  alt={`${car?.brand} ${car?.model}`}
                  className="img-fluid object-fit-contain"
                  style={{ maxHeight: "380px", width: "auto", transition: 'transform 0.3s ease' }}
                  onMouseOver={(e) => e.currentTarget.style.transform = 'scale(1.02)'}
                  onMouseOut={(e) => e.currentTarget.style.transform = 'scale(1)'}
                />
              </div>
            </div>

            <div className="card shadow-sm mb-4 border-0 rounded-4">
              <div className="card-body p-4">
                <div className="row align-items-center">
                  <div className="col-md-8">
                    <h1 className="fw-bold mb-1" style={{ color: '#2d2d2d' }}>
                      {car?.brand} {car?.model}
                    </h1>

                    {/* Model Year */}
                    <p className="text-muted fs-5 mb-2">
                      <i className="bi bi-calendar3 me-2" style={{ color: '#FF8400' }}></i>
                      Model Year: <span className="fw-semibold" style={{ color: '#2d2d2d' }}>{car?.modelYear}</span>
                    </p>

                    {/* Address Line - Placed precisely between Model Year and Review metrics */}
                    <p className="text-muted mb-3 d-flex align-items-start">
                      <i className="bi bi-geo-alt-fill me-2 mt-1" style={{ color: '#FF8400' }}></i>
                      <span className="fw-semibold" style={{ color: '#2d2d2d' }}>{car?.address}</span>
                    </p>

                    {/* Review Badge Content Area inside the Column block */}
                    <div className="d-flex align-items-center gap-3">
                      <div className="d-inline-block px-3 py-1 rounded-3" style={{ backgroundColor: '#FF8400', color: 'white' }}>
                        <i className="bi bi-star-fill me-2 small"></i>
                        <span className="fw-bold">{avgRating || "0.0"}</span>
                      </div>
                      <p className="text-muted small mb-0">
                        <i className="bi bi-chat-dots me-1"></i>
                        {reviews.length} Verified Reviews
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>


            {/* Specifications Grid - Enhanced */}
            <div className="card shadow-sm mb-4 border-0 rounded-4">
              <div className="card-body p-4">
                <h4 className="fw-bold mb-4" style={{ color: '#2d2d2d' }}>
                  <i className="bi bi-gear me-2" style={{ color: '#FF8400' }}></i>
                  Vehicle Specifications
                </h4>
                <div className="row g-3">
                  <div className="col-sm-6 col-md-4">
                    <div className="p-3 rounded-3 h-100" style={{ backgroundColor: '#f8f9fa', borderLeft: '4px solid #FF8400' }}>
                      <p className="mb-1 text-muted small text-uppercase fw-bold">Transmission</p>
                      <span className="fw-bold" style={{ color: '#2d2d2d' }}>{car?.carTransmission}</span>
                    </div>
                  </div>
                  <div className="col-sm-6 col-md-4">
                    <div className="p-3 rounded-3 h-100" style={{ backgroundColor: '#f8f9fa', borderLeft: '4px solid #FF8400' }}>
                      <p className="mb-1 text-muted small text-uppercase fw-bold">Fuel Type</p>
                      <span className="fw-bold" style={{ color: '#2d2d2d' }}>{car?.fuelType}</span>
                    </div>
                  </div>
                  <div className="col-sm-6 col-md-4">
                    <div className="p-3 rounded-3 h-100" style={{ backgroundColor: '#f8f9fa', borderLeft: '4px solid #FF8400' }}>
                      <p className="mb-1 text-muted small text-uppercase fw-bold">Seating</p>
                      <span className="fw-bold" style={{ color: '#2d2d2d' }}>{car?.seats} Seats</span>
                    </div>
                  </div>
                  <div className="col-sm-6 col-md-4">
                    <div className="p-3 rounded-3 h-100" style={{ backgroundColor: '#f8f9fa', borderLeft: '4px solid #FF8400' }}>
                      <p className="mb-1 text-muted small text-uppercase fw-bold">Vehicle Class</p>
                      <span className="fw-bold" style={{ color: '#2d2d2d' }}>{car?.type || "N/A"}</span>
                    </div>
                  </div>
                  <div className="col-md-8">
                    <div className="p-3 rounded-3 h-100" style={{ backgroundColor: '#f8f9fa', borderLeft: '4px solid #FF8400' }}>
                      <p className="mb-1 text-muted small text-uppercase fw-bold">Pickup Location</p>
                      <span className="fw-bold" style={{ color: '#2d2d2d' }}>
                        <i className="bi bi-geo-alt-fill me-2" style={{ color: '#FF8400' }}></i>
                        {car?.location}
                      </span>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            {/* Customer Reviews - Enhanced */}
            <div className="mt-5">
              <h4 className="fw-bold mb-4" style={{ color: '#2d2d2d' }}>
                <i className="bi bi-chat-left-quote me-2" style={{ color: '#FF8400' }}></i>
                Customer Reviews
              </h4>
              {reviews.length === 0 ? (
                <div className="card border-0 shadow-sm rounded-4">
                  <div className="card-body p-4 text-center" style={{ backgroundColor: '#f8f9fa' }}>
                    <i className="bi bi-chat-square-text fs-1" style={{ color: '#FF8400' }}></i>
                    <p className="text-muted mt-3 mb-0">No reviews posted for this vehicle yet.</p>
                  </div>
                </div>
              ) : (
                <div className="row g-3">
                  {reviews.map((review, index) => (
                    <div className="col-md-6" key={index}>
                      <div className="card h-100 shadow-sm border-0 rounded-4">
                        <div className="card-body p-4">
                          <div className="d-flex justify-content-between align-items-start mb-3">
                            <div>
                              <span className="fw-bold" style={{ color: '#2d2d2d' }}>{review.customerName}</span>
                              <div className="mt-2">
                                {Array.from({ length: 5 }, (_, i) => (
                                  <i
                                    key={i}
                                    className={`bi ${i < review.rating ? "bi-star-fill" : "bi-star"}`}
                                    style={{ color: i < review.rating ? '#FF8400' : '#ddd', marginRight: '2px' }}
                                  ></i>
                                ))}
                              </div>
                            </div>
                            <span className="text-muted small">
                              <i className="bi bi-clock me-1"></i>
                              {new Date(review.createdAt).toLocaleDateString()}
                            </span>
                          </div>
                          <p className="text-muted mb-0" style={{ fontSize: '0.95rem' }}>"{review.comment}"</p>
                        </div>
                      </div>
                    </div>
                  ))}
                </div>
              )}
            </div>
          </div>

          {/* RIGHT SIDE: 1/4 Width - Sticky Booking Sidebar */}
          <div className="col-lg-3">
            <div className="card shadow-sm position-sticky rounded-4 border-0" style={{ top: "25px" }}>
              <div className="card-body p-4">
                {/* Header with gradient */}
                <div className="text-center mb-4 p-3 rounded-3">
                  <h5 className="fw-bold mb-0">
                    Book Now
                  </h5>
                </div>

                {/* Price */}
                <div className="mb-4 text-center">
                  <p className="text-muted small mb-1">Rental Fare</p>
                  <h2 className="fw-bold mb-0" style={{ color: '#2d2d2d' }}>
                    ₹{car?.pricePerHour}
                    <span className="fs-6 text-muted fw-normal"> / hour</span>
                  </h2>
                </div>

                <hr style={{ borderColor: '#e0e0e0' }} />

                {/* Dates */}
                <div className="mb-3">
                  <label className="form-label small fw-bold text-secondary">Pickup Date & Time</label>
                  <input
                    type="datetime-local"
                    className="form-control"
                    value={pickup}
                    onChange={(e) => setPickup(e.target.value)}
                  />
                </div>

                <div className="mb-4">
                  <label className="form-label small fw-bold text-secondary">Dropoff Date & Time</label>
                  <input
                    type="datetime-local"
                    className="form-control"
                    value={dropDown}
                    onChange={(e) => setDropDown(e.target.value)}
                  />
                </div>

                {/* Book Button */}
                <Link
                  to={`/cars/booking/${car?.id}`}
                  state={{ pickup: pickupDateTime, dropdown: dropdownDateTime }}
                  className="btn w-100 py-3 fw-bold rounded-3 shadow-sm"
                  style={{
                    backgroundColor: '#FF8400',
                    color: 'white',
                    transition: 'all 0.3s ease',
                    border: 'none'
                  }}

                >
                  <i className="bi bi-arrow-right-circle me-2"></i>
                  Proceed to Book
                </Link>


              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  );
};

export default CarDetails;