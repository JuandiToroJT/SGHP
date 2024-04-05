const API_URL = 'http://localhost:8080/tareas';

export const fetchTareas = async () => {
    try {
        const response = await fetch(`${API_URL}/listar`);
    
        if (!response.ok && response.status !== 400) {
          const errorMessage = `Error en la solicitud: ${response.statusText}`;
          return { error: errorMessage, success: false };
        }
    
        const data = await response.json();
        if (response.status === 400){
            return { error: data.mensajeError, success: false };
        }

        return { respuesta: data, success: true };
      } catch (error) {
        console.error('Error al obtener las tareas:', error);
        return { error: 'Error al obtener las tareas', success: false };
      }
};

export const fetchPostTarea = async (body) => {
  try {
      let url = body.id > 0 ? `${API_URL}/editar` : `${API_URL}/adicionar`;
      let metodo = body.id > 0 ? "PUT" : "POST";
      const response = await fetch(url, {
          method: metodo,
          headers: {
          'Content-Type': 'application/json',
          },
          body: JSON.stringify(body),
      });
  
      if (!response.ok && response.status !== 400) {
        const errorMessage = `Error en la solicitud: ${response.statusText}`;
        return { error: errorMessage, success: false };
      }
  
      const data = await response.json();
      if (response.status === 400){
          return { error: data.mensajeError, success: false };
      }

      return { respuesta: data, success: true };
    } catch (error) {
      console.error('Error al registrar la tarea:', error);
      return { error: 'Error al registrar la tarea', success: false };
    }
};

export const fetchEliminarTarea = async (id) => {
  try {
      const response = await fetch(`${API_URL}/${id}/eliminar`, {
          method: "DELETE",
          headers: {
          'Content-Type': 'application/json',
          }
      });
  
      if (!response.ok && response.status !== 400) {
        const errorMessage = `Error en la solicitud: ${response.statusText}`;
        return { error: errorMessage, success: false };
      }
  
      const data = await response.json();
      if (response.status === 400){
          return { error: data.mensajeError, success: false };
      }

      return { respuesta: data, success: true };
    } catch (error) {
      console.error('Error al eliminar la tarea:', error);
      return { error: 'Error al eliminar la tarea', success: false };
    }
};