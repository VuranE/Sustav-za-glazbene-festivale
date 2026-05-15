import React, { useState } from "react";
import "../src/app.css";

export default function FestivalPage() {

  const [festival, setFestival] = useState({
    naziv: "",
    id: "",
    dobnaSkupina: "",
    vrijemePocetka: "",
    vrijemeKraja: "",
    lokacija: "",
    idMape: ""
  });


  const [aktivnost, setAktivnost] = useState({
    idAktivnost: "",
    vrijemeAktivnosti: "",
    tipAktivnosti: "",
    lokacija: ""
  });

  const [aktivnosti, setAktivnosti] = useState([]);
  const [error, setError] = useState("");
  const [editId, setEditId] = useState(null);

  
  const tipoviAktivnosti = [
    { id: 1, naziv: "Koncert" },
    { id: 2, naziv: "Merch shop" },
    { id: 3, naziv: "Hrana i piće" },
    { id: 4, naziv: "Standovi" },
    { id: 5, naziv: "Radionice" }
  ];

  const lokacije = [
    { id: 1, naziv: "Pozornica 1" },
    { id: 2, naziv: "Pozornica 2" },
    { id: 3, naziv: "Pozornica 3" },
    { id: 4, naziv: "VIP section" }
  ];

 
  const handleFestivalChange = (e) => {
    setFestival({
      ...festival,
      [e.target.name]: e.target.value
    });
  };

 
  const handleAktivnostChange = (e) => {
    setAktivnost({
      ...aktivnost,
      [e.target.name]: e.target.value
    });
  };

  const validateFestival = () => {
    if (!festival.vrijemePocetka || !festival.vrijemeKraja) {
      setError("Molimo unesite oba vremena.");
      return false;
    }

    if (festival.vrijemePocetka >= festival.vrijemeKraja) {
      setError("Početak mora biti prije kraja.");
      return false;
    }

    setError("");
    return true;
  };

  const handleFestivalSubmit = async (e) => {
    e.preventDefault();

    if (!validateFestival()) return;

    try {
      const res = await fetch("http://localhost:8080/api/festivali", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(festival)
      });

      if (!res.ok) {
        const err = await res.json();
        setError(err.message || "Greška na serveru.");
        return;
      }

      alert("Festival uspješno spremljen!");
      setError("");

    } catch (err) {
      setError("Greška na serveru.");
    }
  };

  const handleAktivnostSubmit = async (e) => {
    e.preventDefault();

    try {


      if (editId !== null) {
        const updated = aktivnosti.map((a) =>
          a.idAktivnost === editId ? { ...a, ...aktivnost } : a
        );

        setAktivnosti(updated);
        setEditId(null);

        setAktivnost({
          idAktivnost: "",
          vrijemeAktivnosti: "",
          tipAktivnosti: "",
          lokacija: ""
        });

        return;
      }


      const res = await fetch(
        `http://localhost:8080/api/aktivnosti/${festival.id}`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            vrijemeAktivnosti: aktivnost.vrijemeAktivnosti,
            idTipAktivnosti: Number(aktivnost.tipAktivnosti),
            idLokacija: Number(aktivnost.lokacija)
          })
        }
      );

      if (!res.ok) {
        setError("Greška kod dodavanja aktivnosti.");
        return;
      }

      const nova = await res.json();
      setAktivnosti([...aktivnosti, nova]);

      setAktivnost({
        idAktivnost: "",
        vrijemeAktivnosti: "",
        tipAktivnosti: "",
        lokacija: ""
      });

      setError("");

    } catch (err) {
      setError("Greška na serveru.");
    }
  };


  const handleDelete = (idAktivnost) => {
    setAktivnosti(
      aktivnosti.filter((a) => a.idAktivnost !== idAktivnost)
    );
  };


  const handleEdit = (a) => {
    setAktivnost({
      idAktivnost: a.idAktivnost,
      vrijemeAktivnosti: a.vrijemeAktivnosti,
      tipAktivnosti: a.idTipAktivnosti,
      lokacija: a.idLokacija
    });

    setEditId(a.idAktivnost);
  };


  const getTipNaziv = (id) => {
    const t = tipoviAktivnosti.find((x) => x.id == id);
    return t ? t.naziv : "";
  };

  const getLokacijaNaziv = (id) => {
    const l = lokacije.find((x) => x.id == id);
    return l ? l.naziv : "";
  };

  return (
    <div>

      <div className="header">
        Sustav za glazbene festivale
      </div>

      <div className="container">

        <h2 className="title">Dodaj festival</h2>

        <div className="form-box">
          <form onSubmit={handleFestivalSubmit}>

            <input name="naziv" placeholder="Naziv" value={festival.naziv} onChange={handleFestivalChange} />
            <input name="id" placeholder="ID" value={festival.id} onChange={handleFestivalChange} />
            <input name="dobnaSkupina" placeholder="Dobna skupina" value={festival.dobnaSkupina} onChange={handleFestivalChange} />

            <input type="datetime-local" name="vrijemePocetka" value={festival.vrijemePocetka} onChange={handleFestivalChange} />
            <input type="datetime-local" name="vrijemeKraja" value={festival.vrijemeKraja} onChange={handleFestivalChange} />

            <input name="lokacija" placeholder="Lokacija" value={festival.lokacija} onChange={handleFestivalChange} />
            <input name="idMape" placeholder="ID mape" value={festival.idMape} onChange={handleFestivalChange} />

            <button type="submit">Spremi festival</button>

          </form>
        </div>

        {error && <p style={{ color: "red", textAlign: "center" }}>{error}</p>}

  
        <h2 className="title">Dodaj aktivnosti za festivale</h2>

        <div className="activities-section">


          <div className="form-box left">

            <form onSubmit={handleAktivnostSubmit}>

              <input
                type="datetime-local"
                name="vrijemeAktivnosti"
                value={aktivnost.vrijemeAktivnosti}
                onChange={handleAktivnostChange}
              />
              <select
                name="tipAktivnosti"
                value={aktivnost.tipAktivnosti}
                onChange={handleAktivnostChange}
              >
                <option value="">Odaberi tip</option>
                {tipoviAktivnosti.map((t) => (
                  <option key={t.id} value={t.id}>
                    {t.naziv}
                  </option>
                ))}
              </select>

              <select
                name="lokacija"
                value={aktivnost.lokacija}
                onChange={handleAktivnostChange}
              >
                <option value="">Odaberi lokaciju</option>
                {lokacije.map((l) => (
                  <option key={l.id} value={l.id}>
                    {l.naziv}
                  </option>
                ))}
              </select>

              <button type="submit">
                {editId !== null ? "Spremi izmjene" : "Dodaj aktivnost"}
              </button>

            </form>

          </div>

          <div className="right">

            <h3>Aktivnosti</h3>

            {aktivnosti.length === 0 ? (
              <p>Nema aktivnosti.</p>
            ) : (
              aktivnosti.map((a) => (
                <div key={a.idAktivnost}>

                  <strong>Aktivnost #{a.idAktivnost}</strong>

                  <p>Vrijeme: {a.vrijemeAktivnosti}</p>
                  <p>Tip: {getTipNaziv(a.idTipAktivnosti)}</p>
                  <p>Lokacija: {getLokacijaNaziv(a.idLokacija)}</p>

                  <button onClick={() => handleEdit(a)}>Uredi</button>

                  <button
                    onClick={() => handleDelete(a.idAktivnost)}
                    style={{ marginLeft: "10px", color: "red" }}
                  >
                    Obriši
                  </button>

                  <hr />
                </div>
              ))
            )}

          </div>

        </div>

      </div>
    </div>
  );
}