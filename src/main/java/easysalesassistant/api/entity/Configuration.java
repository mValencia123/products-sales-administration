package easysalesassistant.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "configurations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Configuration implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_store_default")
    Store idStoreDefault;

    String urlImageDefault;
}
