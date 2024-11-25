import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";


const loginUserDefault={
    email:"",
    password:""
}

const Login=()=>{
    const [token,setToken] = useState("");
    const [loginUser,setLoginUser] = useState(loginUserDefault);

    const navigate = useNavigate();

    const handleLoginUser = async (loginUser)=>{
        const loginUserTosend = {
            "email":loginUser.email,
            "password":loginUser.password
        };

        try{
            const response = await fetch("http://localhost:8080/auth/login",
                {
                    method:"POST",
                    headers:{
                        "Content-Type":"application/json"
                    },
                    body:JSON.stringify(loginUserTosend)
                }
            );
            const data = await response.json();
            const newtoken = data.token;
            setToken((tokenAux)=>{
                const updateToken = [...tokenAux,newtoken];
                return updateToken;
            })
            localStorage.setItem("tokenauth",newtoken);
            console.log(newtoken);
            navigate("/");
        }catch(error){
            console.error(error)
        }
    }

    const handleSubmit =(evento)=>{
        evento.preventDefault();
        handleLoginUser(loginUser);
    }

    const handleChange = (evento)=>{
        const {name,value} = evento.target;
        setLoginUser({...loginUser,[name]:value});
    }
    
    return(
        <>
            <figure></figure>
            <p>Inicia sesion en nuestro sistema para tener acceso a todas las ofertas y administracion</p>
            <form onSubmit={handleSubmit}>
                <input onChange={handleChange} name="email" value={loginUser.email} type="email" pattern="^[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}$"  placeholder="Email" required ></input>
                <input  onChange={handleChange} name="password" value={loginUser.password} type="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"  placeholder="password" required></input>
                <input type="submit" value="Login"></input>
                <Link to="/">
                <button>Cancelar</button>
                </Link>
            </form>        
        
        </>
    );
}

export default Login;