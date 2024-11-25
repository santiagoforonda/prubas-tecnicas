import React from "react";
import BotonEditar from "./BotonEditar.js";
import BotonEliminar from "./BotonEliminar.js";
import { TableCell, TableRow } from "@mui/material";
import { tableCellClasses } from '@mui/material/TableCell';
import { styled } from '@mui/material/styles';

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
    backgroundColor:'#f5f5f5',
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.action.white,
    },
    '&:last-child td, &:last-child th': {
      border: 0,
    },
  }));

const TareaRow = ({tarea, tareas}) =>{
    return(
        <StyledTableRow>
            <StyledTableCell>{tarea.id}</StyledTableCell>
            <StyledTableCell>{tarea.titulo}</StyledTableCell>
            <StyledTableCell>{tarea.descripcion}</StyledTableCell>
            <StyledTableCell>{tarea.estado}</StyledTableCell>
            <StyledTableCell><BotonEditar tarea={tarea} tareas={tareas}></BotonEditar></StyledTableCell>
            <StyledTableCell><BotonEliminar tareaId={tarea.id}></BotonEliminar></StyledTableCell>
        </StyledTableRow>
    )
}

export default TareaRow;