import { Link } from "react-router-dom";

const Navbar = () => {
  return (
   <nav  className="top-navbar">
        <div  className="nav-container">
            <div  className="logo-area">
                <h1  className="logo-text">RoadReady</h1>
            </div>
            <div  className="nav-menu">
                 <Link   className="nav-link">Home </Link>
                 <Link   className="nav-link">Profile </Link>
                <Link to= "/login">
            <button className="btn btn-outline-success" type="submit">Login</button>
            </Link>            </div>
        </div>
    </nav>

  )
};

export default Navbar;







  // <nav className="navbar navbar-expand-lg bg-body-tertiary">
  //     <div className="container-fluid">
  //       <a className="navbar-brand">RoadReady</a>

  //           <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">

  //         <span className="navbar-toggler-icon"></span>
  //       </button>

  //       <div className="collapse navbar-collapse" id="navbarSupportedContent">
  //         <ul className="navbar-nav me-auto mb-2 mb-lg-0">
  //           <li className="nav-item">
  //             <a className="nav-link active">Home</a>
  //           </li>
  //           <li className="nav-item">
  //             <a className="nav-link active">Browse Cars</a>
  //           </li>
  //         </ul>

  //         <form className="d-flex" role="search">
  //           <Link to= "/login">
  //           <button className="btn btn-outline-success" type="submit">Login</button>
  //           </Link>
  //         </form>
  //       </div>
  //     </div>
  //   </nav>