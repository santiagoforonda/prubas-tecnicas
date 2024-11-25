import React, { useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

const reservaDefault = {
    id:0,
    paymentToken:"",
    createdAt:"",
    referenceCode:"",
    estatus:"",
    checkInd:false
}

const RegistrarReserva =()=>{
    const {origen,destino,vueloid} = useParams();
    
    const idVuelo = Number(vueloid);
    const [reserva,setReserva] = useState(reservaDefault);
    const [isRegistred,setIsRegistred] = useState(true);
    const navigate = useNavigate();
    const token = localStorage.getItem("tokenauth");
    const user = JSON.parse(localStorage.getItem("user"));
   
    const addReserva = async(newReserva)=>{
        const newReservaToSend = {
            "paymentToken":newReserva.paymentToken,
            "createdAt":newReserva.createdAt,
            "referenceCode":newReserva.referenceCode,
        };

        try{
            const response = await fetch(`http://localhost:8080/api/v1/booking/user/${user.id}/flight/${idVuelo}`,{
                method:"POST",
                headers:{
                    "Content-Type":"application/json",
                    Authorization:`Bearer ${token}`,
                },
                body:JSON.stringify(newReservaToSend)
            });
            const data = await response.json();
            const newReserva = {
                id:data.id,
                paymentToken:data.paymentToken,
                createdAt:data.createdAt,
                referenceCode:data.referenceCode,
                estatus:data.estatus,
                checkInd:data.checkInd
            }

            setReserva((reservaAux=>{
                const updateReserva = [...reservaAux,newReserva];
                return updateReserva;
            }))
            navigate("/reservas");
            setIsRegistred(true);
        }catch(error){
            console.error(error);
            setIsRegistred(false);
        }
    }

    const handleSubmit = (evento)=>{
        evento.preventDefault();
        addReserva(reserva);
    }

    const handleChange = (evento)=>{
        const {name,value} = evento.target;
        setReserva({...reserva,[name]:value});
    }
    return(
        <>
            <figure></figure>
            <h3>Registre su reserva</h3>
            <p>{origen}</p>
            <figure></figure>
            <p>{destino}</p>


            <form onSubmit={handleSubmit}>
                <input onChange={handleChange} name="paymentToken" value={reserva.paymentToken} type="text" placeholder="Token de pago" required pattern="^[A-Za-Z0-9]{10,18}"></input>
                <input onChange={handleChange} name="createdAt" value={reserva.createdAt} type="date" placeholder="Fecha" required></input>
                <input  onChange={handleChange} name="referenceCode" value={reserva.referenceCode} type="text" placeholder="Codigo de referencia" required pattern="^[A-Za-Z0-9]{10,18}"></input>
                <input type="submit" value="Registrar"></input>
                <Link to="/">
                <button>Cancelar</button>
                </Link>
            </form>
            <h3>{isRegistred? "" :"No se ha podido realizar el registro"}</h3>
        
        </>

    )
}

export default RegistrarReserva;