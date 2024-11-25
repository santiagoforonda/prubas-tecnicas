import React from "react";

const ReservasDelete=({element,onDelete,onClose})=>{
    
    const callDelete =()=>{
        onDelete(element.id);
    }


    const callClose=()=>{
        onClose();
    }

    
    return(
        <>
            <figure></figure>
            <h3>¿Seguro que quieres eliminar la reserva?</h3>
            <button onClick={callDelete} >Confirmar</button>
            <button onClick={callClose} >Cnacelar</button>
        
        </>
    )
}

export default ReservasDelete;