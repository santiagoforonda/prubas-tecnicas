import React from "react";
import TareaRow from "./TareaRow.js";

const TareaList = ({tareas}) =>{
    return(
        <div>
            {
                tareas.map((tarea) => (<TareaRow key={tarea.id} tarea={tarea} tareas={tareas}></TareaRow>))
            }
        </div>
    );
}

export default TareaList;