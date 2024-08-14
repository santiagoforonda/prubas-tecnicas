Para probar la aplicacion debe usar las siguientes URL's en algun programa que simule un cliente

Registar usuario: http://localhost:8080/auth/register
body: {
	name:,
	email:,
	password:
}

Logearse: http://localhost:8080/auth/login
body: {
	email:,
	password:
}

----------------------------------------------------------
Listar usuarios: http://localhost:8080/api/v1/showUser
Obtener usuario por ID: http://localhost:8080/api/v1/user/{id}
Actualizar usuario: http://localhost:8080/api/v1/userUpdate/{id}
body: {
	name:,
	email:,
	password:
}
Eliminar usuario: http://localhost:8080/api/v1/userDelete/{id}

-----------------------------------------------------
Registrar un celuar: http://localhost:8080/api/v1/phones/users/{userId}/createPhone
body: {
	number:,
	cityCode:,
	countryCode
}

Listar celulares: http://localhost:8080/api/v1/phones/showPhones
Obtener celular por ID: http://localhost:8080/api/v1/phones/users/{userId}/getPhone/{id}
Eliminar celular: http://localhost:8080/api/v1/phones/users/{userId}/deletePhone/{id}

