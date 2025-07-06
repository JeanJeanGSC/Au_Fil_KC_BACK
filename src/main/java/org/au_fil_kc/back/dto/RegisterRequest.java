package org.au_fil_kc.back.dto;

import java.util.Set;
import org.au_fil_kc.back.entities.Role;

public record RegisterRequest(String id, String prenom, String nom, String email, String motDePasse, Set<Role> roles) {}

