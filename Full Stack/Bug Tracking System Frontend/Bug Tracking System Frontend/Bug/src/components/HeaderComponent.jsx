import React from 'react'
import { NavLink } from 'react-router-dom'
import { isUserLoggedIn, logout } from '../services/AuthService'
import { useNavigate } from 'react-router-dom'

const HeaderComponent = () => {

    const isAuth = isUserLoggedIn();

    const navigator = useNavigate();

    function handleLogout(){
        logout();
        navigator('/login');
    }

  return (
    <div>
        <header>
            <nav className='navbar navbar-expand-md navbar-dark bg-dark'>
                <div>
                <NavLink to="/home" className="nav-link">
             <h5 style={{color: 'red', marginRight: '25px' , marginTop: '7px' }} className="text-center text-light" > Bug Tracking Application</h5>  
          
                </NavLink>
            
                </div>
                
                
                <div className='collapse navbar-collapse'>
                    <ul className='navbar-nav'>

                        {
                            isAuth &&                         
                            <li className='nav-item'>
                            <NavLink to="/bugs" className="nav-link">Bugs</NavLink>
                          
                        </li>
                        }

                    </ul>
                </div>
                <ul className='navbar-nav'>
                    {
                        !isAuth &&                         
                        <li className='nav-item'>
                        <NavLink to="/register" className="nav-link">Register</NavLink>
                    </li>
                    }

                    {
                        !isAuth &&    
                        <li className='nav-item'>
                        <NavLink to="/login" className="nav-link">Login</NavLink>
                    </li>
                    }

                    {
                        isAuth &&    
                        <li className='nav-item'>
                        <NavLink to="/login" className="nav-link" onClick={handleLogout}>Logout</NavLink>
                    </li>
                    }

                    </ul>
            </nav>
        </header>

    </div>
  )
}

export default HeaderComponent