import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

const vueloDefault={
    departureDate: "",
    departureAirportCode: "",
    departureAirportName: "",
    departureCity: "",
    departureLocale: "",
    arrivalDate: "",
    arrivalAirportCode: "",
    arrivalAirPortName: "",
    arrivalCity: "",
    arrivalLocale: "",
    ticketPrice: 0,
    ticketCurrency: "",
    flightNumber: 0,
    seatCapacity: 0
}
const UpdateVuelo =()=>{
    
    const {id} = useParams();
    const idVuelo = Number(id);
    const token = localStorage.getItem("tokenauth");
    const [vueloUpdate,setVueloUpdate] = useState(vueloDefault);
    const [isUpdate,setIsUpdate] = useState(true);
    const navigate = useNavigate();

    const handleUpdateVuelo = async(updateVuelo)=>{
        const vueloTosend={
            "departureDate": updateVuelo.departureDate ,
        "departureAirportCode": updateVuelo.departureAirportCode ,
        "departureAirportName": updateVuelo.departureAirportName,
        "departureCity": updateVuelo.departureCity ,
        "departureLocale":  updateVuelo.departureLocale,
        "arrivalDate": updateVuelo.arrivalDate ,
        "arrivalAirportCode": updateVuelo.arrivalAirportCode ,
        "arrivalAirPortName": updateVuelo.arrivalAirPortName ,
        "arrivalCity": updateVuelo.arrivalCity ,
        "arrivalLocale": updateVuelo.arrivalLocale,
        "ticketPrice": updateVuelo.ticketPrice,
        "ticketCurrency": updateVuelo.ticketCurrency ,
        "flightNumber": updateVuelo.flightNumber,
        "seatCapacity": updateVuelo.seatCapacity
        };
        
        try{
            const response = await fetch(`http://localhost:8080/api/v1/catalog/flight/${idVuelo}`,{
                method:"PUT",
                headers:{
                    "Content-Type":"application/json",
                    Authorization:`Bearer ${token}`
                },
                body:JSON.stringify(vueloTosend)
            });

            if(!response.ok){
                const errorData = await response.json();
                console.error("Error en la respuesta", errorData);
                setIsUpdate(false);
                return;
            }
            setIsUpdate(true);
            navigate("/catalogo");
        }catch(error){
            setIsUpdate(false);
            console.error(error);
        }
    }

    const handleSubmit = (evento)=>{
        evento.preventDefault();
        handleUpdateVuelo(vueloUpdate);
    }

    const handleChange =(evento)=>{
        const {name,value} = evento.target;
        setVueloUpdate({...vueloUpdate,[name]:value});
    }
    
    return(
        <>
            <article>
                <h2>Ingrese los datos del vuelo que sera actualizado</h2>
                <figure></figure>
                <form onSubmit={handleSubmit}>

                    <input onChange={handleChange} value={vueloUpdate.departureDate} name="departureDate"  type="date" placeholder="Fecha de despegue"  ></input>
                    <input onChange={handleChange} value={vueloUpdate.departureAirportCode} name="departureAirportCode" type="text" placeholder="Codigo del aeropuerto de despegue" pattern="^[A-Za-z\s]{3,8}$" ></input>
                    <input onChange={handleChange} value={vueloUpdate.departureAirportName} name="departureAirportName" type="text" placeholder="Nombre del aeropuerto de despegue" pattern="^[A-Za-z\s]{4,50}$"></input>
                    <input onChange={handleChange} value={vueloUpdate.departureCity} name="departureCity" type="text" placeholder="Ciudad de despegue" pattern="^[A-Za-z\s]{8,25}$"  ></input>
                    <input onChange={handleChange} value={vueloUpdate.departureLocale} name="departureLocale" type="text" placeholder="Localidad de despegue" pattern="^[A-Za-z]{2,5}$"  ></input>

                    <input onChange={handleChange} value={vueloUpdate.arrivalDate} name="arrivalDate"  type="date" placeholder="Fecha de llegada"  ></input>
                    <input onChange={handleChange} value={vueloUpdate.arrivalAirportCode} name="arrivalAirportCode" type="text" placeholder="Codigo del aeropuerto de llegada" pattern="^[A-Za-z\s]{3,8}$" ></input>
                    <input onChange={handleChange} value={vueloUpdate.arrivalAirPortName} name="arrivalAirPortName" type="text" placeholder="Nombre del aeropuerto de llegada" pattern="^[A-Za-z\s]{4,50}$" ></input>
                    <input onChange={handleChange} value={vueloUpdate.arrivalCity} name="arrivalCity" type="text" placeholder="Ciudad de llegada" pattern="^[A-Za-z\s]{8,25}$"  ></input>
                    <input onChange={handleChange} value={vueloUpdate.arrivalLocale} name="arrivalLocale" type="text" placeholder="Localidad de llegada" pattern="^[A-Za-z]{2,5}$"  ></input>


                    <input onChange={handleChange} value={vueloUpdate.ticketPrice} name="ticketPrice" type="number" placeholder="Precio del tiquete" pattern="^\d+$" ></input>
                    <input onChange={handleChange} value={vueloUpdate.ticketCurrency} name="ticketCurrency" type="text" placeholder="Moneda del tiquete" pattern="^[A-Za-z]{3,8}$" ></input>
                    <input onChange={handleChange} value={vueloUpdate.flightNumber} name="flightNumber" type="number" placeholder="Numero del vuelo" pattern="^\d+$" ></input>
                    <input onChange={handleChange} value={vueloUpdate.seatCapacity} type="number" name="seatCapacity"  placeholder="Capacidad del vuelo" pattern="^\d+$" ></input>

                    <input type="submit"value="Actualizar"></input>
                    <Link to="/catalogo">
                    <button>Cancelar</button>
                    </Link>
                    <h3>{isUpdate?"":"No se pudo actualizar el vuelo"}</h3>
                    
                </form>
            </article>
        
        
        
        </>
    )
}


export default UpdateVuelo;