import React, { useEffect, useState } from "react";
import "../Styles/Home.css";
import { BrowserRouter, Link, useNavigate } from "react-router-dom";
import ListaVuelos from "./ListaVuelos";

const vueloDefaul={
    origen:"",
    destino:"",
    fecha:""
}

const Home = ()=>{

    const navigate = useNavigate();
    const [existToken,setExistToken] = useState(false);
    const [vuelo,setVuelo] = useState(vueloDefaul);
    const token = localStorage.getItem("tokenauth");

    useEffect(()=>{
        if(token!=undefined){
            setExistToken(true);
        }
    },[])


    const handleSubmit = ()=>{
        navigate(`/vuelos/${vuelo.origen}/${vuelo.destino}/${vuelo.fecha}`);
    }

    const handleChange = (evento)=>{
        const {name, value} = evento.target;
        setVuelo({...vuelo,[name]:value})
    }

    return(
        <>
            <form onSubmit={handleSubmit}>
                <input onChange={handleChange} value={vuelo.origen} name="origen" type="text" placeholder="ORIGEN" required pattern="^[A-Za-z]{3,8}$"></input>
                <input onChange={handleChange} value={vuelo.destino} name="destino" type="text" placeholder="DESTINO" required pattern="^[A-Za-z]{3,8}$"></input>
                <input onChange={handleChange} value={vuelo.fecha} name="fecha" type="date" placeholder="FECHA" required></input>
                <input type="submit" value="BUSCAR"></input>
            </form>
            <article>
                <section>
                    <figure></figure>
                    <p>Vuela ahora a nues destinos</p>
                    <p>hasta mayo del 2025</p>
                    <p>desde 80 USD</p>
                </section>

                <section>
                    <h2>OFERTAS DE VIAJES</h2>
                    <figure></figure>
                    <figure></figure>
                    <figure></figure>
                </section>

                <section>
                    <h2>PREPARATE PARA VIAJAR</h2>
                    <div>
                        <Link to="/centerhelp">
                        <button>
                        <figure></figure>
                        <p>Centro de ayuda</p>
                        <p>Busca y encuentra informacion util para resolver tus preguntas</p>
                        </button>
                        </Link>
                        
                    </div>

                    <div>
                        
                        <Link to="/checkinonline">
                            <button>
                            <figure></figure>
                            <p>Check-In Online</p>
                            <p>Obten tu pase de abordar y ahorra tiempo en el aeropuerto</p>
                            </button>
                        </Link>
                        
                    </div>
                </section>

                <section>
                    <h2>VUELA COMO TE GUSTA</h2>
                    <div>
                        <button>
                        <figure></figure>
                        <p>INSPIRATE</p>
                        <p>Explora nuestros destinos y planea tu proxima aventura.Sacale provecho a tu siguiente vuelo</p>
                        </button>
                    </div>

                    <div>
                        <Link to="/servicios">
                            <button>
                            <figure></figure>
                        <p>EXPERIENCIA</p>
                        <p>¡Listo para despegar! Descubre los servicios a bordo que ofrecemos al volar con nosotros</p>
                            </button>
                        </Link>
                        
                    </div>

                    <div>
                        <Link to="/equipaje">
                        <button>
                        <figure></figure>
                        <p>EQUIPAJE</p>
                        <p>Enterate de las condiciones que debes tener en cuenta antes de preparar tu equipaje</p>
                        </button>
                        </Link>
                        
                    </div>
                </section>


                {existToken ? 
                    <section>
                        <h1>Bienvenido al proyecto Hermes</h1>
                        <figure></figure>
                    </section>
                :
                <section>
                <figure></figure>
                <h1>REGISTRATE</h1>
                <p>AL REGISTRARTE ACCEDE A BENEFICIOS EXCLUSIVOS,
ACUMULA PUNTOS PARA OBTENER LOS MEJORES
DESCUENTOS EN NUESTROS VIAJES Y PAQUETES
TURISTICOS, A DEMAS PODRAS SER PARTE DE LA FAMILIA
DE LA AEROLINA HERMES EN DONDE TODOS IMPORTAN.</p>
                    <Link to="/registrousuario"><button>Registrate ahora</button></Link>
            </section>
                }
            </article>

            <footer>
                <p>Todos los derechos reservados proyecto de desarrollo de software realizado por santiago de jesus foronda valverde 2024</p>
            </footer>
            
        </>
    )
}

export default Home;