package br.ufpb.dcx.dsc.figurinhas.controller;

import br.ufpb.dcx.dsc.figurinhas.models.User;
import br.ufpb.dcx.dsc.figurinhas.repository.UserRepository;
import br.ufpb.dcx.dsc.figurinhas.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil,
                          UserRepository userRepository, PasswordEncoder encoder) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody User request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return jwtUtil.generateToken(request.getUsername());
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String username, @RequestParam String newPassword) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
        return "Senha alterada com sucesso!";
    }
}
