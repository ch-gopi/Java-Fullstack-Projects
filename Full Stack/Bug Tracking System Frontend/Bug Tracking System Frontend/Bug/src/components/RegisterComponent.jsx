import React, { useState } from 'react';
import { registerAPICall } from '../services/AuthService';
import { useNavigate } from 'react-router-dom';
import backgroundImage from '../assets/inm.png'; 

const RegisterComponent = () => {
    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState(''); 
    const navigator = useNavigate();

    async function handleRegistrationForm(e) {
        e.preventDefault();
        setMessage(''); // Reset message
        if (!name || !username || !email || !password) {
            setMessage('⚠️ All fields are required!');
            return;
        }

        const registerData = { name, username, email, password };

        try {
            const response = await registerAPICall(registerData);
            console.log(response.data);

            if (response.status===201) {
                setMessage('✅ Registration Successful! Redirecting to Login...');
                setTimeout(() => navigator("/login"), 2000);
            } else {
                setMessage(response.data.message || '⚠️ Registration failed.');
            }
        } catch (error) {
            console.error(error);
            if (error.response) {
                switch (error.response.status) {
                    case 400:
                        setMessage('❌ User name or email already exists.Change User name Or Try logging in.');
                        break;
                    case 409:
                        setMessage('⚠️ Please fill all required fields.');
                        break;
                    case 500:
                        setMessage('❌ Server error. Try again later.');
                        break;
                    default:
                        setMessage('❌ Something went wrong. Please try again.');
                }
            } else {
                setMessage('❌ Network error. Check your connection.');
            }
        }
    }

    return (
        <div style={{
            backgroundImage: `url(${backgroundImage})`,
            backgroundSize: 'cover',
            backgroundPosition: 'center',
            height: '100vh',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center'
        }}>
            <div style={{
                backdropFilter: 'blur(10px)',
                backgroundColor: 'rgba(255, 255, 255, 0.2)',
                borderRadius: '15px',
                padding: '30px',
                marginTop: '30px', 
                boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                
                width: '350px',
                textAlign: 'center'
            }}>
                <h2 style={{ color: 'white' }}>Register</h2>
                {message && (
                    <p style={{
                        color: message.includes('✅') ? '#28a745' : '#ff4d4d',
                        fontWeight: 'bold',
                        fontSize: '16px'
                    }}>
                        {message}
                    </p>
                )}
                <form>
                    <div className='mb-3'>
                        <input
                            type='text'
                            name='name'
                            className='form-control'
                            placeholder='Enter Name'
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            style={{ borderRadius: '25px', padding: '10px', fontSize: '16px' }}
                        />
                    </div>
                    <div className='mb-3'>
                        <input
                            type='text'
                            name='username'
                            className='form-control'
                            placeholder='Enter Username'
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            style={{ borderRadius: '25px', padding: '10px', fontSize: '16px' }}
                        />
                    </div>
                    <div className='mb-3'>
                        <input
                            type='email'
                            name='email'
                            className='form-control'
                            placeholder='Enter Email'
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            style={{ borderRadius: '25px', padding: '10px', fontSize: '16px' }}
                        />
                    </div>
                    <div className='mb-3'>
                        <input
                            type='password'
                            name='password'
                            className='form-control'
                            placeholder='Enter Password'
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            style={{ borderRadius: '25px', padding: '10px', fontSize: '16px' }}
                        />
                    </div>
                    <button 
                        style={{
                            borderRadius: '25px',
                            padding: '12px',
                            width: '100%',
                            fontSize: '18px',
                            fontWeight: 'bold',
                            backgroundColor: '#28a745',
                            color: 'white',
                            border: 'none',
                            cursor: 'pointer',
                            transition: 'background 0.3s ease'
                        }}
                        onMouseOver={(e) => e.target.style.backgroundColor = '#218838'}
                        onMouseOut={(e) => e.target.style.backgroundColor = '#28a745'}
                        onClick={(e) => handleRegistrationForm(e)}
                    >
                        Register
                    </button>
                </form>
            </div>
        </div>
    );
};

export default RegisterComponent;
