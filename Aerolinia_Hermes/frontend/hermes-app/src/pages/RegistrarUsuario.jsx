import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Loader from "../components/Loader";

const userDefault = {
    id:0,
    name:"",
    lastname:"",
    email:"",
    password:""
}
const RegistrarUsuario = () =>{

    const [derechos,setDerechos] = useState(false);
    const [user,setUser] = useState(userDefault);
    const [loading,setLoading]=useState(false);
    const [exito,setExito]= useState(true);
    const navigate = useNavigate();
    
    const handleAddUser = async (newUser) =>{
        const newUserToSend = {
            "name":newUser.name,
            "lastname":newUser.lastname,
            "email":newUser.email,
            "password":newUser.password
        };

        try{
            const response = await fetch("http://localhost:8080/auth/register",
                {
                    method:"POST",
                    headers:{
                        "Content-Type":"application/json",
                    },
                    body: JSON.stringify(newUserToSend)
                }
            );
            const data = await response.json();
            
            const newUser = {
                id:data.id,
                name:data.name,
                lastname:data.lastname,
                email:data.email,
                password:data.password
            };

            setUser((userAux)=>{
                const updateUser = [...userAux,newUser];
                
                return updateUser;
            })
            localStorage.setItem("user",JSON.stringify(newUser));
            setLoading(false);
        }catch(error){
            setExito(false);
            console.error(error);
        }
    };

    const handleSubmit =(evento) =>{
        
        if(derechos){
            evento.preventDefault();
            setLoading(true);
            handleAddUser(user);
            if(exito){
                navigate("/registroexitoso");    
            }
        }
    }

    const handleChange=(evento)=>{
        const {name,value} = evento.target;
        setUser({...user,[name]:value});
    }

    const handleCheck=(evento)=>{
        let isChecked = evento.target.checked;
        setDerechos(isChecked);
    }

    return(

        <article>
            <figure></figure>
            <h3>Complete los campos para legalizar el registro de usuario</h3>
            <form onSubmit={handleSubmit}> 
                <input onChange={handleChange} name="name" value={user.name} type="text" placeholder="Nombre" required pattern="^[A-Za-zÑñÁáÉéÍíÓóÚúÜü\s]{2,15}$" ></input>
                <input onChange={handleChange} name="lastname" value={user.lastname} type="text" placeholder="Apellido" required  pattern="^[A-Za-zÑñÁáÉéÍíÓóÚúÜü\s]{3,25}$" ></input>
                <input onChange={handleChange} name="email" value={user.email} type="email" placeholder="Email" required pattern="^[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}$"></input>
                <input onChange={handleChange} name="password" value={user.password} type="password" placeholder="Password" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"></input>
                <label htmlFor="derechos">
                <input type="checkbox" checked={derechos}id="derechos"  onChange={handleCheck}></input>
                Acepto el uso de datos personales
                </label>
                <p>
                    {derechos? "Derechos aceptados" : ""}
                </p>
                
                <input type="submit" value="Registrar"></input>
                <Link to="/">
                <button>Cancelar</button>
                </Link>
            </form>
            {loading && <Loader></Loader>}
            <h3>{exito? "":"No se pudo regitrar al usuario"}</h3>
        </article>
    )

}

export default RegistrarUsuario;