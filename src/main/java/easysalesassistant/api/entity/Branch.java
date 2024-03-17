package easysalesassistant.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "branches")
@Setter
@Getter
public class Branch implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String description;

    private Date createdAt;

    private Long phoneNumber;

    private boolean allowSellNegativeStock;

    @ManyToOne
    @JoinColumn(name="id_store")
    private Store idStore;

    @OneToOne
    @JoinColumn(name="id_address")
    private Address idAddress;
}
