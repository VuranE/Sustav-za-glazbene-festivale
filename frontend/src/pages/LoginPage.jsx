import { useState } from 'react';
import axios from 'axios';
import './LoginPage.css';
import {useNavigate } from "react-router-dom";

export default function LoginPage() {
    const navigate = useNavigate();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log({ username, password });
    };

    const handleLogin = async (e) => {
    e.preventDefault();

    try {
        const response = await axios.post(
            "http://localhost:8081/api/auth/login",
            {
                username,
                password,
            }
        );

        console.log(response.data);

        if (response.data.success) {

            localStorage.setItem(
                "userType",
                response.data.type
            );

            if(response.data.type == 3){
                navigate(`/new-notification/`);
            } else if(response.data.type == 1) {

                navigate(`/home/`);
            } else {
                navigate(`/news-feed/`);
            }
            
            
        } else {
            alert("Neispravno korisničko ime ili lozinka");
        }
    } catch (error) {
        console.error(error);
    }
};
    return (
        <div className="login-container">
            <form className="login-form" onSubmit={handleLogin}>
                <h2 className='prijava'>Sustav za glazbene festivale</h2>
                <h2 className='prijava'>Prijava</h2>

                <input
                    type="text"
                    placeholder="Korisničko ime"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />

                <input
                    type="password"
                    placeholder="Lozinka"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />

                <button type="submit">Prijavi se</button>
            </form>
        </div>
    );
}