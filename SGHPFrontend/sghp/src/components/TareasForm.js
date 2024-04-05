import React, { useState } from 'react';
import { Link, useNavigate, useParams, useLocation } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { fetchPostTarea } from '../controllers/TareasController';
import LoadingOverlay from './LoadingOverlay';

const TareasForm = () => {
    const location = useLocation();
    const { tarea } = location.state || {};

    let { id } = useParams();

    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const navigate  = useNavigate();

    const [formData, setFormData] = useState({
        id: parseInt(id),
        titulo: (tarea !== undefined && tarea !== null) ? tarea.titulo : '',
        descripcion: (tarea !== undefined && tarea !== null) ? tarea.descripcion : '',
        fechaInicio: (tarea !== undefined && tarea !== null) ? tarea.fechaInicio : '',
        fechaFin: (tarea !== undefined && tarea !== null) ? tarea.fechaFin : '',
        tiempo: (tarea !== undefined && tarea !== null) ? tarea.tiempo : 0,
        prioridad: (tarea !== undefined && tarea !== null) ? tarea.prioridad : 0
      });

      const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
          }));
      };

      const handleChangeNumber = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: parseInt(value),
          }));
      };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        const data = await fetchPostTarea(formData);
        if (data.success){
            setError(null);
            navigate('/');
        }
        else{
            setError(data.error);
        }

        setLoading(false);
      };

  return (
    <div className="container">
      {loading && <LoadingOverlay />}
      <div className="form-container form-register">
        <h2>{ parseInt(id) === 0 ? "Crear" : "Editar" } tarea</h2>
        <form onSubmit={handleSubmit}>
            <div className="row">
            <div className="mb-3 col-12">
            <label htmlFor="nombre" className="form-label">Titulo:</label>
            <input type="text" className="form-control" id="titulo" name="titulo" maxLength="20" onChange={handleChange} value={formData.titulo} required />
          </div>
          <div className="mb-3 col-12">
            <label htmlFor="descripcion" className="form-label">Descripción:</label>
            <textarea
                className="form-control"
                id="descripcion"
                name="descripcion"
                onChange={handleChange}
                value={formData.descripcion}
                required
            ></textarea>
            </div>
            <div className="mb-3 col-6">
      <label htmlFor="fechaInicio" className="form-label">Fecha de inicio:</label>
      <input type="date" className="form-control" id="fechaInicio" name="fechaInicio" onChange={handleChange} value={formData.fechaInicio} required />
    </div>
    <div className="mb-3 col-6">
      <label htmlFor="fechaFin" className="form-label">Fecha de finalización:</label>
      <input type="date" className="form-control" id="fechaFin" name="fechaFin" onChange={handleChange} value={formData.fechaFin} required />
    </div>
    <div className="mb-3 col-6">
      <label htmlFor="tiempo" className="form-label">Tiempo en días:</label>
      <input type="number" className="form-control" id="tiempo" name="tiempo" onChange={handleChangeNumber} value={formData.tiempo} required />
    </div>
    <div className="mb-3 col-6">
      <label htmlFor="prioridad" className="form-label">Prioridad:</label>
      <select className="form-select" id="prioridad" name="prioridad" onChange={handleChangeNumber} value={formData.prioridad} required>
        <option value="0">Seleccionar</option>
        <option value="3">Alta</option>
        <option value="2">Media</option>
        <option value="1">Baja</option>
      </select>
    </div>
          <div className="mb-3 d-grid gap-2 d-flex justify-content-center">
            <button type="submit" className="btn btn-primary">Aplicar</button>
            <Link to="/" className="btn btn-secondary">Volver</Link>
          </div>
            </div>
        </form>
      </div>
      {error && <div className="error-message">{error}</div>}
    </div>
  );
};

export default TareasForm;