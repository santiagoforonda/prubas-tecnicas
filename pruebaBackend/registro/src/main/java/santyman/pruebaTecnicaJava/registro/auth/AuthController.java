package santyman.pruebaTecnicaJava.registro.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import santyman.pruebaTecnicaJava.registro.config.UserInfoService;
import santyman.pruebaTecnicaJava.registro.dto.UserDTO;
import santyman.pruebaTecnicaJava.registro.exceptions.UserNotFoundException;
import santyman.pruebaTecnicaJava.registro.jwt.JwtService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserInfoService userInfoService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthController(UserInfoService userInfoService, JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.userInfoService = userInfoService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if(authentication.isAuthenticated()){
            AuthResponse token  = new AuthResponse(jwtService.getToken(loginRequest));
            return ResponseEntity.ok(token);
            
        }else{
            throw new UserNotFoundException(loginRequest.getEmail());
        }
    }

     @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userInfoService.addUser(userDTO));
    }
}
