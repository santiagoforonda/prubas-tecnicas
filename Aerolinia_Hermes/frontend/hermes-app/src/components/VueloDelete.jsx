import React from "react";


const VueloDelete =({element,onDelete,onClose})=>{

    const callDelete =()=>{
        onDelete(element.id);
    }

    const callClose=()=>{
        onClose();
    }

    return(
        <>
        <figure></figure>
            <h3>¿Seguro que quiere eliminar el vuelo {element.flightNumber}?</h3>
            <button onClick={callDelete} >Confirmar</button>
            <button onClick={callClose}>Cancelar</button>
        </>
    );
}

export default VueloDelete;