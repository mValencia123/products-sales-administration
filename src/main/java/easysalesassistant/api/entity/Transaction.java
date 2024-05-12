package easysalesassistant.api.entity;

import easysalesassistant.api.enums.StatusTransaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "transactions")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_store_origin")
    private Store origin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_store_destination")
    private Store destination;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "id_user_created")
    private SystemUser userCreated;

    private Date autorizedAt;

    @ManyToOne
    @JoinColumn(name = "id_user_autorized")
    private SystemUser userAutorized;

    private Date sentAt;

    @ManyToOne
    @JoinColumn(name = "id_user_sent")
    private SystemUser userSent;

    @ManyToOne
    @JoinColumn(name = "id_branch")
    private Branch branch;

    @OneToMany
    List<ProductTransaction> products;

    private String comment;

    private StatusTransaction status;
}
