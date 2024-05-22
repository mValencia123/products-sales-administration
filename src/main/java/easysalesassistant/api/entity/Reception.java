package easysalesassistant.api.entity;

import easysalesassistant.api.enums.StatusReception;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "receptions")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reception implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    List<ProductTransaction> products;

    @Column(length = 2048)
    private String comments;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "id_user_created")
    private SystemUser userCreated;

    private Date authorizedAt;

    @ManyToOne
    @JoinColumn(name = "id_user_authorized")
    private SystemUser userAuthorized;

    @ManyToOne
    @JoinColumn(name = "id_store")
    private Store storeReceive;

    @Enumerated(EnumType.STRING)
    private StatusReception status;
}
