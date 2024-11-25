import React from "react";


const UsuarioDelete =({element,onDelete,onClose})=>{


    const callDelete=()=>{
        onDelete(element.id)
    }

    const callClose=()=>{
        onClose();
    }

    return(
        <>
            <figure></figure>
            <h3>Seguro que quiere eliminar al usuario {}</h3>
            <button onClick={callDelete} >Confirmar</button>
            <button onClick={callClose} >Cancelar</button>
        </>
    );
}


export default UsuarioDelete;