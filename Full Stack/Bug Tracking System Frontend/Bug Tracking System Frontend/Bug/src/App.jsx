import './App.css'
import { useState } from 'react'
import Listbugcomponent from './components/Listbugcomponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import { BrowserRouter, Routes, Route} from 'react-router-dom'
import Bugcomponent from './components/Bugcomponent'
import RegisterComponent from './components/RegisterComponent'
import LoginComponent from './components/LoginComponent'
import { isUserLoggedIn } from './services/AuthService'

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
              {/* http://localhost:8082 */}
              <Route path='/' element = { <LoginComponent /> }></Route>
               {/* http://localhost:8082/bugs */}
              <Route path='/bugs' element = { <AuthenticatedRoute><Listbugcomponent /> </AuthenticatedRoute>}></Route>
              {/* http://localhost:8082/add-bug */}
              <Route path='/add-bug' element = {<AuthenticatedRoute><Bugcomponent /> </AuthenticatedRoute> }></Route>
              {/* http://localhost:8082/update-bug/1 */}
              <Route path='/update-bug/:id' element = { <AuthenticatedRoute><Bugcomponent /></AuthenticatedRoute> }></Route>
              {/* http://localhost:8080/register */}
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
