import './App.css';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import FestivalPage from './pages/FestivalPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/festival" element={<FestivalPage />} />
        <Route path="/festival/:id" element={<FestivalPage />} />
        
      </Routes>
    </Router>
  );
}

export default App;