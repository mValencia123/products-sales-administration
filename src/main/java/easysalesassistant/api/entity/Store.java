package easysalesassistant.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table( name = "stores")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Store implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String description;

    private Date createdAt;

    private Long phoneNumber;

    private boolean deleted = false;

    private Date deletedAt;

    @ManyToOne
    @JoinColumn(name="id_user_created")
    private SystemUser idUserCreated;

    @ManyToOne
    @JoinColumn(name="id_user_deleted")
    private SystemUser idUserDeleted;

    @OneToOne
    @JoinColumn(name="id_address")
    private Address idAddress;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idStore")
    private List<Stock> stock;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idStore")
    private List<Branch> branch;
}
