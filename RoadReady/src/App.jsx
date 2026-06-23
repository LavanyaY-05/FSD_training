import { Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Auth from "./pages/Auth";
import PageNotFound from "./pages/PageNotFound";
import AdminDashboard from "./pages/AdminDashboard";
import AgentDashboard from "./pages/AgentDashboard";
import CustomerRegister from "./components/Customer_Components/CustomerRegister";
import Widget from "./components/Admin_Components/widget";
import Cars from "./components/Admin_Components/ManageCar";
import CustomerDashboard from "./pages/Customer_DashBoard";
import CarDetails from "./components/Car_Components/CarDetails";
import Booking from "./components/Customer_Components/Booking";
import AllBookings from "./components/Customer_Components/AllBookings";
import CustomerProfile from "./pages/CustomerProfile";
import Profile from "./components/Customer_Components/Profile";
import AgentOnboard from "./components/Admin_Components/AgentOnboard";
import Coupons from "./components/Admin_Components/ManageCoupon";
import CarSearch from "./components/Car_Components/CarSearch";

import Car from "./pages/Cars";
import LandingSearch from "./components/landingsearch";
import AdminBookings from "./components/Admin_Components/ManageBooking";
import Inspection from "./components/AgentComponent/Inspection";
import MyReviews from "./components/Customer_Components/MyReviews";
import AdminProfile from "./components/Admin_Components/AdminProfile";
import AgentProfile from "./components/AgentComponent/AgentProfile";
import ConfirmBooking from "./components/Customer_Components/ConfirmBooking";


const App = () =>{

    
 
    return(
      <div>
        <Routes>
          <Route path="/" element={<Home/>}></Route>
          <Route path="/login" element={<Auth/>}></Route>
          <Route path="/register" element={<CustomerRegister/>}></Route>


          <Route path="/admin" element={<AdminDashboard/>}>
              <Route path="" element={<Widget/>}></Route>
              <Route path="manageCars" element={<Cars/>}></Route>
              <Route path="manageCoupon" element= {<Coupons/>}></Route>
              <Route path="manageBooking" element = {<AdminBookings/>}></Route>
              <Route path="profile" element = {<AdminProfile/>}></Route>

              <Route path="manageAgent" element = {<AgentOnboard/>}></Route>
          </Route>


          <Route path="/customer" element={<CustomerDashboard/>}></Route>
          <Route path="/profile" element={<CustomerProfile/>}>
                    <Route path="myBookings" element={<AllBookings/>}></Route>
                    <Route path="" element={<Profile/>}></Route>
                    <Route path="myReviews" element= {<MyReviews/>}></Route>

          </Route>

          {/* <Route path="/home" element= {<LandingSearch/>}></Route> */}

          <Route path="/cars" element= {<Car/>}>
              <Route path = "" element = {<CarSearch/>}></Route>
              <Route path="cardetails/:car_id" element= {<CarDetails/>}></Route>
              <Route path="booking/:id" element= {<Booking/>}></Route>
              <Route path="confirmBooking/:id" element= {<ConfirmBooking/>}></Route>

          </Route>
          <Route path="/agent" element={<AgentDashboard/>}>

                    <Route path="" element={<Inspection/>}></Route>
                    <Route path="profile" element={<AgentProfile/>}></Route>

          </Route>


          <Route path="*" element={<PageNotFound/>}></Route>

        </Routes>
      </div>
    )

}
export default App;