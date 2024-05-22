package easysalesassistant.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cash_cuts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashCut implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user_created")
    private SystemUser idUserCreated;

    private Date startAt;

    private Date finishAt;

    private double total;

    private String comments;
}
