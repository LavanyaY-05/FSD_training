import { Outlet } from "react-router-dom";
import NavbarCustomer from "../components/NavbarCustomer";

const Car = () =>{
 return(
      <div> 
          <NavbarCustomer />
            <div className="d-flex flex-grow-1 gap-3 align-items-stretch">
                 <main className="flex-grow-1">
        <Outlet />
      </main>
            </div>
      </div>
    )
}
export default Car;