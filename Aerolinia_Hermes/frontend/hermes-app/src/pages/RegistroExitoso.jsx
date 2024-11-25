import React from "react";
import {  Link } from "react-router-dom";

const RegistroExitoso=()=>{
    
    return(
        <>
            <figure></figure>
            <p>El usuario ha sido registrado con exito, por favor
            de click en el siguiente enlace para ser enviado al</p>
            <Link to="/login">Login de usuarios</Link>
        </>
    )
}

export default RegistroExitoso;