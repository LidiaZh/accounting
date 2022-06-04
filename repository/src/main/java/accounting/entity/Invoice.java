package accounting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "invoice",
        uniqueConstraints = @UniqueConstraint(columnNames = {"number", "date"}))
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "cause")
    private String cause;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "receiver_id")
    private Receiver receiver;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Equipment> equipment = new HashSet<>();

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Invoice invoice = (Invoice) object;
        return id == invoice.id
                && number == invoice.number
                && Objects.equals(date, invoice.date)
                && Objects.equals(cause, invoice.cause);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, date, cause);
    }
}
