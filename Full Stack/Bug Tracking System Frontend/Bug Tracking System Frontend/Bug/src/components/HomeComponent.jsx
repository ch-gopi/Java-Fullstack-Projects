<<<<<<< HEAD
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getBugAnalytics } from '../services/Bugservice'; 
import loggedInBg from "../assets/wm.png";
import defaultBg from "../assets/gbm.png";
import { Chart } from 'react-chartjs-2'; 
import ChartDataLabels from 'chartjs-plugin-datalabels';
import { Chart as ChartJS, ArcElement, Tooltip, Legend} from 'chart.js';
ChartJS.register(ArcElement, Tooltip, Legend,ChartDataLabels);
import ContactComponent from '../components/ContactComponent'
import FeaturesComponent from '../components/FeaturesComponent'
import AboutComponent from '../components/AboutComponent'
import {isUserLoggedIn } from "../services/AuthService";

const HomeComponent = () => {
    const [analytics, setAnalytics] = useState({});
    const [bgImageLoaded, setBgImageLoaded] = useState(false);
  
const [isLoggedIn, setIsLoggedIn] = useState(isUserLoggedIn()); 

const welcomeTextColor = isLoggedIn ? "white" : "black"; 
const resolvedBugsColor = isLoggedIn ? "white" : "black"; 


const [backgroundImage, setBackgroundImage] = useState(
    isLoggedIn ? defaultBg : loggedInBg
);

 useEffect(() => {
    setIsLoggedIn(isUserLoggedIn()); 
    setBackgroundImage(isLoggedIn ? defaultBg: loggedInBg);
}, []);
 
useEffect(() => {
    const fetchAnalytics = async (retries = 3) => { 
        try {
            const response = await getBugAnalytics(); 
            setAnalytics(response.data); 
        } catch (error) { 
            if (retries > 0) { 
                console.warn("Retrying fetch...");
                setTimeout(() => fetchAnalytics(retries - 1), 1000); 
            } else { 
                console.error("Failed to fetch analytics:", error);
            } 
        } 
    }; 

    fetchAnalytics(); 

  
    const img = new Image();
    img.src = backgroundImage;
    img.onload = () => setBgImageLoaded(true); // Set state when image is loaded
}, []);
    const homeStyle = {
        backgroundImage: `url(${backgroundImage})`,
       backgroundSize: 'cover',
  
backgroundRepeat: 'no-repeat',
backgroundPosition: 'center',
        minHeight: '100vh',
        color: 'white',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'flex-end',
        textAlign: 'center',
        paddingRight: '30px'
    };

    const buttonContainerStyle = {
        display: 'flex',
        gap: '80px',
        marginTop: '20px',
        justifyContent: 'flex-start',
        paddingRight: '58px',
        marginLeft  : '195px'
    };

    const buttonStyle = {
        padding: '12px 20px',
        fontSize: '18px',
        fontWeight: 'bold',
        backgroundColor: '#007bff',
        color: 'white',
        border: 'none',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: '0.3s ease',
        textDecoration: 'none'
    };

    return (
        <div>
        <div style={homeStyle}>
        <div style={{color: welcomeTextColor, marginTop: '38px',marginRight: '680px'}}>
            <h1 >🚀 Welcome to Bug Tracking Application</h1>
            <p style={{ maxWidth: '600px', fontSize: '18px',marginLeft: '140px' }}>
                Track, manage, and resolve bugs efficiently with our intuitive platform. 
                Empower your development team with seamless issue tracking and project transparency.
            </p>
              
            <div style={buttonContainerStyle}>
                <Link to="/features" style={buttonStyle}>Features</Link>
                <Link to="/about" style={buttonStyle}>About</Link>
                <Link to="/contact" style={buttonStyle}>Contact</Link>
            </div>
            </div>
           <div style={{ color: resolvedBugsColor,display: 'flex', alignItems: 'center', gap: '50px', marginTop: '-215px',marginRight: '140px'}}>
            <div className="row" >
                <div className="col-md-6">
                    <h4>Total Bugs: {analytics.totalBugs || 0}</h4>
                    <h4>Resolved Bugs: {analytics.resolvedBugs || 0}</h4>
                    <h4>Unresolved Bugs: {analytics.unresolvedBugs || 0}</h4>
                </div>
                <div className="col-md-6">
                    <Chart
                        type="pie"
                        data={{
                            
                            labels: ['Low', 'Medium', 'High', 'Critical'],
                            datasets: [{
                                data: [analytics.lowSeverity || 0, analytics.mediumSeverity || 0, analytics.highSeverity || 0, analytics.criticalSeverity || 0],
                                backgroundColor: [' #0492C2', '#ED7014', '#0F4C81', 'red']
                            }]
                        }}
                        options={{
                            plugins: {
                                datalabels: {
                                    color: '#FFD700',
                                    font: {
                                        size: 14,
                                        weight: 'bold'
                                    },
                                    formatter: (value, context) => {
                                        const total = context.chart.data.datasets[0].data.reduce((sum, num) => sum + num, 0);
                                        const percentage = ((value / total) * 100).toFixed(1) + '%';
                                        return percentage;
                                    }
                                }
                            }
                        }}
                        
                    
                    />
                </div>
            </div>
            </div>

</div>

<div style={{ marginTop: '50px', width: '100vw' }}>  
  <div>
    <div>
      <div style={{
          marginTop: '50px',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          justifyContent: 'center',
          padding: '40px',
          borderRadius: '15px',
          width: '100vw',  
          maxWidth: 'none', 
          background: 'linear-gradient(to right, #007bff, #6610f2)', 
          color: 'white',
          boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
          textAlign: 'center'
      }}>
        <h2 style={{ fontSize: '28px', fontWeight: 'bold' }}>✨ Why Choose Us?</h2>
        <p style={{ fontSize: '18px', lineHeight: '1.5', maxWidth: '600px', margin: '0 auto' }}>
            Our Bug Tracking Application is designed to **streamline** your development process. 
            With features like **real-time tracking**, **automated notifications**, and **comprehensive analytics**, 
            you can focus on what matters most—building great software 🚀.
        </p>
      </div>
    </div>  
    <FeaturesComponent />
    <AboutComponent /> 
    <ContactComponent />
  </div>
</div>

        </div>
    );
};

