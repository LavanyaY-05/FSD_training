import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const LandingSearch = () => {

    const navigate = useNavigate()

  return (
    <div>
    <div
        className="position-relative d-flex align-items-center justify-content-center text-center"
        style={{
          backgroundImage: "url('/Images/image2.png')",
          backgroundSize: 'cover',
          backgroundPosition: 'center',
          minHeight: '80vh', // Clean height for the hero area
        }}
      >
        {/* Dark tint overlay */}
        <div
          className="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center justify-content-center px-3"
          style={{ backgroundColor: 'rgba(15, 23, 42, 0.75)' }}
        >
          <div className="container d-flex flex-column align-items-center" style={{ maxWidth: '800px' }}>
            
            {/* Bold Centered Typography */}
            <h1 className="text-white display-4 fw-black mb-3" style={{ fontWeight: 800 }}>
              Your Ultimate Road Trip Starts Here
            </h1>
            
            <p className="text-light lead fs-5 mb-4 opacity-75" style={{ maxWidth: '600px' }}>
              Unbeatable rates. Unlimited miles. Infinite memories.
            </p>
            
            {/* Centered Action Button */}
            <button
              type="button"
              onClick={() => navigate("/login")}
              className="btn btn-lg px-5 py-3 fw-bold rounded-pill text-white text-uppercase"
              style={{
                backgroundColor: '#ff9900',
                border: 'none',
                letterSpacing: '0.5px',
              }}
            >
              Choose Your Car
            </button>

          </div>
        </div>
      </div>

      <section className="py-5" style={{ backgroundColor: '#f8f9fa' }}>
        <div className="container py-4">
          <h2 className="text-center fw-bold mb-5" style={{ color: '#2d2d2d' }}>
            How It Works
          </h2>
          <div className="row g-4">

            {/* Step 1 */}
            <div className="col-md-4">
              <div
                className="d-flex flex-column align-items-center text-center h-100 shadow-sm"
                style={{ background: '#fff', borderRadius: '12px', padding: '36px 24px' }}
              >
                <div style={{ fontSize: '2.5rem', color: '#f07a1a', marginBottom: '20px' }}>📍</div>
                <h5 style={{ fontWeight: 700, color: '#2d2d2d', marginBottom: '10px' }}>
                  Step 1: Choose Location &amp; Date
                </h5>
                <p style={{ color: '#6c757d', fontSize: '0.9rem', margin: 0 }}>
                  Select your pickup spot and perfect timeline vectors easily.
                </p>
              </div>
            </div>

            {/* Step 2 */}
            <div className="col-md-4">
              <div
                className="d-flex flex-column align-items-center text-center h-100 shadow-sm"
                style={{ background: '#fff', borderRadius: '12px', padding: '36px 24px' }}
              >
                <div style={{ fontSize: '2.5rem', color: '#f07a1a', marginBottom: '20px' }}>🚗</div>
                <h5 style={{ fontWeight: 700, color: '#2d2d2d', marginBottom: '10px' }}>
                  Step 2: Pick Your Car
                </h5>
                <p style={{ color: '#6c757d', fontSize: '0.9rem', margin: 0 }}>
                  Filter through hundreds of pristine sedans, hatchbacks, and SUVs.
                </p>
              </div>
            </div>

            {/* Step 3 */}
            <div className="col-md-4">
              <div
                className="d-flex flex-column align-items-center text-center h-100 shadow-sm"
                style={{ background: '#fff', borderRadius: '12px', padding: '36px 24px' }}
              >
                <div style={{ fontSize: '2.5rem', color: '#f07a1a', marginBottom: '20px' }}>🔑</div>
                <h5 style={{ fontWeight: 700, color: '#2d2d2d', marginBottom: '10px' }}>
                  Step 3: Enjoy the Ride
                </h5>
                <p style={{ color: '#6c757d', fontSize: '0.9rem', margin: 0 }}>
                  Get instant approval and unlock premium highway driving comfort.
                </p>
              </div>
            </div>

          </div>
        </div>
      </section>
      </div>
  );
}


export default LandingSearch;









//       const now = new Date()
//     now.setHours(now.getHours() + 6)
//     const tomorrow = new Date(now)
//     tomorrow.setDate(now.getDate() + 1)
//     const formatDate = (date) => date.toISOString().slice(0, 16)
//     const [locations, setLocations] = useState([])
//     const [pickup, setPickup] = useState(formatDate(now))
//     const [dropDown, setDropDown] = useState(formatDate(tomorrow))
//         const [loc, setLoc] = useState();
//         const [errPickUp, setErrPickUP] = useState();
//             const [errDropDown, setErrDropDown] = useState();
//             const [errLoc, setErrLoc] = useState();
//     const locationApi = "http://localhost:8080/api/car/locations";


