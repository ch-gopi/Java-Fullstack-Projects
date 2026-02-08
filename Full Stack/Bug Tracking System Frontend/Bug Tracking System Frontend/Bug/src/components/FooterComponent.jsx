import React, { useState, useEffect } from 'react';

const FooterComponent = () => {
    const [isVisible, setIsVisible] = useState(true);

    useEffect(() => {
        let prevScrollPos = window.pageYOffset;

        const handleScroll = () => {
            let currentScrollPos = window.pageYOffset;
            setIsVisible(prevScrollPos > currentScrollPos || currentScrollPos < 10);
            prevScrollPos = currentScrollPos;
        };

        window.addEventListener('scroll', handleScroll);
        return () => window.removeEventListener('scroll', handleScroll);
    }, []);

    return (
        <div style={{
            position: 'fixed',
            fontSize: '16px',
            bottom: isVisible ? 0 : '-60px',  
            width: '100%',
            height: '50px',
            backgroundColor: 'black',
            textAlign: 'center',
            color: 'white',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            transition: 'bottom 0.3s ease-in-out'  
        }}>
            <p>Copyrights reserved By @Gopi.Ch</p>
        </div>
    );
};

export default FooterComponent;
