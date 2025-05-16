import React, { useContext } from "react";
import { NavLink } from "react-router-dom";
import { isUserLoggedIn, logout } from "../services/AuthService";
import { useNavigate } from "react-router-dom";
import { NotificationContext } from "./NotificationContext"; // Import Notification Context

const HeaderComponent = () => {
  const isAuth = isUserLoggedIn();
  const navigator = useNavigate();
  const { notifications } = useContext(NotificationContext); // Access notifications from context
  const lastNotifications = notifications.slice(-4);

  function handleLogout() {
    logout();
    navigator("/login");
  }

  return (
    <div>
      <header>
        <nav className="navbar navbar-expand-md navbar-dark bg-dark">
          <div>
            <NavLink to="/home" className="nav-link">
              <h5
                style={{ color: "red", marginRight: "25px", marginTop: "7px" }}
                className="text-center text-light"
              >
                Bug Tracking Application
              </h5>
            </NavLink>
          </div>

          <div className="collapse navbar-collapse">
            <ul className="navbar-nav">
              {isAuth && (
                <li className="nav-item">
                  <NavLink to="/bugs" className="nav-link">
                    Bugs
                  </NavLink>
                </li>
              )}
            </ul>
          </div>

          {/* Notification Bell - Always Visible */}
          {isAuth && (
            <div
              className="ml-auto"
              style={{ marginRight: "70px", position: "relative" }}
            >
              <button className="btn btn-dark">
                🔔 {notifications.length > 0 ? `(${notifications.length})` : ""}
              </button>

              {/* Dropdown Only When Notifications Exist */}
              {notifications.length > 0 && (
                <ul className="dropdown-menu show">
                  <div className="notification-container">
                    {notifications.slice(-4).map((notif, index) => (
                      <p key={index} className="notification-text">
                        {notif}
                      </p>
                    ))}
                  </div>
                </ul>
              )}
            </div>
          )}

          <ul className="navbar-nav">
            {!isAuth && (
              <li className="nav-item">
                <NavLink to="/register" className="nav-link">
                  Register
                </NavLink>
              </li>
            )}

            {!isAuth && (
              <li className="nav-item">
                <NavLink to="/login" className="nav-link">
                  Login
                </NavLink>
              </li>
            )}

            {isAuth && (
              <li className="nav-item">
                <NavLink
                  to="/login"
                  className="nav-link"
                  onClick={handleLogout}
                >
                  Logout
                </NavLink>
              </li>
            )}
          </ul>
        </nav>
      </header>
    </div>
  );
};

export default HeaderComponent;
