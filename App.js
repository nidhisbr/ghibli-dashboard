import './App.css';
import Header from './Film/Header';
import CardDetails from './Film/CardDetails';
import FilmTable from './Film/FilmTable';
import Login from './Film/Login';
import Signup from './Film/Signup';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';



function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <Routes>   
          <Route path="/" element={<Navigate to="/films" replace />} />       
          <Route path="/films" element={<FilmTable/>} />         
           <Route path="/films/:id" element={<CardDetails />} />
           <Route path="/login" element={<Login/>}/>
           <Route path="/signup" element={<Signup/>}/>
        </Routes>
      </Router>
        
    </div>
  );
}

export default App;
