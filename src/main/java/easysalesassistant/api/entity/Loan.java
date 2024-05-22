package easysalesassistant.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.List;

@Table(name = "loans")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Loan implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Sell idSell;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "id_user_authorized")
    private SystemUser idUserAuthorized;

    @PreUpdate
    public void preUpdated(){
        double subtotal = 0;
        for(PaymentLoan paymentLoan : payments){
            subtotal += paymentLoan.getPayment();
        }
        this.balance = idSell.getTotal() - subtotal;
    }

    @OneToMany
    List<PaymentLoan> payments;
}
