import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import TableRow from "../components/TableRow";

const ListaVuelos=()=>{
    const {origen,destino,fecha} = useParams();
    const [vuelos,setVuelos] = useState([]);
    const token = localStorage.getItem("tokenauth");
    useEffect(()=>{
        const vuelosFetch = async ()=>{
            try{
                const response = await fetch(`http://localhost:8080/api/v1/catalog/flights/?departureAirPortCode=${origen}&arrivalAirPortCode=${destino}&departureDate=${fecha}`,{
                    method:"GET",
                    headers:{
                        "Content-Type":"application/json",
                        Authorization:`Bearer ${token}`,
                    }
                });
                const data = await response.json();
                setVuelos(data);
            }catch(error){
                console.error(error);
            }
        }
        vuelosFetch();
    },[]);

    return(
        <>
            <article>
                <figure></figure>
                <h3>{origen}</h3>
                <figure></figure>
                <h3>{destino}</h3>
                <section>
                    {vuelos.length>0?
                        (vuelos.map((elemento)=>(
                            <TableRow
                                key={elemento.id}
                                element={elemento}
                            ></TableRow>))):
                            (
                                <h3>Sin datos</h3>
                            )    
                    }
                </section>
                        
                <Link to="/catalogo"><button>Adminsitrar vuelos</button></Link>
                <Link to="/reservas"><button>Administrar reservas</button></Link>
                
            </article>
        </>
    )
}

export default ListaVuelos;
