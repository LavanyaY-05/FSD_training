import { Route, Routes } from "react-router-dom"
import UserList from "./components/UserList"
import AddUser from "./components/AddUser"
import Characters from "./components/Characters"

const App = () =>{

  return(
    <div>
      <Routes>
         <Route path= "/users" element={<UserList/>}></Route>
                  <Route path= "/add-user" element={<AddUser/>}></Route>
                  <Route path= "/character" element={<Characters/>}></Route>

      </Routes>
    </div>
  )
}
export default App