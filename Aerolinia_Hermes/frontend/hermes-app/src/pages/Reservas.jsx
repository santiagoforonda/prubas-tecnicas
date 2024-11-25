import React, { useEffect, useState } from "react";
import TableRowReservas from "../components/TableRowReservas";
import ReservasDelete from "../components/ReservasDelete";

const busquedaDefault={
    usuario:"",
    vuelo:0,
    estado:"",
    id:0
}

const Reservas=()=>{

    const[reservas,setReservas] =useState([]);
    const[busqueda,setBusqueda] = useState(busquedaDefault);
    const token = localStorage.getItem("tokenauth");
    useEffect(()=>{
        const listaReservas = async() =>{
            try{
                const response = await fetch("http://localhost:8080/api/v1/booking",{
                    method:"GET",
                    headers:{
                        "Content-Type":"application/json",
                        Authorization:`Bearer ${token}`,
                    }
                });
                if(!response.ok){
                    //condicional para imprimir en consola el mensaje de error que manda el backend en caso de error
                    const errorData = await response.json();
                    console.error("Error en la respuesta",errorData);
                    return;
                }
                const lista = await response.json();
                const newListaReservas = lista.map(r=>({
                    "id":r.id,
                    "paymentToken": r.paymentToken,
                    "checkInd": r.checkInd,
                    "createdAt": r.createdAt,
                    "referenceCode": r.referenceCode,
                    "status": r.status

                }));
                setReservas(newListaReservas);
            }catch(error){
                console.error(error);
            }
        }
        listaReservas();
    },[]);

    const handlesearch =async(busqueda)=>{
        let url="";
        let resultado;

        if(busqueda.usuario !==""){
            url =`http://localhost:8080/api/v1/bookingByUser?user=${busqueda.usuario}`;
        }

        if(busqueda.vuelo !==0){
            url=`http://localhost:8080/api/v1/bookingByfligth/${busqueda.vuelo}`;
        }

        if(busqueda.id!==0){
            url =`http://localhost:8080/api/v1/booking/${busqueda.id}`;
        }

        if(busqueda.estado!==""){
            url=`http://localhost:8080/api/v1/bookingByStatus?status=${busqueda.estado}`;
        }

        if(busqueda.estado!=="" && busqueda.usuario!==""){
            url=`http://localhost:8080/api/v1/bookingBystatusAndUser?user=${busqueda.usuario}.com&status=${busqueda.estado}`;
        }

        try{
            const response = await fetch(url,{
                method:"GET",
                headers:{
                    "Content-Type":"application/json",
                    Authorization:`Bearer ${token}`,
                }
            });
            if(!response.ok){
                //condicional para imprimir en consola el mensaje de error que manda el backend en caso de error
                const errorData = await response.json();
                console.error("Error en la respuesta",errorData);
                setReservas([]);
                return;
            }
            resultado = await response.json();
            if(!resultado || resultado.length===0){
                setReservas([]);
                return;
            }
            setReservas(Array.isArray(resultado)?resultado:[resultado]);
            
        }catch(error){
            console.error(error);
        }

    }

    const handleSubmit=(evento)=>{
        evento.preventDefault();
        handlesearch(busqueda);
    }

    const handleChange=(evento)=>{
        const {name,value} =evento.target;
        setBusqueda({...busqueda,[name]:value});
    }

    const handleDelete=async(id)=>{
        try{
            const response = await fetch(`http://localhost:8080/api/v1/booking/${id}`,{
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
            window.alert("La reserva ha sido eliminada");
            setMessage(false);
        }catch(error){
            console.log(error)
        }
    }

    const [message,setMessage]=useState(false);
    const [reservaToDelete,setReservaToDelete]=useState({});
    const handleDeleteMessage=(element)=>{
        setMessage(true);
        setReservaToDelete(element);
    }

    const handleCloseMessage=()=>{
        setMessage(!message);
    }

    return(
        <>
            <figure></figure>
            <h2>Administracion de reservas</h2>

            <form onSubmit={handleSubmit}>
                <input  onChange={handleChange} name="usuario" value={busqueda.usuario}  type="email" placeholder="Usuario" pattern="^[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}$"></input>
                <input onChange={handleChange} name="estado" value={busqueda.estado} type="text" placeholder="Estado" pattern="^[A-Za-z]{8,12}$" ></input>
                <input onChange={handleChange} name="vuelo" value={busqueda.vuelo} type="number" placeholder="Vuelo" pattern="^\d+$" ></input>
                <input onChange={handleChange} name="id" value={busqueda.id} type="number" placeholder="Id de la reserva" pattern="^\d+$" ></input>
                <input type="submit" value="Buscar"></input>
            </form>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>ESTADO</th>
                        <th>CODIGO DE REFERENCIA</th>
                        <th>Check-In</th>
                        <th>Fecha de creacion</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                    {reservas.length>0?(
                        reservas.map((el)=>(
                            <TableRowReservas key={el.id} element={el} onDelete={handleDeleteMessage}></TableRowReservas>
                        ))
                    ):(<tr>
                        <td colSpan="6">Sin datos</td>
                    </tr>)}
                </tbody>
            </table>

            <dialog open={message} onClose={handleCloseMessage}>
                <ReservasDelete element={reservaToDelete} onClose={handleCloseMessage} onDelete={handleDelete} ></ReservasDelete>
            </dialog>
        </>
    )
}

export default Reservas;