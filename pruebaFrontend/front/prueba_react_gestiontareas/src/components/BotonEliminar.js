import React from "react";


const BotonEliminar = ({tareaId})=>{


    const handleDeleteTarea = async (tareaId) =>{
        try{
            const response = await fetch(`http://localhost:8080/api/v1/tareas/eliminarTarea/${tareaId}`,{
                method: "DELETE",
                headers:{
                    "Content-Type": "application/json"
                }
            });

            const tareaIsdelete = response;
            if(tareaIsdelete){
                alert("La tarea ha sido eliminada")
            }
        }catch(error){
            console.error("Error al momento de eliminar")
        }
    };


    return(
        <button onClick={()=>handleDeleteTarea(tareaId)}>Eliminar tarea</button>

    );

}


export default BotonEliminar;