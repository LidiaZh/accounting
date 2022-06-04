package accounting.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "branch")
public class Branch extends Organization implements Serializable {

    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
    private Set<ResponsiblePerson> responsiblePerson = new HashSet<>();


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "department_branch",
            joinColumns = {@JoinColumn(name = "id_branch")},
            inverseJoinColumns = {@JoinColumn(name = "id_department")}
    )
    @ToString.Exclude
    @Builder.Default
    private Set<Department> department = new HashSet<>();

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", contact='" + getContact() + '\'' +
                ", phone='" + getPhone() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Branch that = (Branch) object;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getAddress(), that.getAddress())
                && Objects.equals(getContact(), that.getContact())
                && Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getContact(), getAddress(), getPhone());
    }
}
