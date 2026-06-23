import axios from "axios"
import { useEffect, useState } from "react"
import { Link } from "react-router-dom"

const Cars = () => {

    const carApi = "http://localhost:8080/api/car/all"
    const deleteApi = "http://localhost:8080/api/car/delete/"
    const typeApi = "http://localhost:8080/api/car/getTypes"
    const addApi = "http://localhost:8080/api/car/add"
    const imgApi = "http://localhost:8080/api/car/uploadImage"
    const [cars, setCars] = useState([])
    const [currentPage, setCurrentPage] = useState(0)
    const [totalPage, setTotalPage] = useState(0)
    const [size, setSize] = useState(7)


    const [carTransmission, setCarTransmission] = useState([])
    const [fuelType, setFuelType] = useState([])
    const [carTypes, setCarTypes] = useState([])
    let count = 0
    const [arry, setArry] = useState([])

    const [brand, setBrand] = useState()
    const [model, setModel] = useState()
    const [type, setType] = useState()
    const [modelYear, setModelYear] = useState()
    const [carTransmissionValue, setCarTransmissionValue] = useState()
    const [fuelTypeValue, setFuelTypeValue] = useState()
    const [seats, setSeats] = useState()
    const [mileage, setMileage] = useState()
    const [pricePerHour, setPricePerHour] = useState()
    const [location, setLocation] = useState()
    const [imageFile, setImageFile] = useState(null)

    const [errMsg, setErrMsg] = useState()

    const [successMsg, setSuccessMsg] = useState()

    const [file, setFile] = useState()
    const [filename, setFilename] = useState()
    const [errFile, setErrFile] = useState()
    const [successFile, setSuccessFile] = useState()



    const config = {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }


    useEffect(() => {
        const getAll = async () => {
            const response = await axios.get(carApi + `?page=${currentPage}&size=${size}`)
            console.log(response.data)
            setCars(response.data.cars)
            setTotalPage(response.data.totalPages)
            setArry(Array.from({ length: totalPage }))
        }
        getAll()
    }, [currentPage])

    const getTypesAndStatus = async () => {
        try {
            const response = await axios.get(typeApi)
            setCarTransmission(response.data.carTransmissions)
            setCarTypes(response.data.carTypes)
            setFuelType(response.data.fuelTypes)
        }
        catch (err) {

        }

    }

    const deleteCar = async (id) => {

        try {
            const response = await axios.delete((deleteApi + id), config)
            console.log(response)
            let tempCars = [...cars].filter(c => c.id !== id)
            setCars([...tempCars])

        }
        catch (err) {

        }


    }

    const saveCar = async (e) => {
        e.preventDefault()
        setSuccessMsg(undefined)
        setErrMsg(undefined)
        let body = {

            "brand": brand,
            "model": model,
            "type": type,
            "modelYear": modelYear,
            "carTransmission": carTransmissionValue,
            "fuelType": fuelTypeValue,
            "seats": seats,
            "mileage": mileage,
            "PricePerHour": pricePerHour,
            "location": location,
            "image": imageFile


        }
        try {
            const response = await axios.post(addApi, body, config)
            console.log(response)
            setSuccessMsg("Car Added to DB")
            setErrMsg(undefined)
        }
        catch (err) {
            console.log(err)
            setSuccessMsg(undefined)
            setErrMsg("Failed To add, Try Again")
        }

    }

    const handleFile = (e) => {
        e.preventDefault()
        setFile(e.target.files[0])
        setErrFile(undefined)

    }

    const uploadFile = async (e) => {
        if (!file) {
            setErrFile("Please Upload The Image")
            return
        }

        const formData = new FormData()
        formData.append('file', file)
        const response = await axios.post(imgApi, formData, config)
        setImageFile(response.data)
    }

    return (

        <div className="dashboard-main">
            <div className="d-flex align-items-center justify-content-between mb-4">
                <h2 className="fw-bold">Car Inventory Management</h2>
                <button
                    onClick={getTypesAndStatus}
                    className="btn btn-primary fw-semibold"
                    data-bs-toggle="modal"
                    data-bs-target="#car"
                >
                    <i className="bi bi-plus-lg me-1"></i> Add New Car
                </button>
                <div class="modal fade" id="car" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div className="modal-body">
                                    <form onSubmit={(e) => saveCar(e)}>
                                        {
                                            errMsg != undefined ?
                                                <div class="alert alert-danger" > {errMsg}</div> : ""
                                        }
                                        {
                                            successMsg != undefined ?
                                                <div class="alert alert-primary" > {successMsg}</div> : ""
                                        }
                                        <div className="row g-3">

                                            <div className="col-md-6 mb-3">
                                                <label className="form-label text-muted small fw-bold">Brand</label>
                                                <input type="text" className="form-control" onChange={(e) => setBrand(e.target.value)} required />
                                            </div>

                                            <div className="col-md-6 mb-3">
                                                <label className="form-label text-muted small fw-bold">Model</label>
                                                <input type="text" className="form-control" onChange={(e) => setModel(e.target.value)} required />
                                            </div>

                                            {/* Dynamic Select Menus using your typeApi lists */}
                                            <div className="col-md-4 mb-3">
                                                <label className="form-label text-muted small fw-bold">Car Type</label>
                                                <select className="form-select" onChange={(e) => setType(e.target.value)} required>
                                                    <option value="">--- select Type ---</option>
                                                    {carTypes.map((t, index) => (
                                                        <option key={index} value={t}>{t}</option>
                                                    ))}
                                                </select>
                                            </div>

                                            <div className="col-md-4 mb-3">
                                                <label className="form-label text-muted small fw-bold">Transmission System</label>
                                                <select className="form-select" onChange={(e) => setCarTransmissionValue(e.target.value)} required>
                                                    <option value="">--- select Transmission ---</option>
                                                    {carTransmission.map((t, index) => (
                                                        <option key={index} value={t}>{t}</option>
                                                    ))}
                                                </select>
                                            </div>

                                            <div className="col-md-4 mb-3">
                                                <label className="form-label text-muted small fw-bold">Fuel Configuration</label>
                                                <select className="form-select" onChange={(e) => setFuelTypeValue(e.target.value)} required>
                                                    <option value="">--- select Fuel Type ---</option>
                                                    {fuelType.map((f, index) => (
                                                        <option key={index} value={f}>{f}</option>
                                                    ))}
                                                </select>
                                            </div>

                                            <div className="col-md-4 mb-3">
                                                <label className="form-label text-muted small fw-bold">Model Year</label>
                                                <input type="number" className="form-control" onChange={(e) => setModelYear(e.target.value)} required />
                                            </div>

                                            <div className="col-md-4 mb-3">
                                                <label className="form-label text-muted small fw-bold">Seats Capacity</label>
                                                <input type="number" className="form-control" onChange={(e) => setSeats(e.target.value)} required />
                                            </div>

                                            <div className="col-md-4 mb-3">
                                                <label className="form-label text-muted small fw-bold">Mileage (km/l)</label>
                                                <input type="number" step="0.1" className="form-control" onChange={(e) => setMileage(e.target.value)} required />
                                            </div>

                                            <div className="col-md-6 mb-3">
                                                <label className="form-label text-muted small fw-bold">Price Per Hour (₹)</label>
                                                <input type="number" step="0.01" className="form-control" onChange={(e) => setPricePerHour(e.target.value)} required />
                                            </div>

                                            <div className="col-md-6 mb-3">
                                                <label className="form-label text-muted small fw-bold">Base Location Hub</label>
                                                <input type="text" className="form-control" onChange={(e) => setLocation(e.target.value)} required />
                                            </div>
                                            {
                                                errFile !== undefined ?
                                                    <span style={{ color: 'red' }}>{errFile}</span> : ""
                                            }
                                            <div>
                                                <label className="form-label text-muted small fw-bold">Upload Car Image</label>
                                                <input type="file" className="form-control" onChange={(e) => handleFile(e)} />
                                                <button onClick={(e) => uploadFile(e)}>Upload Image</button>
                                            </div>



                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <button type="button" class="btn btn-primary">Save changes</button>
                                        </div>
                                    </form>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>
            </div>





            <div className="table-responsive">
                <table className="table text-nowrap">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Brand</th>
                            <th scope="col">Model</th>
                            <th scope="col">Type</th>


                            <th scope="col" style={{ whiteSpace: 'nowrap' }}>Model Year</th>
                            <th scope="col" style={{ whiteSpace: 'nowrap' }}>Fuel Type</th>
                            <th scope="col" style={{ whiteSpace: 'nowrap' }}>Car Transmission</th>
                            <th scope="col">Seats</th>
                            <th scope="col">Price</th>
                            <th scope="col">Location</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            cars.map((c, index) => (
                                <tr key={index}>
                                    <th scope="row">{c.id}</th>
                                    <td>{c.brand}</td>
                                    <td>{c.model}</td>
                                    <td>{c.type}</td>
                                    <td>{c.modelYear}</td>
                                    <td>{c.fuelType}</td>
                                    <td>{c.carTransmission}</td>
                                    <td>{c.seats}</td>
                                    <td>{c.pricePerHour}</td>
                                    <td>{c.location}</td>
                                    <td>
                                        <button className="btn btn-link p-0 text-decoration-none" onClick={(e) => deleteCar(c.id)} ><i className="bi bi-trash"></i></button>
                                        &nbsp;&nbsp;&nbsp;
                                        <button
                                            className="btn btn-link p-0 text-primary me-2"
                                            title="Edit Car"
                                            data-bs-toggle="modal"
                                            data-bs-target="#car"
                                            onClick={() => {
                                                getTypesAndStatus();

                                            }}
                                        >
                                            <i className="bi bi-pencil-square fs-5"></i>
                                        </button>                                 </td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div> {/* End of table-responsive */}

            <nav aria-label="Page navigation example">
                <ul className="pagination" style={{ display: 'flex', justifyContent: 'center', listStyle: 'none', padding: 0 }}>

                    <li className="page-item"><button className="page-link" disabled={currentPage === 0} onClick={(e) => setCurrentPage(currentPage - 1)}>Previous</button></li>
                    {
                        arry.map((_, index) => (
                            <li className="page-item" key={index}>
                                <button className="page-link" onClick={(e) => setCurrentPage(index)}>{count = count + 1}</button></li>
                        ))
                    }
                    <li className="page-item"><button className="page-link" disabled={currentPage === totalPage - 1} onClick={(e) => setCurrentPage(currentPage + 1)} >Next</button></li>
                </ul>
            </nav>
        </div>
    )


}
export default Cars