package accounting.entity;

import lombok.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
@Table(name = "EQUIPMENT")
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

//    @Formula("(SELECT i.price FROM INVOICE i WHERE i.id = id)")
    @Column(name = "depreciation")
    private float depreciation;

    @Column(name = "serial_number",unique = true)
    private String s_number;

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
                && Float.compare(equipment.depreciation, depreciation) == 0
                && Objects.equals(id, equipment.id)
                && Objects.equals(status, equipment.status)
                && Objects.equals(start_date, equipment.start_date)
                && Objects.equals(s_number, equipment.s_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account_number,
                status, start_date, depreciation, s_number);
    }
}
