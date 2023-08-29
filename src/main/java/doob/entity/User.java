package doob.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Size(min = 3, max = 20, message = "Длинна от 3 дo 20 символов")
    @Column(name = "name")
    private String name;
    @Size(min = 3, max = 20, message = "Длинна от 3 дo 20 символов")
    @Column(name = "lastName")
    private String lastName;
    @Size(min = 3, max = 20, message = "Длинна от 3 дo 20 символов")
    @Column(name = "accountName")
    private String accountName;
    @Size(min = 3, max = 20, message = "Длинна от 3 дo 20 символов")
    @Email
    @Column(name = "email")
    private String email;
    @Size(min = 3, max = 20, message = "Длинна от 3 дo 20 символов")
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User(int id, String name, String lastName, String accountName, String email, String phone) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.accountName = accountName;
        this.email = email;
        this.phone = phone;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
