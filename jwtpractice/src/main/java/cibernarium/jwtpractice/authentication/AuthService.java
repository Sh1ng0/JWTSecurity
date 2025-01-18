package cibernarium.jwtpractice.authentication;


import cibernarium.jwtpractice.jwt.JwtService;
import cibernarium.jwtpractice.user.Role;
import cibernarium.jwtpractice.user.User;
import cibernarium.jwtpractice.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Este servicio se encarga de la autenticación del usuario comparando la request con lo existente en la DB

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;


    public AuthResponse login(LoginRequest request) {

        return null;

    }


    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .country(request.getCountry())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }
}
