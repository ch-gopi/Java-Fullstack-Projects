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
import ForgotpasswordComponent from './components/ForgotpasswordComponent'
import AboutComponent from './components/AboutComponent'







function App() {
  function AuthenticatedRoute({children}){

    const isAuth = isUserLoggedIn();

    if(isAuth) {
      return children;
    }

    return <Navigate to="/" />

  }
  return (
    <>
    <BrowserRouter> 
        <HeaderComponent />
          <Routes>
             <Route path="/home" element={<HomeComponent />} />
             <Route path="/features" element={<FeaturesComponent />} />
             <Route path="/contact" element={<ContactComponent />} />
             <Route path="/about" element={<AboutComponent />} />
             <Route path="/forgot-password" element={<ForgotpasswordComponent />} />

        
         
              {/* http://localhost:3000 */}
              <Route path='/' element = { <HomeComponent /> }></Route>
               {/* http://localhost:8082/bugs */}
              <Route path='/bugs' element = { <AuthenticatedRoute><Listbugcomponent /> </AuthenticatedRoute>}></Route>
              {/* http://localhost:8082/add-bug */}
              <Route path='/add-bug' element = {<AuthenticatedRoute><Bugcomponent /> </AuthenticatedRoute> }></Route>
              {/* http://localhost:8082/update-bug/1 */}
              <Route path='/update-bug/:id' element = { <AuthenticatedRoute><Bugcomponent /></AuthenticatedRoute> }></Route>
              {/* http://localhost:8082/register */}
              <Route path='/register' element = { <RegisterComponent />}></Route>
               {/* http://localhost:8082/login */}
               <Route path='/login' element = { <LoginComponent /> }></Route>
               
              


               
          </Routes>
        <FooterComponent />
        </BrowserRouter>
    </>
  )
}

export default App
