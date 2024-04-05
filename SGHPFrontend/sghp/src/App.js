import React, { useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import TareasForm from './components/TareasForm';
import ListadoTareas from './components/listado-tareas';
import './App.css';

const App = () => {
  useEffect(() => {
    document.title = 'Sistema de Gestión de Horarios por Prioridad';
  }, []);

  return (
    <Router>
      <Routes>
        <Route path="/formulario/:id" element={<TareasForm />} />
        <Route path="/" element={<ListadoTareas />} />
      </Routes>
    </Router>
  );
}

export default App;
