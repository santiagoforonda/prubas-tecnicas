import React, { useEffect, useState } from "react";
import conexion from "./assets/conexion.js";
import TareaList from "./TareaList.js";
import BotonAgregar from "./BotonAgregar.js";
import {  Container, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import { tableCellClasses } from '@mui/material/TableCell';
import { styled } from '@mui/material/styles';
import '../estilosComponentes/Tarea.css';
const tareasData = [];


const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
      backgroundColor: theme.palette.common.black,
      color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
      fontSize: 14,
    },
  }));
  
  const StyledTableRow = styled(TableRow)(({ theme }) => ({
    backgroundColor: '#f5f5f5',
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.common.white,
      
    },
    '&:last-child td, &:last-child th': {
      border: 0,
    },
  }));


const Tarea = () =>{
    const [tareas, setTareas] = useState(tareasData);
    const [filtro,setfiltro] = useState(1);
    const tareasObtenidas = async ()=>{
        try{

            switch(filtro){
                case 1:
                    const response = await fetch(conexion.MOSTRAR_ALLTAREAS);
                    const tareasList = await response.json();
                    setTareas(tareasList);
                    break;
                case 2:
                    const responsePendientes = await fetch(conexion.MOSTRAR_TAREAS_PENDIENTES);
                    const tareasListPendientes = await responsePendientes.json();
                    setTareas(tareasListPendientes);
                    break;
                case 3:
                    const responseCompletadas = await fetch(conexion.MOSTRAR_TAREAS_COMPLETADAS);
                    const tareasListCompletadas = await responseCompletadas.json();
                    setTareas(tareasListCompletadas);
                    break;
                default:
                    alert("No hay conexion con la base de datos");
            }
        }catch(error){
            console.log("Error al momento de obtener las tareas: ", error);
        }
    }
    useEffect(()=>{tareasObtenidas();},[filtro]);


    const handleChange =(opcion)=>{
        setfiltro(opcion);
    }
    
    return(
        <Container className="contenedor-principal">
            
            <div className="contenedor-botones">
            <BotonAgregar setTareas={setTareas}></BotonAgregar>
                <button className="btn-mostrar" onClick={()=>handleChange(1)}>MOSTRAR TODAS LAS TAREAS</button>
                <button className="btn-pendientes" onClick={()=>handleChange(2)}>MOSTRAR TAREAS PENDIENTES</button>
                <button className="btn-completadas" onClick={()=>handleChange(3)}>MOSTRAR TAREAS COMPLETADAS</button>
            </div>
                
               
         
                <TableContainer className="contenedor-tabla">
                    <Table aria-label="simple table" className="tabla">
                        <TableHead className="cabecera">
                            <StyledTableRow className="titulos">
                                <StyledTableCell align="center" >ID</StyledTableCell>
                                <StyledTableCell align="center"> Titulo </StyledTableCell>
                                <StyledTableCell align="center"> Descripcion</StyledTableCell>
                                <StyledTableCell align="center"> Estado</StyledTableCell>
                                <StyledTableCell align="center"> Editar </StyledTableCell>
                                <StyledTableCell align="center"> Eliminar </StyledTableCell>
                            </StyledTableRow>
                        </TableHead>


                        <TableBody>
                            <TareaList tareas={tareas}></TareaList>
                        </TableBody>
                    </Table>
                </TableContainer>
        </Container>
        
            
      
    );
}


export default Tarea;