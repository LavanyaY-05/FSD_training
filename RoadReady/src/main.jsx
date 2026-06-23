import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
// import './index.css'
import App from './App.jsx'

import { Chart } from 'primereact/chart';

import { BrowserRouter } from 'react-router-dom'
import 'bootstrap-icons/font/bootstrap-icons.css'
import 'bootstrap/dist/css/bootstrap.min.css';

import { Provider } from 'react-redux'; // <-- 1. Import Provider
import { store } from './store.js';  

import './assets/css/Admin_Style_code.css'
import './assets/css/car.css'
createRoot(document.getElementById('root')).render(
      <Provider store={store}>

  <BrowserRouter>
    <App />
  </BrowserRouter>
  </Provider>
)
