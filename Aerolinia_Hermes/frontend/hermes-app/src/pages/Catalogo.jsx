import React, { useEffect, useState } from "react";
import TableRowVuelos from "../components/TableRowVuelos";
import { Link } from "react-router-dom";
import VueloDetails from "../components/VueloDetails";
import VueloDelete from "../components/VueloDelete";


let searchDefault ={
    departureAirportCode:"",
    departureDate:""
}
const Catalogo = ()=>{

    const token = localStorage.getItem("tokenauth");
    const [vuelos,setVuelos]=useState([]);
    const [vuelo,setVuelo] = useState({});
    const [search,setSearch] = useState(searchDefault);

    
    useEffect(()=>{
        const handleGetData = async ()=>{
                    try{
                        const response=await fetch("http://localhost:8080/api/v1/catalog/flightsAll",{
                            method:"GET",
                            headers:{
                                "Content-Type":"application/json",
                                Authorization:`Bearer ${token}`
                            }
                        });

                        if (!response.ok) {
                            // Manejo de error si el backend retorna un estado HTTP de error
                            const errorData = await response.json();
                            console.error("Error en la respuesta:", errorData);
                            return;
                        }
                        
                        const data = await response.json();

                        if (!data || data.length === 0) {
                            console.warn("No se encontraron vuelos.");
                            setVuelos([]); // Actualiza el estado con un array vacío
                            return;
                        }


                        const vuelosAux = data.map(v=>({
                            "id": v.id,
                            "departureDate":v.departureDate,
                            "departureAirportCode": v.departureAirportCode,
                            "departureAirportName": v.departureAirportName,
                            "departureCity": v.departureCity,
                            "departureLocale": v.departureLocale,
                            "arrivalDate": v.arrivalDate,
                            "arrivalAirportCode": v.arrivalAirportCode,
                            "arrivalAirPortName": v.arrivalAirPortName,
                            "arrivalCity": v.arrivalCity,
                            "arrivalLocale": v.arrivalLocale,
                            "ticketPrice": v.ticketPrice,
                            "ticketCurrency": v.ticketCurrency,
                            "flightNumber": v.flightNumber,
                            "seatCapacity": v.seatCapacity,
                            "bookings":v.bookings
                        }))
                        setVuelos(vuelosAux);
                    }catch(error){
                        console.error(error);
                    }  
        }
        handleGetData();
    },[]);
    
   
    const handleDelete =async(id)=>{
        try{
            const response = await fetch(`http://localhost:8080/api/v1/catalog/flight/${id}`,{
                method:"DELETE",
                headers:{
                    "Content-Type":"application/json",
                    Authorization:`Bearer ${token}`
                }
            });
            if(!response.ok){
                const erroData = await response.json();
                console.error("Error en la respuesta", erroData);
                return;
            }
            setMessage(false);
            window.alert("El vuelo ha sido eliminado");
            localStorage.removeItem("vuelo");
        }catch(error){
            console.error(error);
        }
    }

    const handleGetBySearch= async(search)=>{
        try{
            const response = await fetch(`http://localhost:8080/api/v1/catalog/fligth/${search.departureAirportCode}/?departureDate=${search.departureDate}`,{
                method:"GET",
                headers:{
                    "Content-Type":"application/json",
                    Authorization:`Bearer ${token}`
                }
            });
            if(!response.ok){
                const errorData = await response.json();
                console.error("Error en la respuesta", errorData);
                setVuelos([]);
                return;
            }
            const data = await response.json();
            if(!data || data.length===0){
                setVuelos([]);
                return;
            }
            setVuelos(data);
        }catch(erro){
            console.error(erro);
        }    
    }

    const handleSubmit =(evento)=>{
        evento.preventDefault();
        handleGetBySearch(search);
        
    }

    const handleChange =(evento)=>{
        const {name,value} = evento.target;
        setSearch({...search,[name]:value});
    }

    const [isDetailsOpen,setIsDetailsOpen] = useState(false);

    const handleCloseDetails=()=>{
        setIsDetailsOpen(!isDetailsOpen);
    }

    const handleDetails =(element)=>{
        setIsDetailsOpen(true);
        setVuelo(element);
    }

    const [message,setMessage] = useState(false);
    


    const handleDeleteMessage=(element)=>{
        setMessage(true);
        setVuelo(element);
    }

    const handleCloseMessage =()=>{
        setMessage(!message);
    }

    return(
        <>
            <figure></figure>
            <h2>Administracion de vuelos</h2>
            <Link to="/registrarVuelo">
                <button>Registrar</button>
            </Link>
            
            <form onSubmit={handleSubmit}>
                <input onChange={handleChange} value={search.departureAirportCode} name="departureAirportCode" type="text" placeholder="Codigo del aeropuerto de despegue" pattern="^[A-Za-z]{3,8}$"></input>
                <input onChange={handleChange} value={search.departureDate} name="departureDate" type="date" placeholder="Fecha"></input>
                <input type="submit" value="Buscar"></input>
            </form>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>FLIGTH NUMBER</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                    {vuelos.length>0?(
                        vuelos.map((el)=>(<TableRowVuelos key={el.id} element={el} onDelete={handleDeleteMessage} onDetails={handleDetails}></TableRowVuelos>))
                    ):(<tr>
                        <td colSpan="3">Sin datos</td>
                    </tr>)}
                </tbody>
            </table>
            <dialog open={isDetailsOpen} onClose={handleCloseDetails}>
                <VueloDetails element={vuelo} onClose={handleCloseDetails}></VueloDetails>
            </dialog>

            <dialog open={message} onClose={handleCloseMessage}>
                <VueloDelete element={vuelo} onClose={handleCloseMessage} onDelete={handleDelete}></VueloDelete>
            </dialog>
        </>
    )

}

export default Catalogo;