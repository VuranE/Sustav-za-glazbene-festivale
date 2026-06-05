import { useState } from "react";
import "../pages/NewNotificationPage.css"

export default function NewNotificationPage() {
    const [formData, setFormData] = useState({
        title: "",
        message: "",
        targetGroup: "",
        media: null,
    });

    const [confirmation, setConfirmation] = useState("");
    const [showConfirmation, setShowConfirmation] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;

        setFormData((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleFileChange = (e) => {
        setFormData((prev) => ({
            ...prev,
            media: e.target.files[0],
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (confirmation === "send") {
            console.log("Slanje obavijesti", formData);
        } else {
            console.log("Nastavak uređivanja");
        }
    };

    return (
        <div className="notification-page">
            <h1>Kreiranje obavijesti</h1>

            <form onSubmit={handleSubmit}>
                <div className="card">
                    <h2 className="prijava">notificationForm</h2>

                    <div className="form-group">
                        <label className="prijava">Naslov *</label>
                        <input
                            type="text"
                            name="title"
                            value={formData.title}
                            onChange={handleChange}
                            minLength={1}
                            maxLength={100}
                            required
                            placeholder="Unesite naslov obavijesti"
                        />
                        <small className="prijava">1 - 100 znakova</small>
                    </div>

                    <div className="form-group">
                        <label className="prijava">Poruka *</label>
                        <textarea
                            name="message"
                            value={formData.message}
                            onChange={handleChange}
                            minLength={1}
                            maxLength={500}
                            required
                            rows={6}
                            placeholder="Unesite tekst obavijesti"
                        />
                        <small className="prijava">1 - 500 znakova</small>
                    </div>

                    <div className="form-group">
                        <label className="prijava">Ciljna skupina *</label>

                        <label className="prijava">
                            <input
                                type="radio"
                                name="targetGroup"
                                value="participants"
                                checked={
                                    formData.targetGroup === "participants"
                                }
                                onChange={handleChange}
                                required
                            />
                            Posjetitelji i zaposlenici
                        </label>

                        <label className="prijava">
                            <input
                                type="radio"
                                name="targetGroup"
                                value="employees"
                                checked={
                                    formData.targetGroup === "employees"
                                }
                                onChange={handleChange}
                            />
                            Samo zaposlenici
                        </label>
                    </div>

                    <div className="form-group">
                        <label className="prijava">Slika (neobavezno)</label>
                        <input
                            type="file"
                            accept="image/*"
                            onChange={handleFileChange}
                        />
                    </div>
                </div>

                <div className="card">
    <button
        type="button"
        onClick={() => setShowConfirmation(true)}
    >
        Pošalji obavijest
    </button>
</div>
            </form>

            {showConfirmation && (
    <div className="modal-overlay">
        <div className="modal">
            <h2 className="prijava">Potvrda slanja</h2>

            <p className="prijava">
                Jeste li sigurni da želite poslati ovu obavijest?
            </p>

            <div className="modal-buttons">
                <button
                    type="button"
                    onClick={() => {
                        console.log("Slanje obavijesti", formData);
                        setShowConfirmation(false);
                    }}
                >
                    Pošalji obavijest
                </button>

                <button
                    type="button"
                    onClick={() => setShowConfirmation(false)}
                >
                    Nastavi uređivati
                </button>
            </div>
        </div>
    </div>
)}
        </div>
    );
}