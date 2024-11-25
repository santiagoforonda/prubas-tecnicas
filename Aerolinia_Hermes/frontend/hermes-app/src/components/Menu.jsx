import React from "react";
import { Link } from "react-router-dom";
const Menu = () =>{

    return(

        <nav className="menu">
                    <Link to="/">HOME</Link>
                    <Link to="/catalogo">CATALOGO</Link>
                    <Link to="/reservas">RESERVAS</Link>
                    <Link to="/profile">PROFILE</Link>
                </nav>
    )
}

export default Menu;