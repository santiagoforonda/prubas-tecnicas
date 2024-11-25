import React from "react";
import Home from "../pages/Home";
import {BrowserRouter,Routes,Route} from "react-router-dom";
import Catalogo from "../pages/Catalogo";
import Reservas from "../pages/Reservas";
import Profile from "../pages/Profile";
import Menu from "./Menu";
import UpdateReserva from "../pages/UpdateReserva";
import UpdateUsuario from "../pages/UpdateUsuario";
import CheckInOnline from "../pages/CheckInOnline";
import EquipajeInfo from "../pages/EquipajeInfo";
import UpdateVuelo from "../pages/UpdateVuelo";
import RegistrarVuelo from "../pages/RegistrarVuelo";
import CenterHelp from "../pages/CenterHelp";
import ListaVuelos from "../pages/ListaVuelos";
import Login from "../pages/Login";
import MenuHelp from "../pages/MenuHelp";
import RegistrarUsuario from "../pages/RegistrarUsuario";
import RegistrarReserva from "../pages/RegistrarReserva";
import RegistroExitoso from "../pages/RegistroExitoso";
import Servicios from "../pages/Servicios";
import Error404 from "../pages/Error404";
import logo from "../assets/Logo.png";

/*Las imagenes hay que importarlas en proyectos de React */
const Header = () =>{
    return <header>
        <figure>
            <img src={logo} alt="Logo del aeropuerto"></img>
        </figure>
        <h3>AEROLINA HERMES</h3>
        <BrowserRouter>
                <Menu></Menu>
                <Routes>
                    <Route path="/" element={<Home></Home>}></Route>
                    <Route path="/catalogo" element={<Catalogo></Catalogo>}></Route>
                    <Route path="/reservas" element={<Reservas></Reservas>}></Route>
                    <Route path="/profile" element={<Profile></Profile>}></Route>
                    <Route path="/updateReserva/:id" element={<UpdateReserva></UpdateReserva>}></Route>
                    <Route path="/updateUser/:id" element={<UpdateUsuario></UpdateUsuario>}></Route>
                    <Route path="/checkinonline" element={<CheckInOnline></CheckInOnline>}></Route>
                    <Route path="/equipaje" element={<EquipajeInfo></EquipajeInfo>}></Route>
                    <Route path="/updateVuelo/:id" element={<UpdateVuelo></UpdateVuelo>}></Route>
                    <Route path="/registrarVuelo" element={<RegistrarVuelo></RegistrarVuelo>}></Route>
                    <Route path="/centerhelp" element={<CenterHelp></CenterHelp>}></Route>
                    <Route path="/vuelos/:origen/:destino/:fecha" element={<ListaVuelos></ListaVuelos>}></Route>
                    <Route path="/login" element={<Login></Login>}></Route>
                    <Route path="/menuhelp" element={<MenuHelp></MenuHelp>}></Route>
                    <Route path="/registrousuario" element={<RegistrarUsuario></RegistrarUsuario>}></Route>
                    <Route path="/registrarreserva/:origen/:destino/:vueloid" element={<RegistrarReserva></RegistrarReserva>}></Route>
                    <Route path="/registroexitoso" element={<RegistroExitoso></RegistroExitoso>}></Route>
                    <Route path="/servicios" element={<Servicios></Servicios>}></Route>
                    <Route path="*" element={<Error404></Error404>}></Route>
                </Routes>
            </BrowserRouter>
    </header>
}

export default Header;