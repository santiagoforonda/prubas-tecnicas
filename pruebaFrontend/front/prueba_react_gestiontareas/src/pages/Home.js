import React from "react";
import Tarea from "../components/Tarea";
import '../estilosComponentes/home.css';

const Home = ()=>{


    return(

        <div>
            <h1 className="titulo"> Tabla de tareas </h1>
            <Tarea></Tarea>
        </div>
        

    );
}

export default Home;