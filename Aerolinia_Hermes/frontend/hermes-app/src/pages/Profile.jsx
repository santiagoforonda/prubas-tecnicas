import React, { useEffect, useState } from "react";
import TableRowUsuarios from "../components/TableRowUsuarios";
import UsuarioDelete from "../components/UsuarioDelete";

const Profile =()=>{

    const token = localStorage.getItem("tokenauth");
    const [users,setUsers]=useState([]);
    const [id,setId] = useState(0);
    useEffect(()=>{
        const getUsers = async()=>{
            try{
                const response = await fetch("http://localhost:8080/api/v1/getUsers",{
                    method:"GET",
                    headers:{
                        "Content-Type":"application/json",
                        Authorization:`Bearer ${token}`
                    }
                });
                const listaUsuario = await response.json();
                const newListaUsuarios = listaUsuario.map(u=>({
                    "id":u.id,
                    "name":u.name,
                    "lastname":u.lastname,
                    "email":u.email,
                    "bookings":u.bookings
                }))
                setUsers(newListaUsuarios);
            }catch(error){
                console.log(error);
            }
        }

        getUsers()
    },[])

    const getUserByid =async(id)=>{
        try{
            const response = await fetch(`http://localhost:8080/api/v1/user/${id}`,{
                method:"GET",
                headers:{
                    "Content-Type":"application/json",
                    Authorization:`Bearer ${token}`
                }
            });
            if(!response.ok){
                const errorData = await response.json();
                console.error("Error en la respuesta", errorData);
                setUsers([]);
                return;
            }

            const data = await response.json();
            setUsers([data]);
                
        }catch(error){
            console.error(error);
        }
    }

   const handleSubmit = (evento)=>{
        evento.preventDefault();
        getUserByid(id);
   }

   const handleChange=(evento)=>{
    const {value} = evento.target;
    setId(value);
   }

   const handleDelete = async(id)=>{
    try{
        const response = await fetch(`http://localhost:8080/api/v1/user/${id}`,{
            method:"DELETE",
            headers:{
                "Content-Type":"application/json",
                Authorization:`Bearer ${token}`
            }
        });

        if(!response.ok){
            const errorData = await response.json();
            console.error("Error en la respuesta", errorData);
            return;
        }
        localStorage.removeItem("user");
        localStorage.removeItem("tokenauth");
        setMessage(false);
        window.alert("El usuario ha sido eliminado");
    }catch(error){
        console.log(error);
    }
   }

   const [userToDelete,setUserToDelete] = useState({});
   const [message,setMessage] = useState(false);

   const handleMessageDelete =(usuario)=>{
        setUserToDelete(usuario);
        setMessage(true);
   }

   const handleCloseMessage=()=>{
    setMessage(!message);
   }

    return(
        <>
            <figure></figure>
            <h2>Administracion de usuarios</h2>

            <form onSubmit={handleSubmit}>
                <input onChange={handleChange} value={id} name="id" type="number" placeholder="ID del usuario" pattern="^\d+$"></input>
                <input type="submit" value="Buscar"></input>
            </form>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>LASTNAME</th>
                        <th>EMAIL</th>
                        <th>RESERVAS</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                    {users.length>0?(
                        users.map((el)=>(
                            <TableRowUsuarios key={el.id} element={el} onDelete={handleMessageDelete}></TableRowUsuarios>
                        ))
                    ):(<tr>
                        <td colSpan="6">Sin datos</td>
                    </tr>)}
                </tbody>
            </table>
            <dialog open={message} onClose={handleCloseMessage}>
                <UsuarioDelete element={userToDelete} onClose={handleCloseMessage} onDelete={handleDelete} ></UsuarioDelete>
            </dialog>
        </>
    )
}

export default Profile;