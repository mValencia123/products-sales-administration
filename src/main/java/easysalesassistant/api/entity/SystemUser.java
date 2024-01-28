package easysalesassistant.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    @NotBlank(message = "Last name must not be empty")
    private String lastName;

    @Column(length = 25)
    @NotBlank(message = "Name must not be empty")
    private String name;

    @Column(length = 18)
    private String rfc;

    @Column(unique = true)
    private String userName;

    @Column(length = 60)
    private String password;

    @Email
    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Role> authorities;

    private boolean enabled;
}