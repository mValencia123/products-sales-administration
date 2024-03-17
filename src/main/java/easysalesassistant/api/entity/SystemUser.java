package easysalesassistant.api.entity;

import easysalesassistant.api.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.io.Serializable;
import java.util.Date;
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
    private String firstName;

    @Column(length = 18)
    private String rfc;

    @Column(unique = true)
    private String userName;

    @Column(length = 60)
    private String password;

    @Email
    private String email;

    private boolean enabled = true;

    private Date createdAt;

    private Date deletedAt;

    private String photo;

    private Long phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Date birthday;

    @ManyToOne
    @JoinColumn(name="id_branch")
    private Branch idBranch;

    @ManyToOne
    @JoinColumn(name="id_user_created")
    private SystemUser idUserCreated;

    @ManyToOne
    @JoinColumn(name="id_user_deleted")
    private SystemUser idUserDeleted;

    @OneToOne
    @JoinColumn(name="id_address")
    private Address idAddress;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Role> authorities;
}
