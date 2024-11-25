import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";


const reservaDefault ={
    paymentToken: "",
    checkInd: false,
    createdAt: "",
    referenceCode: "",
    status: ""
}

const UpdateReserva = () =>{

    const {id} = useParams();

    const idReserva = Number(id);
    const user = JSON.parse(localStorage.getItem("user"));
    const vuelo = JSON.parse(localStorage.getItem("vuelo"));
    const token = localStorage.getItem("tokenauth");

    const [reservaUpdate,setReservaUpdate] = useState(reservaDefault);
    const [isUpdate,setIsUpdate] = useState(true);
    const navigate = useNavigate();


    const handleUpdateReserva =async (reserva)=>{
        const reservaToSend ={
            "paymentToken": reserva.paymentToken,
            "checkInd": reserva.checkInd,
            "createdAt": reserva.createdAt,
            "referenceCode": reserva.referenceCode,
            "status": reserva.status
        };

        try{
            const response = await fetch(`http://localhost:8080/api/v1/booking/${idReserva}/user/${user.id}/flight/${vuelo.id}`,{
                method:"PUT",
                headers:{
                    "Content-Type":"application/json",
                    Authorization:`Bearer ${token}`
                },
                body:JSON.stringify(reservaToSend)
            });
            if(!response.ok){
                const errorData = await response.json();
                console.error("Error en la respuesta", errorData);
                setIsUpdate(false);
                return;
            }
            navigate("/reservas");
        }catch(error){
            setIsUpdate(false);
            console.error(error);
        }
    }


    const handleSubmit=(evento)=>{
        evento.preventDefault();
        handleUpdateReserva(reservaUpdate);
    }

    const handleChange=(evento)=>{
        const { name, value, type, checked } = evento.target;

 
        const parsedValue = type === "radio" && (value === "true" || value === "false")
            ? value === "true"
            : value;

            setReservaUpdate({
            ...reservaUpdate,
            [name]: parsedValue,
        });
    }

    return(
        <>
            <section>
            <figure></figure>
            <h1>Actualice su reserva</h1>
            </section>

            <form onSubmit={handleSubmit}>
                <input onChange={handleChange}  name="paymentToken" value={reservaUpdate.paymentToken} type="text" placeholder="Token de pago" pattern="^[A-Za-Z0-9]{10,18}$"  ></input>
                <input onChange={handleChange} name="createdAt" value={reservaUpdate.createdAt} type="date" placeholder="Fecha"></input>
                <input onChange={handleChange} name="referenceCode" value={reservaUpdate.referenceCode} type="text" placeholder="Codigo de referencia" pattern="^[A-Za-Z0-9]{10,18}$"></input>
                
                <p>Check-In</p>
                <label>
                    Realizado
                    <input 
                    id="realizada" 
                    type="radio" 
                    onChange={handleChange} 
                    name="checkInd" 
                    value={true} 
                    checked={reservaUpdate.checkInd === true} 
                    />
                </label>
                <label>
                    No realizado
                    <input 
                    id="no_realizado" 
                    type="radio" 
                    onChange={handleChange} 
                    name="checkInd" 
                    value={false} 
                    checked={reservaUpdate.checkInd === false} 
                    />
                </label>

                <p>Estado</p>
                <label>
                    Confirmada
                    <input 
                    id="confirmada" 
                    type="radio" 
                    onChange={handleChange} 
                    name="status" 
                    value="CONFIRMED" 
                    checked={reservaUpdate.status === "CONFIRMED"} 
                    />
                </label>
                <label>
                    Sin confirmar
                    <input 
                    id="sin_confirmar" 
                    type="radio" 
                    onChange={handleChange} 
                    name="status" 
                    value="UNCONFIRMED" 
                    checked={reservaUpdate.status === "UNCONFIRMED"} 
                    />
                </label>
                <label>
                    Cancelada
                    <input 
                    id="cancelada" 
                    type="radio" 
                    onChange={handleChange} 
                    name="status" 
                    value="CANCELLED" 
                    checked={reservaUpdate.status === "CANCELLED"} 
                    />
                </label>
                

                <input type="submit" value="Actualizar"></input>
                <button>Cancelar</button>
            </form>
            <h3>{isUpdate ? "":"No se pudo actualiar la reserva"}</h3>
        </>
    );
}

export default UpdateReserva;