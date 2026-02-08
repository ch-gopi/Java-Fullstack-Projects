import './App.css'
import { useState } from 'react'
import Listbugcomponent from './components/Listbugcomponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import { BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
import Bugcomponent from './components/Bugcomponent'
import RegisterComponent from './components/RegisterComponent'
import LoginComponent from './components/LoginComponent'
import { isUserLoggedIn } from './services/AuthService'
import HomeComponent from './components/HomeComponent'
import ContactComponent from './components/ContactComponent'
import FeaturesComponent from './components/FeaturesComponent'
import ForgotpasswordComponent from './components/ForgotPasswordComponent'
import AboutComponent from './components/AboutComponent'
import { NotificationProvider } from './components/NotificationContext' // Import Notification Provider

function App() {

  function AuthenticatedRoute({children}){
    const isAuth = isUserLoggedIn();
    return isAuth ? children : <Navigate to="/" />;
  }

  return (
    <>
    <NotificationProvider> {/* Wrap app inside NotificationProvider */}
      <BrowserRouter> 
        <HeaderComponent /> {/* This can now access notifications */}
          <Routes>
             <Route path="/home" element={<HomeComponent />} />
             <Route path="/features" element={<FeaturesComponent />} />
             <Route path="/contact" element={<ContactComponent />} />
             <Route path="/about" element={<AboutComponent />} />
             <Route path="/forgot-password" element={<ForgotpasswordComponent />} />

              <Route path='/' element={<HomeComponent />} />
              <Route path='/bugs' element={<AuthenticatedRoute><Listbugcomponent /></AuthenticatedRoute>} />
              <Route path='/add-bug' element={<AuthenticatedRoute><Bugcomponent /></AuthenticatedRoute>} />
              <Route path='/update-bug/:id' element={<AuthenticatedRoute><Bugcomponent /></AuthenticatedRoute>} />
              <Route path='/register' element={<RegisterComponent />} />
              <Route path='/login' element={<LoginComponent />} />
          </Routes>
        <FooterComponent />
      </BrowserRouter>
    </NotificationProvider>
    </>
  );
}

export default App;
