package easysalesassistant.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "providers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Provider implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 15)
    private String rfc;

    private boolean deleted = false;

    private Date deletedAt;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "id_user_created")
    private SystemUser idUserCreated;

    @ManyToOne
    @JoinColumn(name = "id_user_deleted")
    private SystemUser idUserDeleted;

    @OneToOne
    @JoinColumn(name="id_address")
    private Address idAddress;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idProvider")
    private List<Product> products;
}
