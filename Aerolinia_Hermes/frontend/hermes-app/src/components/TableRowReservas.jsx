import React from "react";
import { useNavigate } from "react-router-dom";

const TableRowReservas = ({element, onDelete}) =>{

    const navigate = useNavigate();
    const handleUpdate=()=>{
        navigate(`/updateReserva/${element.id}`);
    }

    const callDelete =()=>{
        onDelete(element);
    }

    return(
        <tr>
            <td>{element.id}</td>
            <td>{element.status}</td>
            <td>{element.referenceCode}</td>
            <td>{element.checkInd?"Realizado":"No realizado"}</td>
            <td>{element.createdAt}</td>
            <td>
                <button onClick={handleUpdate}>Actualizar</button>
                <button onClick={callDelete}>Eliminar</button>
            </td>
        </tr>
    )
}


export default TableRowReservas;