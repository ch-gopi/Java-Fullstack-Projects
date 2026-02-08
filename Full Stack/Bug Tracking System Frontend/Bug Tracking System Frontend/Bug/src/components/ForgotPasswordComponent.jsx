<<<<<<< HEAD
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const ForgotPasswordComponent = () => {
    const [email, setEmail] = useState('');
    const [otp, setOtp] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [step, setStep] = useState(1);
    const [message, setMessage] = useState('');
    const navigator = useNavigate();

    function handleSendEmail(e) {
        e.preventDefault();
        setMessage(' Mock: Verification OTP sent to email.');
        setStep(2); 
    }

    function handleVerifyOTP(e) {
        e.preventDefault();
        setMessage(' Mock: OTP verified! Proceed to reset password.');
        setStep(3); 
    }

    function handleResetPassword(e) {
        e.preventDefault();
        setMessage(' Mock: Password reset successful! Redirecting...');
        setTimeout(() => navigator("/login"), 2000);
    }

    return (
        <div style={{
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            height: '100vh',
            background: 'linear-gradient(to right, #ffcc00, #ff6600)',
            textAlign: 'center'
        }}>
            <div style={{
                backdropFilter: 'blur(10px)',
                backgroundColor: 'rgba(255, 255, 255, 0.2)',
                borderRadius: '15px',
                padding: '30px',
                boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                width: '400px'
            }}>
                <h2 style={{ color: 'white' }}>Forgot Password</h2>
                {message && <p style={{ color: 'white', fontWeight: 'bold' }}>{message}</p>}

                {/* Step 1: Entering Email */}
                {step === 1 && (
                    <form onSubmit={handleSendEmail}>
                        <input 
                            type="email" 
                            placeholder="Enter Email" 
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            style={{ width: '100%', padding: '10px', borderRadius: '25px' }}
                            required
                        />
                        <button 
                            type="submit"
                            style={{
                                marginTop: '10px',
                                width: '100%',
                                padding: '12px',
                                borderRadius: '25px',
                                backgroundColor: '#007bff',
                                color: 'white',
                                fontSize: '18px',
                                fontWeight: 'bold',
                                border: 'none',
                                cursor: 'pointer'
                            }}
                        >
                            Send OTP
                        </button>
                    </form>
                )}

              
                {step === 2 && (
                    <form onSubmit={handleVerifyOTP}>
                        <input 
                            type="text" 
                            placeholder="Enter OTP" 
                            value={otp}
                            onChange={(e) => setOtp(e.target.value)}
                            style={{ width: '100%', padding: '10px', borderRadius: '25px' }}
                            required
                        />
                        <button 
                            type="submit"
                            style={{
                                marginTop: '10px',
                                width: '100%',
                                padding: '12px',
                                borderRadius: '25px',
                                backgroundColor: '#28a745',
                                color: 'white',
                                fontSize: '18px',
                                fontWeight: 'bold',
                                border: 'none',
                                cursor: 'pointer'
                            }}
                        >
                            Verify OTP
                        </button>
                    </form>
                )}

               
                {step === 3 && (
                    <form onSubmit={handleResetPassword}>
                        <input 
                            type="password" 
                            placeholder="Enter New Password" 
                            value={newPassword}
                            onChange={(e) => setNewPassword(e.target.value)}
                            style={{ width: '100%', padding: '10px', borderRadius: '25px' }}
                            required
                        />
                        <button 
                            type="submit"
                            style={{
                                marginTop: '10px',
                                width: '100%',
                                padding: '12px',
                                borderRadius: '25px',
                                backgroundColor: '#dc3545',
                                color: 'white',
                                fontSize: '18px',
                                fontWeight: 'bold',
                                border: 'none',
                                cursor: 'pointer'
                            }}
                        >
                            Reset Password
                        </button>
                    </form>
                )}
            </div>
        </div>
    );
};

export default ForgotPasswordComponent;
=======
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const ForgotPasswordComponent = () => {
    const [email, setEmail] = useState('');
    const [otp, setOtp] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [step, setStep] = useState(1);
    const [message, setMessage] = useState('');
    const navigator = useNavigate();

    function handleSendEmail(e) {
        e.preventDefault();
        setMessage(' Mock: Verification OTP sent to email.');
        setStep(2); 
    }

    function handleVerifyOTP(e) {
        e.preventDefault();
        setMessage(' Mock: OTP verified! Proceed to reset password.');
        setStep(3); 
    }

    function handleResetPassword(e) {
        e.preventDefault();
        setMessage(' Mock: Password reset successful! Redirecting...');
        setTimeout(() => navigator("/login"), 2000);
    }

    return (
        <div style={{
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            height: '100vh',
            background: 'linear-gradient(to right, #ffcc00, #ff6600)',
            textAlign: 'center'
        }}>
            <div style={{
                backdropFilter: 'blur(10px)',
                backgroundColor: 'rgba(255, 255, 255, 0.2)',
                borderRadius: '15px',
                padding: '30px',
                boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                width: '400px'
            }}>
                <h2 style={{ color: 'white' }}>Forgot Password</h2>
                {message && <p style={{ color: 'white', fontWeight: 'bold' }}>{message}</p>}

                {/* Step 1: Entering Email */}
                {step === 1 && (
                    <form onSubmit={handleSendEmail}>
                        <input 
                            type="email" 
                            placeholder="Enter Email" 
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            style={{ width: '100%', padding: '10px', borderRadius: '25px' }}
                            required
                        />
                        <button 
                            type="submit"
                            style={{
                                marginTop: '10px',
                                width: '100%',
                                padding: '12px',
                                borderRadius: '25px',
                                backgroundColor: '#007bff',
                                color: 'white',
                                fontSize: '18px',
                                fontWeight: 'bold',
                                border: 'none',
                                cursor: 'pointer'
                            }}
                        >
                            Send OTP
                        </button>
                    </form>
                )}

              
                {step === 2 && (
                    <form onSubmit={handleVerifyOTP}>
                        <input 
                            type="text" 
                            placeholder="Enter OTP" 
                            value={otp}
                            onChange={(e) => setOtp(e.target.value)}
                            style={{ width: '100%', padding: '10px', borderRadius: '25px' }}
                            required
                        />
                        <button 
                            type="submit"
                            style={{
                                marginTop: '10px',
                                width: '100%',
                                padding: '12px',
                                borderRadius: '25px',
                                backgroundColor: '#28a745',
                                color: 'white',
                                fontSize: '18px',
                                fontWeight: 'bold',
                                border: 'none',
                                cursor: 'pointer'
                            }}
                        >
                            Verify OTP
                        </button>
                    </form>
                )}

               
                {step === 3 && (
                    <form onSubmit={handleResetPassword}>
                        <input 
                            type="password" 
                            placeholder="Enter New Password" 
                            value={newPassword}
                            onChange={(e) => setNewPassword(e.target.value)}
                            style={{ width: '100%', padding: '10px', borderRadius: '25px' }}
                            required
                        />
                        <button 
                            type="submit"
                            style={{
                                marginTop: '10px',
                                width: '100%',
                                padding: '12px',
                                borderRadius: '25px',
                                backgroundColor: '#dc3545',
                                color: 'white',
                                fontSize: '18px',
                                fontWeight: 'bold',
                                border: 'none',
                                cursor: 'pointer'
                            }}
                        >
                            Reset Password
                        </button>
                    </form>
                )}
            </div>
        </div>
    );
};

export default ForgotPasswordComponent;
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