//             useEffect(() => {
//         const getLocation = async () => {
//             console.log("reached")
//             try {
//                 const locationRes = await axios.get(locationApi);
//                 setLocations(locationRes.data);
//                             console.log(locationRes.data)

//             }
//             catch(err){
//             console.log(err)

//             }
//         }
// getLocation()
//     }, [])
//     return (
//        <div style={{ backgroundColor: '#f8f9fa', minHeight: '100vh', paddingBottom: '50px' }}>
//             <div className="container py-4">
                
//                 {/* MAIN SEARCH SECTION */}
//                 <div className="card shadow-lg border-0 rounded-4 mb-4" style={{ backgroundColor: 'white' }}>
//                     <div className="card-body p-4">
//                         <h4 className="fw-bold mb-4" style={{ color: '#2d2d2d' }}>
//                             <i className="bi bi-car-front me-2" style={{ color: '#ff9525' }}></i>
//                             Find Your Perfect Ride
//                         </h4>
                        
//                         <form>
//                             <div className="row g-3 align-items-start">
//                                 <div className="col-xl-3 col-md-6">
//                                     <label className="form-label fw-semibold small text-uppercase" style={{ color: '#6c757d' }}>
//                                         <i className="bi bi-geo-alt me-1" style={{ color: '#ff9525' }}></i>
//                                         Location
//                                     </label>
//                                     <select 
//                                         className="form-select rounded-3" 
//                                         onChange={(e) => setLoc(e.target.value)}
//                                         style={{ borderColor: '#e0e0e0' }}
//                                         value={loc}
//                                     >
//                                     <option value="">Select Location</option>

//                                         {locations.map((l, index) => (
//                                             <option key={index} value={l}>{l}</option>
//                                         ))}
//                                     </select>
//                                                                 <div style={{ height: '24px', marginTop: '4px' }}>

//                                     {errLoc && (
//                                         <div className="text-danger small fw-semibold mt-1">
//                                             {errLoc}
//                                         </div>
//                                     )}
//                                     </div>
//                                 </div>

//                                 <div className="col-xl-3 col-md-6">
//                                     <label className="form-label fw-semibold small text-uppercase" style={{ color: '#6c757d' }}>
//                                         <i className="bi bi-calendar-plus me-1" style={{ color: '#ff9525' }}></i>
//                                         Pickup Date
//                                     </label>
//                                     <input 
//                                         type="datetime-local" 
//                                         className="form-control rounded-3" 
//                                         onChange={(e) => setPickup(e.target.value)} 
//                                         value={pickup}
//                                         style={{ borderColor: '#e0e0e0' }}
//                                     />
//                                                                 <div style={{ height: '24px', marginTop: '4px' }}>

//                                     {errPickUp && (
//                                         <div className="text-danger small fw-semibold mt-1">
//                                             {errPickUp}
//                                         </div>
//                                     )}
//                                     </div>
//                                 </div>

//                                 <div className="col-xl-3 col-md-6">
//                                     <label className="form-label fw-semibold small text-uppercase" style={{ color: '#6c757d' }}>
//                                         <i className="bi bi-calendar-minus me-1" style={{ color: '#ff9525' }}></i>
//                                         Dropoff Date
//                                     </label>
//                                     <input 
//                                         type="datetime-local" 
//                                         className="form-control rounded-3" 
//                                         onChange={(e) => setDropDown(e.target.value)} 
//                                         value={dropDown}
//                                         style={{ borderColor: '#e0e0e0' }}
//                                     />
//                             <div style={{ height: '24px', marginTop: '4px' }}>

//                                     {errDropDown && (
//                                         <div className="text-danger small fw-semibold mt-1">
//                                             {errDropDown}
//                                         </div>
//                                     )}
//                                     </div>
//                                 </div>

//                                 <div className="col-xl-3 col-md-6 mb-8">
//                                     <Link to={`/cars`}
//                                                 state={{
//                                                     pickupDateTime: pickup,
//                                                     dropdownDateTime: dropDown,
//                                                     selectedLocation:loc
//                                                 }}
//                                         type="submit" 
//                                         className="btn w-100 py-2 fw-bold rounded-3 shadow-sm"
//                                           style={{ 
//                                             backgroundColor: '#FF8400', 
//                                             color: 'white',
//                                             border: 'none',
//                                             transition: 'all 0.3s ease',
//                                             marginTop: '30px'
//                                         }}
                                      
                                        
//                                     >
//                                         <i className="bi bi-search me-2"></i>
//                                         Search Cars
//                                     </Link>
//                                 </div>
//                             </div>
//                         </form>
//                     </div>
//           </div>
//       </div>
// </div>
    
