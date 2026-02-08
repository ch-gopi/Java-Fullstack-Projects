import React, { useState } from "react";
import {
  loginAPICall,
  saveLoggedInUser,
  storeToken,
} from "../services/AuthService";
import { useNavigate } from "react-router-dom";
import backgroundImage from "../assets/lom.png";

const LoginComponent = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const navigator = useNavigate();

  async function handleLoginForm(e) {
    e.preventDefault();
    await loginAPICall(username, password)
      .then((response) => {
        console.log(response.data);
        const token = "Bearer " + response.data.accessToken;
        const role = response.data.role;
        storeToken(token);
        saveLoggedInUser(username, role);
        setMessage(" Login Successfull! Redirecting...");
        setTimeout(() => navigator("/home"), 2000);
      })
      .catch((error) => {
        console.error(error);
        setMessage(" Incorrect login credentials. Please try again.");
      });
  }

  return (
    <div
      style={{
        backgroundImage: `url(${backgroundImage})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        height: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <div
        style={{
          backdropFilter: "blur(10px)",
          backgroundColor: "rgba(255, 255, 255, 0.2)",
          borderRadius: "15px",
          padding: "30px",
          marginTop: "-240px",
          marginLeft: "-125px",
          boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.3)",
          width: "345px",
          height: "337px",
          textAlign: "center",
        }}
      >
        <h2 style={{ color: "white" }}>Login</h2>
        {message && (
          <p style={{ color: "white", fontWeight: "bold" }}>{message}</p>
        )}
        <form>
          <div className="mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Enter Username or Email"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              style={{
                borderRadius: "25px",
                padding: "10px",
                fontSize: "16px",
              }}
            />
          </div>
          <div className="mb-3">
            <input
              type="password"
              className="form-control"
              placeholder="Enter Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              style={{
                borderRadius: "25px",
                padding: "10px",
                fontSize: "16px",
              }}
            />
          </div>
          <button
            style={{
              borderRadius: "25px",
              padding: "12px",
              width: "100%",
              fontSize: "18px",
              fontWeight: "bold",
              backgroundColor: "#007bff",
              color: "white",
              border: "none",
              cursor: "pointer",
              transition: "background 0.3s ease",
            }}
            onMouseOver={(e) => (e.target.style.backgroundColor = "#0056b3")}
            onMouseOut={(e) => (e.target.style.backgroundColor = "#007bff")}
            onClick={(e) => handleLoginForm(e)}
          >
            Submit
          </button>
        </form>
       
        <div style={{ marginTop: "15px" }}>
          <a
            href="/forgot-password"
            style={{
              color: "white",
              fontSize: "16px",
              textDecoration: "none",
              fontWeight: "bold",
              transition: "color 0.3s ease",
            }}
            onMouseOver={(e) => (e.target.style.color = "#0F4C81")}
            onMouseOut={(e) => (e.target.style.color = "white")}
          >
            Forgot Password?
          </a>
        </div>
      </div>
    </div>
  );
};

export default LoginComponent;
