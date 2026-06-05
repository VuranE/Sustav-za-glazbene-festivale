import './App.css';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import FestivalPage from './pages/FestivalPage';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import NewNotificationPage from './pages/NewNotificationPage';
import NewsFeedPage from './pages/NewsFeedPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/festival" element={<FestivalPage />} />
        <Route path="/festival/:id" element={<FestivalPage />} />
        <Route path="/new-notification" element={<NewNotificationPage/>}/>
        <Route path="/news-feed" element={<NewsFeedPage/>}/>
        
      </Routes>
    </Router>
  );
}

export default App;