import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { fetchTareas, fetchEliminarTarea } from '../controllers/TareasController';
import LoadingOverlay from './LoadingOverlay';

const ListadoTareas = () => {
    const navigate = useNavigate();

    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const [loading, setLoading] = useState(false);

    const [tareas, setTareas] = useState([]);

    const fetchTareasFromApi = async () => {
        setLoading(true);
        const data = await fetchTareas();
        if (data.success){
            setError(null);
            setTareas(data.respuesta);
        }
        else{
            setError(data.error);
        }

        setLoading(false);
    };

    function handleEditar(tarea) {
      navigate(`/formulario/${tarea.id}`, { state: { tarea } });
    }
    
    const handleEliminar = async (id) => {
      setLoading(true);
      const data = await fetchEliminarTarea(id);
      if (data.success){
          setError(null);
          fetchTareasFromApi();
      }
      else{
          setError(data.error);
      }

      setLoading(false);
    }

    useEffect(() => {
      fetchTareasFromApi();
    }, []);

    return (
      <div className="container">
      {loading && <LoadingOverlay />}
        <div className="form-container form-list">
          <h2>Horario</h2>
          <table className="table">
            <thead>
              <tr>
                <th scope="col">Titulo</th>
                <th scope="col">Descripción</th>
                <th scope="col">Fecha de inicio</th>
                <th scope="col">Fecha de finalización</th>
                <th scope="col">Tiempo en días</th>
                <th scope="col">Prioridad</th>
                <th scope="col">Acciones</th>
              </tr>
            </thead>
            <tbody>
              {tareas && tareas.length > 0 ? (tareas.map((tarea) => (
                <tr key={tarea.id}>
                  <td>{tarea.titulo}</td>
                  <td>{tarea.descripcion}</td>
                  <td>{tarea.fechaInicio}</td>
                  <td>{tarea.fechaFin}</td>
                  <td>{tarea.tiempo}</td>
                  <td>
                  {tarea.prioridad === 1 ? "Baja" :
                  tarea.prioridad === 2 ? "Media" :
                  tarea.prioridad === 3 ? "Alta" :
                  "Desconocida"}
                  </td>
                  <td>
                    <a href='javascript:void(0);' onClick={() => handleEditar(tarea)}>Editar</a>&nbsp;|&nbsp; 
                    <a href='javascript:void(0);' onClick={() => handleEliminar(tarea.id)}>Eliminar</a>
                  </td>
                </tr>
              ))) : (<tr>
                      <td colSpan="7">No hay tareas</td>
                    </tr>)}
            </tbody>
          </table>
          <div className='row'>
            <div className="mb-3 d-grid gap-2 d-flex justify-content-center">
                <Link to={`/formulario/${0}`} className="btn btn-primary">Crear tarea</Link>
              </div>
            </div>
          </div>
          {error && <div className="error-message">{error}</div>}
          {success && <div className="success-message">{success}</div>}
      </div>
    );
};

export default ListadoTareas;