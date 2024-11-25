import React from "react";

const MenuHelp=()=>{

    return(
        <>
            <section>
                <label for="opcionesViaje">Antes de tu viaje</label>
                <select id="opcionesViaje" name="opcionesViaje">
                    <option value="Requisitosparaviajar">Requisitos para viajar</option>
                    <option value="Nuestrasalianzas">Nuestras alianzas</option>
                    <option value="Asientos">Asientos</option>
                    <option value="Comidasabordo">Comidas a bordo</option>
                </select>


                <label for="opcionesEquipaje">Equipaje</label>
                <select id="opcionesEquipaje" name="opcionesEquipaje">
                    <option value="Equipajedemano">Equipaje de mano</option>
                    <option value="Equipajedebodega">Equipaje de bodega</option>
                    <option value="Equipajeespecial">Equipaje especial</option>
                    <option value="Articulosrestringidos">Articulos restringidos</option>
                </select>


                <label for="opcionesReserva">Tu reserva</label>
                <select id="opcionesReserva" name="opcionesReserva">
                    <option value="Soportecontucompra">Soporte con tu compra</option>
                    <option value="Check-In">Check-In</option>
                    <option value="Cambios">Cambios</option>
                    <option value="Reembolsos">Reembolsos</option>
                </select>


                <label for="opcionesEspecial">Asistencia especial</label>
                <select id="opcionesEspecial" name="opcionesEspecial">
                    <option value="EquiposMedicos">Equipos medicos</option>
                    <option value="ServiciosEspeciales">Servicios especiales</option>
                </select>


                <label for="opcionesMascotas">Mascotas a bordo</label>
                <select id="opcionesMascotas" name="opcionesMascotas">
                    <option value="PerrosDeAsistencia">Perros de asistencia y mascotas</option>
                    <option value="DocumentosRequisitos">Documentos y requisitos</option>
                </select>


                <label for="opcionesKids">Volar con niños</label>
                <select id="opcionesKids" name="opcionesKids">
                    <option value="VuelaconKidYbebes">Vuela con niños y bebes</option>
                    <option value="MujereEmbarazadas">Mujeres en estado de embarazo</option>
                </select>
            </section>


            <p>¿Fue util este articulo?</p>
            <button><figure></figure></button>
            <button><figure></figure></button>
        
        </>
    )
}

export default MenuHelp;