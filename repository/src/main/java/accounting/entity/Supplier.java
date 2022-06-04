package accounting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "supplier")
public class Supplier extends Organization implements Serializable {

    @OneToMany(mappedBy = "supplier")
    private Set<Invoice> invoices = new HashSet<>();

    @Override
    public String toString() {
        return "Supplier{" +
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
        Supplier that = (Supplier) object;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getContact(), that.getContact())
                && Objects.equals(getAddress(), that.getAddress())
                && Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getContact(), getAddress(), getPhone());
    }
}