export default HomeComponent;
=======
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getBugAnalytics } from '../services/Bugservice'; 
import loggedInBg from "../assets/wm.png";
import defaultBg from "../assets/gbm.png";
import { Chart } from 'react-chartjs-2'; 
import ChartDataLabels from 'chartjs-plugin-datalabels';
import { Chart as ChartJS, ArcElement, Tooltip, Legend} from 'chart.js';
ChartJS.register(ArcElement, Tooltip, Legend,ChartDataLabels);
import ContactComponent from '../components/ContactComponent'
import FeaturesComponent from '../components/FeaturesComponent'
import AboutComponent from '../components/AboutComponent'
import {isUserLoggedIn } from "../services/AuthService";

const HomeComponent = () => {
    const [analytics, setAnalytics] = useState({});
    const [bgImageLoaded, setBgImageLoaded] = useState(false);
  
const [isLoggedIn, setIsLoggedIn] = useState(isUserLoggedIn()); 

const welcomeTextColor = isLoggedIn ? "white" : "black"; 
const resolvedBugsColor = isLoggedIn ? "white" : "black"; 


const [backgroundImage, setBackgroundImage] = useState(
    isLoggedIn ? defaultBg : loggedInBg
);

 useEffect(() => {
    setIsLoggedIn(isUserLoggedIn()); 
    setBackgroundImage(isLoggedIn ? defaultBg: loggedInBg);
}, []);
 
useEffect(() => {
    const fetchAnalytics = async (retries = 3) => { 
        try {
            const response = await getBugAnalytics(); 
            setAnalytics(response.data); 
        } catch (error) { 
            if (retries > 0) { 
                console.warn("Retrying fetch...");
                setTimeout(() => fetchAnalytics(retries - 1), 1000); 
            } else { 
                console.error("Failed to fetch analytics:", error);
            } 
        } 
    }; 

    fetchAnalytics(); 

  
    const img = new Image();
    img.src = backgroundImage;
    img.onload = () => setBgImageLoaded(true); // Set state when image is loaded
}, []);
    const homeStyle = {
        backgroundImage: `url(${backgroundImage})`,
       backgroundSize: 'cover',
  
backgroundRepeat: 'no-repeat',
backgroundPosition: 'center',
        minHeight: '100vh',
        color: 'white',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'flex-end',
        textAlign: 'center',
        paddingRight: '30px'
    };

    const buttonContainerStyle = {
        display: 'flex',
        gap: '80px',
        marginTop: '20px',
        justifyContent: 'flex-start',
        paddingRight: '58px',
        marginLeft  : '195px'
    };

    const buttonStyle = {
        padding: '12px 20px',
        fontSize: '18px',
        fontWeight: 'bold',
        backgroundColor: '#007bff',
        color: 'white',
        border: 'none',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: '0.3s ease',
        textDecoration: 'none'
    };

    return (
        <div>
        <div style={homeStyle}>
        <div style={{color: welcomeTextColor, marginTop: '38px',marginRight: '680px'}}>
            <h1 >🚀 Welcome to Bug Tracking Application</h1>
            <p style={{ maxWidth: '600px', fontSize: '18px',marginLeft: '140px' }}>
                Track, manage, and resolve bugs efficiently with our intuitive platform. 
                Empower your development team with seamless issue tracking and project transparency.
            </p>
              
            <div style={buttonContainerStyle}>
                <Link to="/features" style={buttonStyle}>Features</Link>
                <Link to="/about" style={buttonStyle}>About</Link>
                <Link to="/contact" style={buttonStyle}>Contact</Link>
            </div>
            </div>
           <div style={{ color: resolvedBugsColor,display: 'flex', alignItems: 'center', gap: '50px', marginTop: '-215px',marginRight: '140px'}}>
            <div className="row" >
                <div className="col-md-6">
                    <h4>Total Bugs: {analytics.totalBugs || 0}</h4>
                    <h4>Resolved Bugs: {analytics.resolvedBugs || 0}</h4>
                    <h4>Unresolved Bugs: {analytics.unresolvedBugs || 0}</h4>
                </div>
                <div className="col-md-6">
                    <Chart
                        type="pie"
                        data={{
                            
                            labels: ['Low', 'Medium', 'High', 'Critical'],
                            datasets: [{
                                data: [analytics.lowSeverity || 0, analytics.mediumSeverity || 0, analytics.highSeverity || 0, analytics.criticalSeverity || 0],
                                backgroundColor: [' #0492C2', '#ED7014', '#0F4C81', 'red']
                            }]
                        }}
                        options={{
                            plugins: {
                                datalabels: {
                                    color: '#FFD700',
                                    font: {
                                        size: 14,
                                        weight: 'bold'
                                    },
                                    formatter: (value, context) => {
                                        const total = context.chart.data.datasets[0].data.reduce((sum, num) => sum + num, 0);
                                        const percentage = ((value / total) * 100).toFixed(1) + '%';
                                        return percentage;
                                    }
                                }
                            }
                        }}
                        
                    
                    />
                </div>
            </div>
            </div>

</div>

<div style={{ marginTop: '50px', width: '100vw' }}>  
  <div>
    <div>
      <div style={{
          marginTop: '50px',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          justifyContent: 'center',
          padding: '40px',
          borderRadius: '15px',
          width: '100vw',  
          maxWidth: 'none', 
          background: 'linear-gradient(to right, #007bff, #6610f2)', 
          color: 'white',
          boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
          textAlign: 'center'
      }}>
        <h2 style={{ fontSize: '28px', fontWeight: 'bold' }}>✨ Why Choose Us?</h2>
        <p style={{ fontSize: '18px', lineHeight: '1.5', maxWidth: '600px', margin: '0 auto' }}>
            Our Bug Tracking Application is designed to **streamline** your development process. 
            With features like **real-time tracking**, **automated notifications**, and **comprehensive analytics**, 
            you can focus on what matters most—building great software 🚀.
        </p>
      </div>
    </div>  
    <FeaturesComponent />
    <AboutComponent /> 
    <ContactComponent />
  </div>
</div>

        </div>
    );
};

export default HomeComponent;
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
