import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const CarSearch = () => {
    const now = new Date();
    now.setHours(now.getHours() + 6);
    const tomorrow = new Date(now);
    tomorrow.setDate(now.getDate() + 1);
    const formatDate = (date) => date.toISOString().slice(0, 16);
    
    const [locations, setLocations] = useState([]);
    const [pickup, setPickup] = useState(formatDate(now));
    const [dropDown, setDropDown] = useState(formatDate(tomorrow));
    const [loc, setLoc] = useState('');
    
    const [errPickUp, setErrPickUP] = useState();
    const [errDropDown, setErrDropDown] = useState();
    const [errLoc, setErrLoc] = useState();
    
    const [cars, setCars] = useState([]);
    const [size, setSize] = useState(4);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPage, setTotalPage] = useState(0);
    const [arry, setArry] = useState([]);
    const[sort,setSort] = useState('')
    const [carTransmission, setCarTransmission] = useState([]);
    const [fuelType, setFuelType] = useState([]);
    const [carTypes, setCarTypes] = useState([]);
    const [cfuel, setCfuel] = useState("");
    const [ctransmission, setCtransmission] = useState("");
    const [cType, setCType] = useState("");

    const locationApi = "http://localhost:8080/api/car/locations";
    const carSearchApi = "http://localhost:8080/api/car/search";
    const typeApi = "http://localhost:8080/api/car/getTypes";

    useEffect(() => {
        const loadInitialData = async () => {
            try {
                const locationRes = await axios.get(locationApi);
                setLocations(locationRes.data);
                
                const typeRes = await axios.get(typeApi);
                setCarTransmission(typeRes.data.carTransmissions);
                setCarTypes(typeRes.data.carTypes);
                setFuelType(typeRes.data.fuelTypes);
            } catch (err) {
                console.log(err);
            }
        };
        loadInitialData();
    }, []);

    

    const performSearch = async (page) => {
        const body = {
            "location": loc,
            "pickUpDate": pickup,
            "dropDownDate": dropDown,
            "fuelType": cfuel,
            "transmission": ctransmission,
            "type": cType
        };

        try {
            const response = await axios.post(carSearchApi + `?page=${page}&size=${size}&sort=${sort}`, body);
            setCars(response.data.cars);
            setTotalPage(response.data.totalPages);
            setArry(Array.from({ length: response.data.totalPages }));
            setCurrentPage(page);
                        setErrPickUP(undefined);
            setErrDropDown(undefined);
            setErrLoc(undefined);
        } 
        catch (err) {
            console.log(err.response?.data);
            setErrPickUP(err?.response?.data?.pickUpDate);
            setErrDropDown(err?.response?.data?.dropDownDate);
            setErrLoc(err?.response?.data?.location);
        }
    };



    const handleClearFilters = () => {
        setCfuel("");
        setCtransmission("");
        setCType("");
        setLoc('');
        setPickup(formatDate(now))
        setDropDown(formatDate(tomorrow))
setCars([])   
setSort('')
 };

    

    return (
        <div style={{ backgroundColor: '#f8f9fa', minHeight: '100vh', paddingBottom: '50px' }}>
            <div className="container py-4">
                
                {/* MAIN SEARCH SECTION */}
                <div className="card shadow-lg border-0 rounded-4 mb-4" style={{ backgroundColor: 'white' }}>
                    <div className="card-body p-4">
                        <h4 className="fw-bold mb-4" style={{ color: '#2d2d2d' }}>
                            <i className="bi bi-car-front me-2" style={{ color: '#FF8400' }}></i>
                            Find Your Perfect Ride
                        </h4>
                        
                        <div>
                            <div className="row g-3 align-items-start">
                                {/* Location Field */}
                                <div className="col-xl-3 col-md-6">
                                    <label className="form-label fw-semibold small text-uppercase" style={{ color: '#6c757d' }}>
                                        <i className="bi bi-geo-alt me-1" style={{ color: '#FF8400' }}></i>
                                        Location
                                    </label>
                                    <select 
                                        className="form-select rounded-3" 
                                        onChange={(e) => setLoc(e.target.value)}
                                        style={{ borderColor: '#e0e0e0' }}
                                        value={loc}
                                    >
                                            <option value=''>--- Select Location ---</option>

                                        {locations.map((l, index) => (
                                            <option key={index} value={l}>{l}</option>
                                        ))}
                                    </select>
                                    <div style={{ height: '24px', marginTop: '4px' }}>
                                        {errLoc && (
                                            <div className="text-danger small fw-semibold">
                                                <i className="bi bi-exclamation-triangle-fill me-1"></i>
                                                {errLoc}
                                            </div>
                                        )}
                                    </div>
                                </div>

                                {/* Pickup Date Field */}
                                <div className="col-xl-3 col-md-6">
                                    <label className="form-label fw-semibold small text-uppercase" style={{ color: '#6c757d' }}>
                                        <i className="bi bi-calendar-plus me-1" style={{ color: '#FF8400' }}></i>
                                        Pickup Date
                                    </label>
                                    <input 
                                        type="datetime-local" 
                                        className="form-control rounded-3" 
                                        onChange={(e) => setPickup(e.target.value)} 
                                        value={pickup}
                                        style={{ borderColor: '#e0e0e0' }}
                                    />
                                    <div style={{ height: '24px', marginTop: '4px' }}>
                                        {errPickUp && (
                                            <div className="text-danger small fw-semibold">
                                                <i className="bi bi-exclamation-triangle-fill me-1"></i>
                                                {errPickUp}
                                            </div>
                                        )}
                                    </div>
                                </div>

                                {/* Dropoff Date Field */}
                                <div className="col-xl-3 col-md-6">
                                    <label className="form-label fw-semibold small text-uppercase" style={{ color: '#6c757d' }}>
                                        <i className="bi bi-calendar-minus me-1" style={{ color: '#FF8400' }}></i>
                                        Dropoff Date
                                    </label>
                                    <input 
                                        type="datetime-local" 
                                        className="form-control rounded-3" 
                                        onChange={(e) => setDropDown(e.target.value)} 
                                        value={dropDown}
                                        style={{ borderColor: '#e0e0e0' }}
                                    />
                                    <div style={{ height: '24px', marginTop: '4px' }}>
                                        {errDropDown && (
                                            <div className="text-danger small fw-semibold">
                                                <i className="bi bi-exclamation-triangle-fill me-1"></i>
                                                {errDropDown}
                                            </div>
                                        )}
                                    </div>
                                </div>

                                {/* Search Button */}
                                <div className="col-xl-3 col-md-6">
                                    <button 
                                        type="submit" 
                                        onClick={()=>performSearch(0)}
                                        className="btn w-100 py-3 fw-bold rounded-3 shadow-sm"
                                        style={{ 
                                            backgroundColor: '#FF8400', 
                                            color: 'white',
                                            border: 'none',
                                            transition: 'all 0.3s ease',
                                            marginTop: '26px'
                                        }}
                                        onMouseEnter={(e) => {
                                            e.currentTarget.style.backgroundColor = '#E67300';
                                            e.currentTarget.style.transform = 'translateY(-2px)';
                                        }}
                                        onMouseLeave={(e) => {
                                            e.currentTarget.style.backgroundColor = '#FF8400';
                                            e.currentTarget.style.transform = 'translateY(0)';
                                        }}
                                    >
                                        <i className="bi bi-search me-2"></i>
                                        Search Cars
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                {/* FILTER SECTION with individual Search buttons */}
                <div className="card shadow-sm border-0 rounded-4 mb-4" style={{ backgroundColor: 'white' }}>
                    <div className="card-body p-4">
                        <div className="d-flex justify-content-between align-items-center mb-3">
                            <div className="d-flex align-items-center">
                                <i className="bi bi-funnel me-2" style={{ color: '#FF8400', fontSize: '1.2rem' }}></i>
                                <h6 className="fw-bold mb-0" style={{ color: '#2d2d2d' }}>Filter Vehicles</h6>
                            </div>
                            {/* Clear Filters Button */}
                            <button 
                                className="btn btn-sm"
                                style={{ color: '#FF8400' }}
                                onClick={handleClearFilters}
                            >
                                <i className="bi bi-arrow-counterclockwise me-1"></i>
                                Clear All
                            </button>
                        </div>
                        
                        <div className="row g-3 align-items-end">
                            {/* Transmission Filter */}
                            <div className="col-md-3">
                                <select 
                                    className="form-select rounded-3" 
                                    onChange={(e) => setCtransmission(e.target.value)}
                                    style={{ borderColor: '#e0e0e0' }}
                                    value={ctransmission}
                                >
                                    <option value="">All Transmissions</option>
                                    {carTransmission.map((t, index) => (
                                        <option key={index} value={t}>{t}</option>
                                    ))}
                                </select>
                            </div>

                            {/* Fuel Type Filter */}
                            <div className="col-md-2">
                                <select 
                                    className="form-select rounded-3" 
                                    onChange={(e) => setCfuel(e.target.value)}
                                    style={{ borderColor: '#e0e0e0' }}
                                    value={cfuel}
                                >
                                    <option value="">All Fuel Types</option>
                                    {fuelType.map((f, index) => (
                                        <option key={index} value={f}>{f}</option>
                                    ))}
                                </select>
                            </div>

                            {/* Car Type Filter */}
                            <div className="col-md-2">
                                <select 
                                    className="form-select rounded-3" 
                                    onChange={(e) => setCType(e.target.value)}
                                    style={{ borderColor: '#e0e0e0' }}
                                    value={cType}
                                >
                                    <option value="">All Car Types</option>
                                    {carTypes.map((type, index) => (
                                        <option key={index} value={type}>{type}</option>
                                    ))}
                                </select>
                            </div>
 <div className="col-md-2">
                <select
                    className="form-select rounded-3"
                    style={{ borderColor: '#e0e0e0' }}
                    onChange={(e) => setSort(e.target.value)}
                    value={sort}
                >
                    <option value="">Sort By Price</option>
                    <option value="ASC">Price: Low to High</option>
                    <option value="DESC">Price: High to Low</option>
                </select>
            </div>
                            {/* Filter Search Button */}
                            <div className="col-md-3">
                                <button 
                                    className="btn w-100 py-2 fw-bold rounded-3"
                                    onClick={()=>performSearch(0)}
                                    style={{ 
                                        backgroundColor: '#FF8400', 
                                        color: 'white',
                                        border: 'none',
                                        transition: 'all 0.3s ease'
                                    }}
                                    onMouseEnter={(e) => {
                                        e.currentTarget.style.backgroundColor = '#E67300';
                                    }}
                                    onMouseLeave={(e) => {
                                        e.currentTarget.style.backgroundColor = '#FF8400';
                                    }}
                                >
                                    <i className="bi bi-funnel me-2"></i>
                                    Apply Filters
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                

               

                {/* Car Cards Grid */}
                <div className="row g-4">
                    {cars.length === 0 ? (
                        <div className="col-12">
                            <div className="card border-0 shadow-sm rounded-4">
                                <div className="card-body p-5 text-center" style={{ backgroundColor: '#f8f9fa' }}>
                                    <i className="bi bi-car-front fs-1" style={{ color: '#FF8400' }}></i>
                                    <h5 className="mt-3" style={{ color: '#2d2d2d' }}>No vehicles found</h5>
                                    <p className="text-muted">Try adjusting your search criteria or filters</p>
                                    
                                </div>
                            </div>
                        </div>
                    ) : (
                        cars.map((car) => (
                            <div key={car.id} className="col-lg-3 col-md-6 col-sm-12">
                                <div className="card border-0 h-100 shadow-sm rounded-4 overflow-hidden" style={{ transition: 'transform 0.3s ease' }}>
                                    <div 
                                        className="position-relative overflow-hidden" 
                                        style={{ height: '200px', backgroundColor: '#f8f9fa' }}
                                    >
                                        <img
                                            src={`Images/${car?.imageUrl}`}
                                            alt={`${car?.brand} ${car?.model}`}
                                            className="w-100 h-100 object-fit-cover"
                                            style={{ transition: 'transform 0.3s ease' }}
                                            onMouseEnter={(e) => e.currentTarget.style.transform = 'scale(1.05)'}
                                            onMouseLeave={(e) => e.currentTarget.style.transform = 'scale(1)'}
                                        />
                                    </div>
                                    <div className="card-body p-4 d-flex flex-column">
                                        <div>
                                            <h5 className="fw-bold mb-1" style={{ color: '#2d2d2d' }}>
                                                {car.brand} {car.model}
                                            </h5>
                                            <p className="text-muted small mb-2">{car.type}</p>
                                            
                                            <div className="d-flex gap-2 mb-3 flex-wrap">
                                                <span className="badge px-3 py-2 rounded-pill" style={{ backgroundColor: '#f8f9fa', color: '#2d2d2d' }}>
                                                    <i className="bi bi-fuel-pump me-1" style={{ color: '#FF8400' }}></i>
                                                    {car.fuelType}
                                                </span>
                                                <span className="badge px-3 py-2 rounded-pill" style={{ backgroundColor: '#f8f9fa', color: '#2d2d2d' }}>
                                                    <i className="bi bi-people me-1" style={{ color: '#FF8400' }}></i>
                                                    {car.seats} Seats
                                                </span>
                                                <span className="badge px-3 py-2 rounded-pill" style={{ backgroundColor: '#f8f9fa', color: '#2d2d2d' }}>
                                                    <i className="bi bi-gear me-1" style={{ color: '#FF8400' }}></i>
                                                    {car.carTransmission}
                                                </span>
                                            </div>

                                            <p className="text-muted small mb-0">
                                                <i className="bi bi-geo-alt-fill me-1" style={{ color: '#FF8400' }}></i>
                                                {car.location}
                                            </p>
                                        </div>

                                        <hr className="my-3" style={{ borderColor: '#e0e0e0' }} />

                                        <div className="d-flex justify-content-between align-items-center">
                                            <div>
                                                <span className="fs-4 fw-bold" style={{ color: '#FF8400' }}>₹{car.pricePerHour}</span>
                                                <span className="text-muted small"> / hr</span>
                                            </div>
                                            <Link
                                                to={`/cars/cardetails/${car.id}`}
                                                state={{
                                                    pickupDateTime: pickup,
                                                    dropdownDateTime: dropDown
                                                }}
                                                className="btn rounded-3 px-4 fw-semibold"
                                                style={{ 
                                                    backgroundColor: '#FF8400', 
                                                    color: 'white',
                                                    transition: 'all 0.3s ease'
                                                }}
                                                onMouseEnter={(e) => {
                                                    e.currentTarget.style.backgroundColor = '#E67300';
                                                    e.currentTarget.style.transform = 'translateY(-2px)';
                                                }}
                                                onMouseLeave={(e) => {
                                                    e.currentTarget.style.backgroundColor = '#FF8400';
                                                    e.currentTarget.style.transform = 'translateY(0)';
                                                }}
                                            >
                                                View Details
                                            </Link>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))
                    )}
                </div>

                {/* PAGINATION */}
             
                    <nav className="mt-4">
                        <ul className="pagination justify-content-center">
                            <li className={`page-item ${currentPage === 0 ? 'disabled' : ''}`}>
                                <button 
                                    className="page-link rounded-3 me-2" 
                                    onClick={() => performSearch(currentPage - 1)}
                                    style={{ 
                                        borderColor: '#e0e0e0',
                                        color: '#2d2d2d'
                                    }}
                                >
                                    <i className="bi bi-chevron-left"></i> Previous
                                </button>
                            </li>
                            
                            {arry.map((_,index) => (
                                <li className={`page-item ${currentPage === index ? 'active' : ''}`} key={index}>
                                    <button 
                                        className="page-link rounded-3 me-1" 
                                        onClick={() => performSearch(index)}
                                        style={currentPage === index ? {
                                            backgroundColor: '#FF8400',
                                            borderColor: '#FF8400',
                                            color: 'white'
                                        } : {
                                            borderColor: '#e0e0e0',
                                            color: '#2d2d2d'
                                        }}
                                    >
                                        {index + 1}
                                    </button>
                                </li>
                            ))}
                            
                            <li className={`page-item ${currentPage === totalPage - 1 ? 'disabled' : ''}`}>
                                <button 
                                    className="page-link rounded-3 ms-1" 
                                    onClick={() => performSearch(currentPage + 1)}
                                    style={{ 
                                        borderColor: '#e0e0e0',
                                        color: '#2d2d2d'
                                    }}
                                >
                                    Next <i className="bi bi-chevron-right"></i>
                                </button>
                            </li>
                        </ul>
                    </nav>
               
            </div>
        </div>
    );
};

export default CarSearch;