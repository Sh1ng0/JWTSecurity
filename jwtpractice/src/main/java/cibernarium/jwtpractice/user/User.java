package cibernarium.jwtpractice.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
// UniqueConstraint impide que haya duplicados
public class User implements UserDetails {

    @Id
    @GeneratedValue
    Integer id;
    @Basic
    @Column(nullable = false)
    String username;
    @Column(nullable = false)
    String password;

    String firstname;
    String lastname;
    String country;
    @Enumerated(EnumType.STRING)
    // Esto hace que el enum (Que por defecto se guarda como su valor numérico, seguarde como un string en este caso)
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
