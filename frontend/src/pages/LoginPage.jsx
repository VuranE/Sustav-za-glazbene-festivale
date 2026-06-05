import { useState } from 'react';
import './LoginPage.css';

export default function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log({ username, password });
    };

    return (
        <div className="login-container">
            <form className="login-form" onSubmit={handleSubmit}>
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