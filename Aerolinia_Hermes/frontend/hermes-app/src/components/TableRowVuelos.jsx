import React from "react";
import { useNavigate } from "react-router-dom";

const TableRowVuelos =({element,onDelete,onDetails}) =>{

    const navigate = useNavigate();
    const handleUpdate =()=>{
        navigate(`/updateVuelo/${element.id}`);
    }

    const callDelete = ()=>{
       onDelete(element);
    }


    const callDetails=()=>{
        onDetails(element);
    }

    return(
        <tr>
            <td>{element.id}</td>
            <td>{element.flightNumber}</td>
            <td>
                <button onClick={handleUpdate} >Actualizar</button>
                <button onClick={callDelete}>Eliminar</button>
                <button onClick={callDetails}>Ver detalles</button>
            </td>
        </tr>
    )
}


export default TableRowVuelos;