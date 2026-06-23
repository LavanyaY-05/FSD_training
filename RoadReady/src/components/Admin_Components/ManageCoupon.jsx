import axios from "axios"
import { useEffect, useState } from "react"

const Coupons = () => {

    const couponApi = "http://localhost:8080/api/coupons/getAll"
    const deleteApi = "http://localhost:8080/api/coupons/delete/"
    const addApi = "http://localhost:8080/api/coupons/add"

    const [coupons, setCoupons] = useState([])

    const [couponCode, setCouponCode] = useState()
    const [discountValue, setDiscountValue] = useState()
    const [expiryDate, setExpiryDate] = useState()
    const [maxUsage, setMaxUsage] = useState()

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
                const response = await axios.get(couponApi, config)
                console.log(response.data)
                setCoupons(response.data)
            }
            catch (err) {

            }
        }
        getAll()
    }, [])

    const deleteCoupon = async (id) => {
        try {
            const response = await axios.delete((deleteApi +id), config)
            console.log(response)
            let tempCoupons = [...coupons].filter(c => c.id !== id)
            setCoupons([...tempCoupons])
        }
        catch (err) {

        }

        
    }

    const saveCoupon = async (e) => {
        e.preventDefault()
        setSuccessMsg(undefined)
        setErrMsg(undefined)

        let body = {
            "couponCode": couponCode,
            "discountValue": discountValue,
            "expiryDate": expiryDate,
            "maxUsage": maxUsage
        }

        try {
            const response = await axios.post(addApi, body, config)
            console.log(response)
            setCoupons([...coupons, response.data])
            setSuccessMsg("Coupon Added to DB")
            setErrMsg(undefined)
        }
        catch (err) {
            console.log(err)
            setSuccessMsg(undefined)
            setErrMsg("Failed To add, Try Again")
        }
    }

    return (

        <div className="dashboard-main">
            <div className="d-flex align-items-center justify-content-between mb-4">
                <h2 className="fw-bold">Coupon Management</h2>
                <button
                    className="btn btn-primary fw-semibold"
                    data-bs-toggle="modal"
                    data-bs-target="#coupon"
                >
                    <i className="bi bi-plus-lg me-1"></i> Add New Coupon
                </button>
                <div className="modal fade" id="coupon" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title" id="staticBackdropLabel">Add Coupon</h5>
                                <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div className="modal-body">
                                <form onSubmit={(e) => saveCoupon(e)}>
                                    {
                                        errMsg !== undefined ?
                                            <div className="alert alert-danger" >{errMsg}</div> : ""
                                    }
                                    {
                                        successMsg !== undefined ?
                                            <div className="alert alert-primary" >{successMsg}</div> : ""
                                    }
                                    <div className="row g-3">

                                        <div className="col-md-6 mb-3">
                                            <label className="form-label text-muted small fw-bold">Coupon Code</label>
                                            <input type="text" className="form-control" onChange={(e) => setCouponCode(e.target.value)} required />
                                        </div>

                                        <div className="col-md-6 mb-3">
                                            <label className="form-label text-muted small fw-bold">Discount Value</label>
                                            <input type="number" step="0.01" className="form-control" onChange={(e) => setDiscountValue(e.target.value)} required />
                                        </div>

                                        <div className="col-md-6 mb-3">
                                            <label className="form-label text-muted small fw-bold">Expiry Date</label>
                                            <input type="date" className="form-control" onChange={(e) => setExpiryDate(e.target.value)} required />
                                        </div>

                                        <div className="col-md-6 mb-3">
                                            <label className="form-label text-muted small fw-bold">Max Usage</label>
                                            <input type="number" className="form-control" onChange={(e) => setMaxUsage(e.target.value)} required />
                                        </div>

                                    </div>

                                    <div className="modal-footer">
                                        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <button type="submit" className="btn btn-primary">Save changes</button>
                                    </div>
                                </form>
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
                            <th scope="col">Coupon Code</th>
                            <th scope="col">Discount Value</th>
                            <th scope="col">Expiry Date</th>
                            <th scope="col">Active</th>
                            <th scope="col">Max Usage</th>
                            <th scope="col">Used Count</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            coupons.map((c, index) => (
                                <tr key={index}>
                                    <th scope="row">{c.id}</th>
                                    <td>{c.couponCode}</td>
                                    <td>{c.discountValue}</td>
                                    <td>{c.expiryDate}</td>
                                    <td>
                                        <span className={"badge " + (c.isActive ? "bg-success" : "bg-secondary")}>
                                            {c.isActive ? "Active" : "Inactive"}
                                        </span>
                                    </td>
                                    <td>{c.maxUsage}</td>
                                    <td>{c.usedCount}</td>
                                    <td>
                                        <button className="btn btn-link p-0 text-decoration-none" onClick={(e) => deleteCoupon(c.id)} ><i className="bi bi-trash"></i></button>
                                    </td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>

        </div>
    )

}
export default Coupons