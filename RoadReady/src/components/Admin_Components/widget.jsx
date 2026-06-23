import axios from "axios";
import AgentStats from "./AgentStats";
import CarStats from "./CarStats";
import { useEffect, useState } from "react";

const Widget = () => {

    const api = "http://localhost:8080/api/admin/combinedStats"
    const [label, setLabel] = useState([])
    const [count, setCount] = useState([])

    const config = {
        headers: {
            'Authorization': "Bearer " + localStorage.getItem('token')
        }
    }
    useEffect(() => {
        const getStats = async () => {
            try {
                const response = await axios.get(api, config)
                console.log(response)
                setCount(response?.data?.data)
                setLabel(response?.data?.label)
            }
            catch (err) {
                console.log(err)
            }
        }
        getStats()
    }, [])
    return (
        <main className="dashboard-main">
            <div className="content-header">
                <h2 className="page-title">Dashboard</h2>
            </div>
            <div className="row g-3">
                <div className="col-12 col-md-4">
                    <div className="stat-card p-3 border rounded h-100 d-flex align-items-center">
                        <div className="card-icon purple-bg me-3">
                            <i className="bi bi-people fs-4"></i>
                        </div>
                        <div className="card-details">
                            <p className="stat-number fs-3 fw-bold text-dark mb-0 lh-1">{count.length > 0 ? count[0] : 0}</p>
                            <p className="stat-label mb-0 text-muted small">{label.length > 0 ? label[0] : 0}</p>
                        </div>
                    </div>
                </div>

                <div className="col-12 col-md-4">
                    <div className="stat-card p-3 border rounded h-100 d-flex align-items-center">
                        <div className="card-icon blue-bg me-3">
                            <i className="bi bi-calendar-check fs-4"></i>
                        </div>
                        <div className="card-details">
                            <p className="stat-number fs-3 fw-bold text-dark mb-0 lh-1">{count.length > 1 ? count[1] : 0}</p>
                            <p className="stat-label mb-0 text-muted small">{label.length > 1 ? label[1] : 0}</p>
                        </div>
                    </div>
                </div>

                <div className="col-12 col-md-4">
                    <div className="stat-card p-3 border rounded h-100 d-flex align-items-center">
                        <div className="card-icon green-bg me-3">
                            <i className="bi bi-currency-rupee fs-4"></i>
                        </div>
                        <div className="card-details">
                            <p className="stat-number fs-3 fw-bold text-dark mb-0 lh-1">{count.length > 2 ? count[2] : 0}</p>
                            <p className="stat-label mb-0 text-muted small">{label.length > 2 ? label[2] : 0}</p>
                        </div>
                    </div>
                </div>
            </div>

            <div className="container-fluid mt-3">
                <div className="container-fluid mt-3">
                    <div className="row g-4">
                        {/* Takes exactly half the screen width */}
                        <div className="col-md-6 col-lg-6">
                            <div className="card shadow-sm p-4 bg-white rounded h-100" style={{ minHeight: "360px" }}>
                                <h5 className="fw-bold text-secondary mb-3">Fleet Density by City</h5>

                                <div className="mx-auto" style={{ maxWidth: "300px", height: "280px", position: "relative" }}>
                                    <CarStats />
                                </div>
                            </div>
                        </div>

                        <div className="col-md-6 col-lg-6">
                            <div className="card shadow-sm p-4 bg-white rounded h-100" style={{ minHeight: "360px" }}>
                                <h5 className="fw-bold text-secondary mb-3">Car Distribution by Agent</h5>
                                <div className="mx-auto" style={{ maxWidth: "300px", height: "280px", position: "relative" }}>
                                    <AgentStats />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    );
};

export default Widget;
