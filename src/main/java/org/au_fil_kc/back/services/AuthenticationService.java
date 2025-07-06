package org.au_fil_kc.back.services;

import org.au_fil_kc.back.dto.AuthenticationRequest;
import org.au_fil_kc.back.dto.AuthenticationResponse;
import org.au_fil_kc.back.dto.RegisterRequest;
import org.au_fil_kc.back.entities.Utilisateur;
import org.au_fil_kc.back.repositories.UtilisateurRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthenticationService {
    private final UtilisateurRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UtilisateurRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        Utilisateur user = new Utilisateur();
        user.setId(request.id());
        user.setPrenom(request.prenom());
        user.setNom(request.nom());
        user.setEmail(request.email());
        user.setMotDePasse(passwordEncoder.encode(request.motDePasse()));
        user.setRoles(request.roles());

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.motDePasse()
                )
        );
        var user = repository.findByEmail(request.email())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
