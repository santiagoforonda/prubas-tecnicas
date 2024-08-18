import { Button, Dialog, DialogContent, DialogTitle, TextField } from "@mui/material";
import React from "react";
import { useState } from "react"
import '../estilosComponentes/botonEditar.css';

const BotonEditar = ({tarea, tareas})=>{

    const tareaUpdate = {
        "id":tarea.id,
        "titulo":tarea.titulo,
        "descripcion":tarea.descripcion,
        "estado":tarea.estado
    }

    const[newTarea, setNewTarea] = useState(tareaUpdate);
    const[tareasActuales, setTareasActuales] = useState(tareas);
    const[editar, setEditar] = useState(false);

    const handleClose=()=>{
        setEditar(false);
        setNewTarea(tareaUpdate);
    }

    const handleOpen=()=>{
        setEditar(true);
    }

    const handleChange = (e)=>{
        const {name,value} = e.target;
        setNewTarea({...newTarea,[name]:value})
    }

    const handleAddTarea = async(newTarea) =>{
        const newTareaTosend = {
            "id":newTarea.id,
            "titulo":newTarea.titulo,
            "descripcion":newTarea.descripcion,
            "estado":newTarea.estado
        };

        try{
            const response = await fetch(`http://localhost:8080/api/v1/tareas/actualizarTareas/${newTarea.id}`,{
                method:"PUT",
                headers:{
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(newTareaTosend)
            });
            const data = await response.json();
            for(const tarea of tareasActuales){
                if(data.id === tarea.id){
                    setTareasActuales(data);
                }
            }
        }catch(error){
            console.error("Error al momento de actualizar la tarea", error)
        }
    };

    const handleSubmit = (e)=>{
        e.preventDefault();
        handleAddTarea(newTarea);
        handleClose();
    }


    return(
        <div>
            <Button onClick={()=>{handleOpen()}}>Editar tarea</Button>

            <Dialog open={editar} onClose={handleClose}>
                <DialogTitle className="titulo-formulario">Editar tarea</DialogTitle>
                <DialogContent className="formulario-contenedor">
                <form onSubmit={(e)=>handleSubmit(e)} className="formulario">

                    <TextField  pattern="^[A-Za-z\s]+$" type="text" required onChange={handleChange} name="titulo" value={newTarea.titulo} id="outlined-basic" label="Titulo" variant="outlined"></TextField>
                    <TextField  pattern="^.{1,255}" type="text" required onChange={handleChange} name="descripcion" value={newTarea.descripcion} id="outlined-basic" label="Descripcion" variant="outlined"></TextField>
                    <TextField pattern="^[A-Z\s]+$" type="text" required onChange={handleChange} name="estado" value={newTarea.estado} id="outlined-basic" label="Estado" variant="outlined"></TextField>
                    <Button type="submit" color="success">Enviar</Button>
                    <Button onClick={()=>{handleClose()}} color="error">Cerrar</Button>
                </form>
                </DialogContent>


            </Dialog>
                
            

        </div>
    );
}

export default BotonEditar;