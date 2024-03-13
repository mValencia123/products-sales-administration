package easysalesassistant.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
@Setter
@Getter
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    @NotBlank(message = "Description of category must not be empty")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "id_user_created")
    private SystemUser idUserCreated;

    private Date createdAt;

    @ManyToOne()
    @JoinColumn(name = "id_user_deleted")
    private SystemUser idUserDeleted;

    private Date deletedAt;

    private boolean deleted = false;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idCategory")
    private List<Product> products;
}
