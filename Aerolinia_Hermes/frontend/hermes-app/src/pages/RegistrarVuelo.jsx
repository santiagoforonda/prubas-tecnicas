import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const vueloDefault={
    id:0,
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
    seatCapacity: 0,
    bookings:[]
}

const RegistrarVuelo =()=>{

    const [isRegistred,setIsRegistred] = useState(true);
    const [vuelo,setVuelo]=useState(vueloDefault);
    const navigate = useNavigate();

    const token = localStorage.getItem("tokenauth");
    
    const handleAddVuelo = async(newVuelo) =>{
        const newVueloTosend = {
            "departureDate": newVuelo.departureDate ,
            "departureAirportCode": newVuelo.departureAirportCode ,
            "departureAirportName": newVuelo.departureAirportName,
            "departureCity": newVuelo.departureCity ,
            "departureLocale":  newVuelo.departureLocale,
            "arrivalDate": newVuelo.arrivalDate ,
            "arrivalAirportCode": newVuelo.arrivalAirportCode ,
            "arrivalAirPortName": newVuelo.arrivalAirPortName ,
            "arrivalCity": newVuelo.arrivalCity ,
            "arrivalLocale": newVuelo.arrivalLocale,
            "ticketPrice": newVuelo.ticketPrice,
            "ticketCurrency": newVuelo.ticketCurrency ,
            "flightNumber": newVuelo.flightNumber,
            "seatCapacity": newVuelo.seatCapacity,
        };
        try{
            const response = await fetch("http://localhost:8080/api/v1/catalog/flights",{
                method:"POST",
                headers:{
                    "Content-Type":"application/json",
                    Authorization:`Bearer ${token}`,
                },
                body:JSON.stringify(newVueloTosend)
            });//Aqui termina la funcion fetch

            if(!response.ok){
                const errorData = await response.json();
                console.error("Error en la respuesta", errorData);
                return;
            }
            const data=await response.json();
            
            const newVuelo = {
                id:data.id,
                departureDate: data.departureDate,
                departureAirportCode: data.departureAirportCode,
                departureAirportName: data.departureAirportName,
                departureCity: data.departureCity,
                departureLocale: data.departureLocale,
                arrivalDate: data.arrivalDate,
                arrivalAirportCode: data.arrivalAirportCode,
                arrivalAirPortName: data.arrivalAirPortName,
                arrivalCity: data.arrivalCity,
                arrivalLocale: data.arrivalLocale,
                ticketPrice: data.ticketPrice,
                ticketCurrency: data.ticketCurrency,
                flightNumber: data.flightNumber,
                seatCapacity: data.seatCapacity,
                bookings:data.bookings
            }

            setVuelo((vueloAux)=>{
                const updateVuelo = [...vueloAux,newVuelo]
                return updateVuelo;
            })
            localStorage.setItem("vuelo",JSON.stringify(newVuelo));
            setIsRegistred(true);
            navigate("/catalogo");
        }catch(error){
            setIsRegistred(false);
            console.error("Error de ejecucion",error);
        }
    }

    const handleSubmit=(evento)=>{
        evento.preventDefault();
        handleAddVuelo(vuelo);
    }

    const handleChange =(evento)=>{
        const {name,value} = evento.target;
        setVuelo({...vuelo,[name]:value});
    }

    return(
        <>
        <article>
            <h2>Ingrese los datos del vuelo que sera registrado</h2>
            <figure></figure>
            <form onSubmit={handleSubmit}>

                <input onChange={handleChange} value={vuelo.departureDate} name="departureDate" type="date" placeholder="Fecha de despegue" required  ></input>
                <input onChange={handleChange} value={vuelo.departureAirportCode} name="departureAirportCode" type="text" placeholder="Codigo del aeropuerto de despegue" required pattern="^[A-Za-z]{3,8}$" ></input>
                <input onChange={handleChange} value={vuelo.departureAirportName} name="departureAirportName" type="text" placeholder="Nombre del aeropuerto de despegue" required pattern="^[A-Za-z\s]{8,50}$" ></input>
                <input onChange={handleChange} value={vuelo.departureCity} name="departureCity"  type="text" placeholder="Ciudad de despegue" required pattern="^[A-Za-z\s]{8,25}$" ></input>
                <input onChange={handleChange} value={vuelo.departureLocale} name="departureLocale" type="text" placeholder="Localidad de despegue" required pattern="^[A-Za-z]{2,5}$" ></input>

                <input onChange={handleChange} value={vuelo.arrivalDate} name="arrivalDate" type="date" placeholder="Fecha de llegada" required  ></input>
                <input onChange={handleChange} value={vuelo.arrivalAirportCode} name="arrivalAirportCode" type="text" placeholder="Codigo del aeropuerto de llegada" required pattern="^[A-Za-z]{3,8}$" ></input>
                <input onChange={handleChange} value={vuelo.arrivalAirPortName} name="arrivalAirPortName" type="text" placeholder="Nombre del aeropuerto de llegada" required pattern="^[A-Za-z\s]{8,50}$" ></input>
                <input onChange={handleChange} value={vuelo.arrivalCity} name="arrivalCity" type="text" placeholder="Ciudad de llegada" required pattern="^[A-Za-z\s]{8,25}$" ></input>
                <input onChange={handleChange} value={vuelo.arrivalLocale} name="arrivalLocale" type="text" placeholder="Localidad de llegada" required pattern="^[A-Za-z]{2,5}$" ></input>


                <input onChange={handleChange} value={vuelo.ticketPrice} name="ticketPrice" type="number" placeholder="Precio del tiquete" required pattern="^\d+$" ></input>
                <input onChange={handleChange} value={vuelo.ticketCurrency} name="ticketCurrency" type="text" placeholder="Moneda del tiquete" required pattern="^[A-Za-z]{3,8}$" ></input>
                <input onChange={handleChange} value={vuelo.flightNumber} name="flightNumber" type="number" placeholder="Numero del vuelo" required pattern="^\d+$" ></input>
                <input onChange={handleChange} value={vuelo.seatCapacity} name="seatCapacity" type="number" placeholder="Capacidad del vuelo" required pattern="^\d+$" ></input>

                <input type="submit"value="Registrar"></input>
                <Link to="/">
                    <button>Cancelar</button>
                </Link>
            </form>
            <h3>{isRegistred? "":"No se pudo registrar el vuelo"}</h3>
        </article>
    </>
    )
}


export default RegistrarVuelo;