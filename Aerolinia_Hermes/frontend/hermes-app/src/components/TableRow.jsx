import React from "react";
import {  useNavigate } from "react-router-dom";

const TableRow = ({element})=>{

    const navigate = useNavigate();
    const handleClick=()=>{
        navigate(`/registrarreserva/${element.departureCity}/${element.arrivalCity}/${element.id}`)
    }

    return(
            <div>
                <p>{element.departureDate}</p>
                <p>{element.departureLocale}</p>
                <hr></hr>
                <figure></figure>
                <p>{element.arrivalDate}</p>
                <p>{element.arrivalLocale}</p>
                <hr></hr>
                <p>{element.ticketPrice}</p>
                <p>{element.ticketCurrency}</p>
          
                <button onClick={handleClick}>Registrar reserva</button>
         
            </div>
                
       
    )
}

export default TableRow;