package easysalesassistant.api.entity;

import easysalesassistant.api.enums.StatusReception;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "receptions")
@Entity
@Data
public class Reception implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    List<ProductTransaction> products;

    private String comment;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "id_user_created")
    private SystemUser userCreated;

    private Date autorizedAt;

    @ManyToOne
    @JoinColumn(name = "id_user_autorized")
    private SystemUser userAutorized;

    @ManyToOne
    @JoinColumn(name = "id_store")
    private Store storeReceive;

    private StatusReception status;
}
