import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "../pages/HomePage.css";
import axios from "axios";

function HomePage() {
  const navigate = useNavigate();
  const [festivali, setFestivali] = useState([]);
  const [error, setError] = useState("");
  const [search, setSearch] = useState("");

 useEffect(() => {

  const fetchFestivals = async () => {
    try {
      const res = await axios.get(
        "http://localhost:8081/festival/all"
      );

      setFestivali(res.data);

    } catch (err) {
      if (err.response?.status === 404) {
        setError("Nema festivala u bazi.");
      } else {
        setError("Greška kod dohvaćanja festivala.");
      }
    }
  };

  fetchFestivals();

}, []);

const handleDelete = async (idFestival) => {

  const potvrda = window.confirm(
    "Jeste li sigurni da želite obrisati festival?"
  );

  if (!potvrda) return;

  try {

    await axios.delete(
      `http://localhost:8081/festival/delete/${idFestival}`
    );

        setFestivali(
        festivali.filter((f) => f.id !== idFestival)
        );

    alert("Festival uspješno obrisana!");

  } catch (err) {

    console.error(err);

    setError("Greška kod brisanja aktivnosti.");
  }
};

const filteredFestivali = festivali.filter((f) =>
  f.nazivFestivala.toLowerCase().includes(search.toLowerCase())
);

  return (
  <div>
    <div className="header">
        Sustav za glazbene festivale
      </div>
    <h2 className="title">Festivali</h2>

    {error && <p style={{ color: "red" }}>{error}</p>}

    <button className="newButton" onClick={() => navigate(`/festival/`)}>Kreiraj novi festival</button>

    <div className="festival-container">
        <input
            type="text"
            placeholder="Pretraži festivale po nazivu..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            />
            <button onClick={() => setSearch("")}>
            Reset
            </button>
        <p>Dostupni festivali:</p>
        {filteredFestivali.length === 0 ? (
        <p>Nema rezultata.</p>
        ) : (
        filteredFestivali.map((f) => (
            <div key={f.id} className="festival-card">
            <h3>{f.nazivFestivala}</h3>
            <p>{f.lokacijaFestivala}</p>
            <p>Dobna skupina: {f.dobnaSkupina}</p>
            <p>Trajanje: {f.vrijemePocetka} - {f.vrijemeKraja}</p>

            <button onClick={() => navigate(`/festival/${f.id}`)}>
                Otvori
            </button>
            </div>
        ))
        )}

    </div>
  </div>
);

}
export default HomePage;