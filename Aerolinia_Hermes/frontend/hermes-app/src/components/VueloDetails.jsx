import React from "react";

const VueloDetails =({element,onClose})=>{
    
    const callClose=()=>{
        onClose();
    }

    return (
        <>
                <figure></figure>
                    <h3>{element.flightNumber}</h3>
                    <button onClick={callClose}>cerrar</button>
                    <div>
                    <figure></figure>
                    <p>{element.departureDate}</p>
                    
                    <figure></figure>
                    <p>{element.departureAirportCode}</p>
                    
                    <figure></figure>
                    <p>{element.departureAirportName}</p>
                    
                    <figure></figure>
                    <p>{element.departureCity}</p>
                    </div>

                    <div>
                        <figure></figure>
                        <p>{element.arrivalDate}</p>
                        
                        <figure></figure>
                        <p>{element.arrivalAirportCode}</p>
                        
                        <figure></figure>
                        <p>{element.arrivalAirPortName}</p>
                        
                        <figure></figure>
                        <p>{element.arrivalCity}</p>
                    </div>

                    <div>

                        <figure></figure>
                        <p>{element.ticketPrice}</p>

                        <figure></figure>
                        <p>{element.ticketCurrency}</p>

                        <p>Capacidad: {element.seatCapacity}</p>
                        <p>Numero de reservas:{
                                element?.bookings?.length>0? element.bookings.length :"No tinene reservas"
                            }</p>
                    </div>

        </>
    )
}


export default VueloDetails;