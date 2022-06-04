package accounting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "equipment")
public class Equipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_number",unique = true)
    private int account_number;

    @Column(name = "status")
    private String status;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "serial_number",unique = true)
    private String s_number;

    @Column(name = "price")
    private float price;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "equip_detail_id")
    private EquipmentDetail equipmentDetail;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "res_person_id")
    private ResponsiblePerson responsiblePerson;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Equipment equipment = (Equipment) object;
        return account_number == equipment.account_number
                && Objects.equals(id, equipment.id)
                && Objects.equals(status, equipment.status)
                && Objects.equals(start_date, equipment.start_date)
                && Objects.equals(s_number, equipment.s_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account_number,
                status, start_date, s_number);
    }
}
