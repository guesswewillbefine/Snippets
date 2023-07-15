package ua.snippets.core.persistence.user;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ua.snippets.core.persistence.Identifiable;


@Table(name = "user_data")
@Getter
@Setter
@Entity
public class User extends AuthenticationData implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String email;

    @Override
    public boolean isNew() {
        return id == 0;
    }
}
