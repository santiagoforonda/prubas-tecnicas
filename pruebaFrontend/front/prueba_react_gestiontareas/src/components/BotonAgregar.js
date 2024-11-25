import React from "react";
import conexion from "./assets/conexion";
import TareaForm from "./TareaForm";

const BotonAgregar = ({setTareas}) =>{


    const handleAddTarea = async (newTarea) =>{
        const newTareaTosend = {
            "titulo": newTarea.titulo,
            "descripcion":newTarea.descripcion
        }

        try{
            const response = await fetch(conexion.CREAR_TAREA,{
                method: "POST",
                headers:{
                    "Content-Type":"application/json",
                },
                body:JSON.stringify(newTareaTosend),
            });

            const data = await response.json();
            setTareas((tareas) => [...tareas,
                {
                "titulo" :data.titulo,
                "descripcion":data.descripcion}
            ]);
        }catch(error){
            console.error("Error al momento de agregar la tarea", error);
        }
    }

    return(
        <TareaForm agregarTarea={handleAddTarea}></TareaForm>

    );
}

export default BotonAgregar;