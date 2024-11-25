import React, {useState} from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

const userDefault={
    name:"",
    lastname:"",
    email:"",
    password:""
}

const UpdateUsuario = ()=>{
    
    const {id}= useParams();

    const idUser = Number(id);
    const token = localStorage.getItem("tokenauth");
    const [userUpdate,setUserUpdate] = useState(userDefault);
    const [isUpdate,setIsUpdate] = useState(true);
    const navigate = useNavigate();
    
    

    const handleUpdateUser =async(updateUser)=>{
        const userTosend={
            "name":updateUser.name,
            "lastname":updateUser.lastname,
            "email":updateUser.email,
            "password":updateUser.password
        };

        try{
            const response = await fetch(`http://localhost:8080/api/v1/user/${idUser}`,{
                method:"PUT",
                headers:{
                    "Content-Type":"application/json",
                    Authorization:`Bearer ${token}`
                },
                body:JSON.stringify(userTosend)
            });

            if(!response.ok){
                const errorData = await response.json();
                console.error("Error en la respuesta", errorData);
                return;
            }
            setIsUpdate(true);
            navigate("/profile");
        }catch(error){
            setIsUpdate(false);
            console.error(error);
        }
    }

    const handleSubmit=(evento)=>{
        evento.preventDefault();
        handleUpdateUser(userUpdate);
    }

   const handleChange =(evento)=>{
    const{name,value} = evento.target;
    setUserUpdate({...userUpdate,[name]:value});
   }   
    
    return(

        <>
            <figure></figure>
            <h1>Complete los campos para actualizar el usuario</h1>
            <form onSubmit={handleSubmit}>
                <input  onChange={handleChange} name="name" value={userUpdate.name} type="text" placeholder="Nombre" pattern="^[A-Za-zÑñÁáÉéÍíÓóÚúÜü\s]{2,15}$"></input>
                <input  onChange={handleChange} name="lastname" value={userUpdate.lastname} type="text" placeholder="Apellido" pattern="^[A-Za-zÑñÁáÉéÍíÓóÚúÜü\s]{3,25}$" ></input>
                <input  onChange={handleChange} name="email" value={userUpdate.email} type="email" placeholder="Email" pattern="^[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}$" ></input>
                <input  onChange={handleChange} name="password" value={userUpdate.password} type="password" placeholder="Password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" ></input>

                <input type="submit" value="Actualizar"></input>
                <Link to="/profile">
                    <button>Cancear</button>
                </Link>
            </form>
            <h3>{isUpdate? "":"No se pudo actualizar el usuario"}</h3>
        </>
    )
}

export default UpdateUsuario;