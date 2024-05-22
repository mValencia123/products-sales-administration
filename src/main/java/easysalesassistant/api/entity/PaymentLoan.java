package easysalesassistant.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Table(name = "payments_loan")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentLoan implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double payment;

    private Date paymentAt;

    @ManyToOne
    @JoinColumn(name = "id_user_authorized")
    private SystemUser idUserAuthorized;

    @ManyToOne
    @JoinColumn(name = "id_loan")
    private Loan idLoan;
}
