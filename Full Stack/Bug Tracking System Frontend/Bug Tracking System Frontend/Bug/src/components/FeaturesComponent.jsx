import React from 'react';

const FeaturesComponent = () => {
    return (
        <div style={{ padding: '40px', backgroundColor: '#ffffff', textAlign: 'center' }}>
            <h2 style={{ color: '#28a745' }}>Key Features</h2>
            <div style={{ display: 'flex', justifyContent: 'center', flexWrap: 'wrap' }}>
                <div style={{ width: '300px', padding: '20px', margin: '15px', backgroundColor: '#f1f1f1', boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.2)', borderRadius: '10px' }}>
                    <h3 style={{ color: '#dc3545' }}>🔹 User-Friendly Interface</h3>
                    <p>Simplified navigation for a seamless user experience.</p>
                </div>
                <div style={{ width: '300px', padding: '20px', margin: '15px', backgroundColor: '#f1f1f1', boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.2)', borderRadius: '10px' }}>
                    <h3 style={{ color: '#fd7e14' }}>🔹 High Performance</h3>
                    <p>Optimized speed and smooth functionality for better efficiency.</p>
                </div>
                <div style={{ width: '300px', padding: '20px', margin: '15px', backgroundColor: '#f1f1f1', boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.2)', borderRadius: '10px' }}>
                    <h3 style={{ color: '#007bff' }}>🔹 Secure & Reliable</h3>
                    <p>Robust security measures to ensure data protection and safety.</p>
                </div>
            </div>
        </div>
    );
};

export default FeaturesComponent
