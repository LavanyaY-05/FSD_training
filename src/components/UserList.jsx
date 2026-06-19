import axios from "axios"
import { useEffect, useState } from "react"
import { Link } from "react-router-dom"

const UserList = () => {

    const Api = "https://jsonplaceholder.typicode.com/users"

    const [users, setUsers] = useState([])

    useEffect(() => {
        const getUsers = async () => {
            try {
                const response = await axios.get(Api)
                setUsers(response.data)
            }
            catch (err) {
                console.log(err)
            }
        }
        getUsers()
    }, [])


    const deleteUser = async (id) => {
        try {
            const response = await axios.delete(Api + "/" + id)
            console.log(response)
            let filteredUsers = [...users].filter(u => u.id !== Number(id))
            setUsers(filteredUsers)

        }
        catch (err) {
            console.log(err)

        }

    }

    return (
        <div className="bg-light" style={{ minHeight: "100vh" }}>

            <nav className="navbar navbar-dark bg-white shadow-sm">
                <div className="container d-flex  py-2">
                    <span className="navbar-brand fw-bold text-black mb-0">User Management System</span>

                </div>
            </nav>
            <div className="bg-light d-flex align-items-center justify-content-center" style={{ minHeight: "100vh" }}>
                <div className="card shadow-sm border-0 rounded-4 mt-4" style={{ width: "100%", maxWidth: "1200px" }}>

                    <div className="container mt-4 mb-4">
                        <h1 className="text-center">User List</h1>
                        <Link to="/add-user" className="btn btn-primary float-end mb-4">
                            Add User
                        </Link>
                        <div className="container card mt-4">
                            <table className="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">Name</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Phone</th>
                                        <th scope="col">Company Name</th>
                                        <th scope="col">Actions</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        users.map((u, index) => (
                                            <tr key={index}>
                                                <th scope="row">{u.name}</th>
                                                <td>{u.email}</td>
                                                <td>{u.phone}</td>
                                                <td>{u.company.name}</td>
                                                <td>{u.company.name}</td>
                                                <td><button className="btn btn-link p-0 text-decoration-none" onClick={(e) => deleteUser(u.id)}><i className="bi bi-trash"></i></button></td>
                                            </tr>
                                        ))
                                    }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )
}
export default UserList