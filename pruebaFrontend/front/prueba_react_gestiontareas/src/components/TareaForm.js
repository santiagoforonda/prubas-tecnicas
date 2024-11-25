import React, { useState } from "react";
import { TextField,Button, DialogTitle, DialogContent } from "@mui/material";
import Dialog from '@mui/material/Dialog';
import "../estilosComponentes/tareaForm.css";


const TareaForm = ({agregarTarea})=>{
    
    const defaultFormData = {
        titulo:"",
        descripcion:""
    }

    const [newTarea,setNewTarea] = useState(defaultFormData);
    const [open, setOpen] = useState(false);


    const handleClose = ()=>{
        setOpen(false)
        setNewTarea(defaultFormData)
    }

    const handleOpen=()=>{
        setOpen(true);
    }

    const handleSubmit =(e)=>{
        e.preventDefault();
        agregarTarea(newTarea);
        handleClose();
    }

    const handleChange = (e)=>{
        const {name,value} = e.target;
        setNewTarea({...newTarea,[name]:value})
    }

    return (
        <div>
           <Button onClick={()=>handleOpen()} variant="contained">Agregar una tarea</Button>
            <Dialog open={open} onClose={handleClose} className="dialog-contenedor">
                <DialogTitle className="titulo-formulario">Formulario para agregar una tarea</DialogTitle>
                <DialogContent className="formulario-contenedor">
                    <form onSubmit={(e)=>{handleSubmit(e)}} className="formulario">
                    <TextField pattern="^[A-Za-z\s]+$" id="outlined-basic" label="Titulo" variant="outlined" name="titulo" value={newTarea.titulo} required onChange={handleChange} type="text" />
                    <TextField  pattern="^.{1,255}" id="outlined-basic" label="descripcion" variant="outlined" type="text" name="descripcion" value={newTarea.descripcion} required onChange={handleChange}></TextField>
                    <Button type="submit" variant="contained" color="success">Agregar tarea</Button>
                    <Button variant="contained" onClick={()=> handleClose()} color="error">Cerrar</Button>
                    </form>
                </DialogContent>
            </Dialog>
        </div>
    );
}

export default TareaForm;