package accounting;

import accounting.entity.Branch;
import accounting.entity.Department;
import accounting.entity.Equipment;
import accounting.entity.EquipmentDetail;
import accounting.entity.Invoice;
import accounting.entity.Receiver;
import accounting.entity.ResponsiblePerson;
import accounting.entity.Supplier;

import java.time.LocalDate;

import static accounting.Constant.*;

public class Utils {

    public static Supplier createSupplier(String name) {
        return Supplier.builder()
                .name(name)
                .address(ADDRESS_FOR_SUPPLIER)
                .phone(PHONE_FOR_SUPPLIER)
                .build();
    }

    public static Receiver createReceiver(String name) {
        return Receiver.builder()
                .name(name)
                .address(ADDRESS_FOR_RECEIVER)
                .phone(PHONE_FOR_RECEIVER)
                .build();
    }

    public static Invoice createInvoice(Integer number, Supplier supplier, Receiver receiver) {
        return Invoice.builder()
                .number(number)
                .date(LocalDate.of(YEAR_OF_INVOICE, MONTH_OF_INVOICE, DAY_OF_INVOICE))
                .cause(CAUSE_FOR_INVOICE)
                .receiver(receiver)
                .supplier(supplier)
                .build();
    }

    public static Invoice createTestInvoice(Integer number) {
        return Invoice.builder()
                .number(number)
                .date(LocalDate.of(YEAR_OF_INVOICE, MONTH_OF_INVOICE, DAY_OF_INVOICE))
                .cause(CAUSE_FOR_INVOICE)
                .build();
    }

    public static Equipment createEquipment(Integer accountNumber,
                                            String serialNumber) {
        return Equipment.builder()
                .account_number(accountNumber)
                .s_number(serialNumber)
                .status(STATUS)
                .start_date(LocalDate.of(YEAR_START_DATE,
                        MONTH_START_DATE, DAY_OF_MONTH_START_DAY))
                .build();
    }

    public static EquipmentDetail createEquipmentDetail(String name) {
        return EquipmentDetail.builder()
                .name(name)
                .desc(DESC_OF_EQUIPMENT_DETAIL)
                .termOfUse(TERM_OF_USE)
                .producer(PRODUCER)
                .build();
    }

    public static Branch createBranch(String name) {
        return Branch.builder()
                .name(name)
                .address(ADDRESS_FOR_BRANCH)
                .phone(PHONE_FOR_BRANCH)
                .build();
    }

    public static Department createDepartment(String name) {
        return Department.builder()
                .name(name)
                .build();
    }

    public static ResponsiblePerson createPerson(
            String name, Branch branch, Department department) {
        return ResponsiblePerson.builder()
                .name(name)
                .surname(SURNAME_OF_RESPONSIBLE_PERSON)
//                .branch(branch)
//                .department(department)
                .build();
    }

    public static ResponsiblePerson createRPerson(String name) {
        return ResponsiblePerson.builder()
                .name(name)
                .surname(SURNAME_OF_RESPONSIBLE_PERSON)
                .build();
    }
}
