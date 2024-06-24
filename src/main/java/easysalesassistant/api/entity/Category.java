package easysalesassistant.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    @NotBlank(message = "Description of category must not be empty")
    private String description;

    private Date createdAt;

    private Date deletedAt;

    private boolean deleted = false;

    @ManyToOne()
    @JoinColumn(name = "id_user_created")
    private SystemUser idUserCreated;

    @ManyToOne()
    @JoinColumn(name = "id_user_deleted")
    private SystemUser idUserDeleted;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idCategory")
    private List<Product> products;
}
