import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import "../pages/FestivalPage.css";
import axios from "axios";

function FestivalPage() {

  const { id } = useParams();

  useEffect(() => {

  if (!id) return;

  const fetchFestival = async () => {

  try {

    const res = await axios.get(
      `http://localhost:8081/festival/details/${id}`
    );
    

    const data = res.data;

    setFestival({
      nazivFestivala: data.nazivFestivala || "",
      id: data.id || "",
      dobnaSkupina: data.dobnaSkupina || "",
      vrijemePocetka: data.vrijemePocetka || "",
      vrijemeKraja: data.vrijemeKraja || "",
      lokacijaFestivala: data.lokacijaFestivala || "",
      idMape: data.mapaID || ""
    });

    if (data.aktivnosti) {
      setAktivnosti(data.aktivnosti);
    }

  } catch (err) {

    if (err.response?.status === 404) {
      setError("Festival nije pronađen.");
    } else {
      setError("Greška kod dohvaćanja festivala.");
    }
  }
};

  fetchFestival();

}, [id]);

  const [festival, setFestival] = useState({
    nazivFestivala: "",
    id: "",
    dobnaSkupina: "",
    vrijemePocetka: "",
    vrijemeKraja: "",
    lokacijaFestivala: "",
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
  const [showAktivnostForm, setShowAktivnostForm] = useState(false);

  
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

    const payload = {
      id: festival.id || null,
      nazivFestivala: festival.nazivFestivala,
      dobnaSkupina: Number(festival.dobnaSkupina),
      vrijemePocetka: festival.vrijemePocetka,
      vrijemeKraja: festival.vrijemeKraja,
      lokacijaFestivala: festival.lokacijaFestivala,
      mapaID: Number(festival.idMape)
    };

    let response;

    // UPDATE
    if (id) {
      response = await axios.patch(
        "http://localhost:8081/festival/update-festival",
        payload
      );
    }

    // CREATE
    else {
      response = await axios.post(
        "http://localhost:8081/festival/create-festival",
        payload
      );
    }

    alert("Festival uspješno spremljen!");
    setError("");

   
    setFestival(response.data);

  } catch (err) {

    console.error(err);
    setError("Greška na serveru.");
  }
};

  const handleAktivnostSubmit = async (e) => {

  e.preventDefault();

  try {

    const payload = {
      vrijemeAktivnosti: aktivnost.vrijemeAktivnosti,
      tipAktivnostiID: Number(aktivnost.tipAktivnosti),
      festivalID: Number(festival.id),
      lokacijaID: Number(aktivnost.lokacija),
      naziv: aktivnost.naziv || ""
    };

    // UPDATE
    if (editId !== null) {

      const { data } = await axios.patch(
        "http://localhost:8081/aktivnost/update-aktivnost",
        payload
      );

      const updated = aktivnosti.map((a) =>
        a.idAktivnost === editId ? data : a
      );

      setAktivnosti(updated);

      setEditId(null);

    } else {

      // CREATE
      const { data } = await axios.post(
        "http://localhost:8081/aktivnost/create-aktivnost",
        payload
      );

      setAktivnosti([...aktivnosti, data]);
    }

    // reset forme
    setAktivnost({
      idAktivnost: "",
      vrijemeAktivnosti: "",
      tipAktivnosti: "",
      lokacija: ""
    });

    setError("");

  } catch (err) {

    console.error(err);

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

        <h2 className="title">Festival</h2>

        <div className="form-box">
          <form onSubmit={handleFestivalSubmit}>

            <input name="nazivFestivala" placeholder="Naziv" value={festival.nazivFestivala} onChange={handleFestivalChange} />
            <input name="id" placeholder="ID" value={festival.id} onChange={handleFestivalChange} disabled/>
            <input name="dobnaSkupina" placeholder="Dobna skupina" value={festival.dobnaSkupina} onChange={handleFestivalChange} />

            <input type="datetime-local" name="vrijemePocetka" value={festival.vrijemePocetka} onChange={handleFestivalChange} />
            <input type="datetime-local" name="vrijemeKraja" value={festival.vrijemeKraja} onChange={handleFestivalChange} />

            <input name="lokacijaFestivala" placeholder="Lokacija" value={festival.lokacijaFestivala} onChange={handleFestivalChange} />
            <input name="idMape" placeholder="ID mape" value={festival.idMape} onChange={handleFestivalChange} />

            <button type="submit">Spremi festival</button>

          </form>
        </div>

        {error && <p style={{ color: "red", textAlign: "center" }}>{error}</p>}

  
        <h2 className="title">Aktivnosti festivala</h2>

        <div className="activities-section">

            <div className="right">

            <h3>Aktivnosti</h3>

            {aktivnosti.length === 0 ? (
              <p>Nema aktivnosti.</p>
            ) : (
              aktivnosti.map((a) => (
                <div key={a.idAktivnost}>

                  <strong>Aktivnost #{a.idAktivnost} {a.naziv}</strong>

                  <p>Vrijeme: {a.vrijemeAktivnosti}</p>
                  <p>Tip: {a.tipAktivnosti?.opisTip}</p>
                  <p>Lokacija: {a.lokacija}</p>
                  

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

          <button
            onClick={() => setShowAktivnostForm(!showAktivnostForm)}
          >
            {showAktivnostForm ? "Zatvori formu" : "Dodaj novu aktivnost"}
          </button>
          {showAktivnostForm && (
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
          )}

          

        </div>

      </div>
    </div>
  );
}

export default FestivalPage;