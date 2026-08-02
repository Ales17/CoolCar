package cz.ales17.auto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "user_entity")
public class UserEntity extends AbstractEntity implements UserDetails, OidcUser {
    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;
    @Column(unique = true, nullable = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "ownedBy", orphanRemoval = true)
    private List<Car> cars;

    /*
     * OIDC state, populated by TinyauthOidcUserService on login and never persisted.
     * Implementing OidcUser here means an OIDC login puts a real UserEntity into the
     * security context, so SecurityUtil, AuthorizationService and the @PreAuthorize
     * checks keep working exactly as they do for form login.
     */
    @Transient
    private OidcIdToken idToken;
    @Transient
    private OidcUserInfo userInfo;
    @Builder.Default
    @Transient
    private Map<String, Object> claims = Map.of();

    @Override
    public Map<String, Object> getAttributes() {
        return claims;
    }

    /**
     * Identifies the principal to Spring Security. Deliberately the app username
     * rather than the OIDC {@code sub} - Tinyauth issues pairwise subjects, and the
     * templates render {@code #authentication.name}.
     */
    @Override
    public String getName() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map((role) -> new SimpleGrantedAuthority("ROLE_" + role.getName())).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
