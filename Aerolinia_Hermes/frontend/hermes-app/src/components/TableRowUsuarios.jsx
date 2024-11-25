import React from "react";
import { Link, useNavigate } from "react-router-dom";

const TableRowUsuarios = ({element,onDelete}) =>{

    const navigate = useNavigate();
  
    const handleUpdate =()=>{
        navigate(`/updateUser/${element.id}`);
    }

    const callDelete=()=>{
        onDelete(element);
    }
    return(
        <tr>
            <td>{element.id}</td>
            <td>{element.name}</td>
            <td>{element.lastname}</td>
            <td>{element.email}</td>
            <td>{element.bookings.length>0? element.bookings.length :"No tiene reservas"}</td>
            <td>
                
                <button onClick={handleUpdate}>Actualizar</button>
                <button onClick={callDelete}>Eliminar</button>
            </td>
        </tr>
    )

}


export default TableRowUsuarios;