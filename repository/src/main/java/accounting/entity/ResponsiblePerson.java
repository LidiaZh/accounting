package accounting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "responsible_person")
public class ResponsiblePerson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "responsiblePerson", fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Equipment> equipment
            = new HashSet<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @ToString.Exclude
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne(cascade = CascadeType.MERGE)
    @ToString.Exclude
    @JoinColumn(name = "department_id")
    private Department department;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ResponsiblePerson person = (ResponsiblePerson) object;
        return Objects.equals(id, person.id)
                && Objects.equals(name, person.name)
                && Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }
}
