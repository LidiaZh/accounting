package managment;

import accounting.entity.*;
import managment.implementation.AccountantServiceImpl;
import managment.implementation.AdminServiceImpl;
import managment.interfaces.AccountantService;

import java.time.LocalDate;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        AdminServiceImpl adminService = new AdminServiceImpl();

        Branch branch1 = adminService.addBranch("ОАО 'Групп' Склад",
                "г.Минск, ул.Амураторская,5", "(017)227-18-19");
        Branch branch2 = adminService.addBranch("Минский филиал ОАО 'Групп'",
                "г.Минск, ул.Скрыганова,14", "(017)355-55-52");
        Branch branch3 = adminService.addBranch("Брестский филиал ОАО 'Групп'",
                "г.Брест, ул.Строителей,16", "(016)212-12-15");

        Department department1 = adminService.addDepartment("Бухгалтерия");
        Department department2 = adminService.addDepartment("Тех.отдел");
        Department department3 = adminService.addDepartment("Лаборатория");

        ResponsiblePerson person1 = adminService.addPerson(
                "Мария", "Цветкова", branch1, department1);
        ResponsiblePerson person2 = adminService.addPerson(
                "Иван", "Васильков", branch2, department1);
        ResponsiblePerson person3 = adminService.addPerson(
                "Кирилл", "Лютиков", branch1, department3);
        ResponsiblePerson person4 = adminService.addPerson(
                "Михаил", "Тюльпанов", branch3, department3);

        AccountantServiceImpl accountantService = new AccountantServiceImpl();

        Supplier supplier1 = accountantService.addSupplier("CompanyService",
                "г.Гомель, ул.Радужная, 15", "(023)213-11-44");

        Supplier supplier2 = accountantService.addSupplier("CompanyGroup",
                "г.Минск, ул.Красная, 11", "(017)244-15-55");

        Supplier supplier3 = accountantService.addSupplier("Брестский филиал ОАО 'Групп'",
                "г.Брест, ул.Строителей,16", "(016)212-12-15");

        Supplier supplier4 = accountantService.addSupplier("ОАО 'Групп' Склад",
                "г.Минск, ул.Амураторская,5", "(017)227-18-19");


        Receiver receiver1 = accountantService.addReceiver("ОАО 'Групп' Склад",
                "г.Минск, ул.Амураторская,5", "(017)227-18-19");

        Receiver receiver2 = accountantService.addReceiver("CompanyService",
                "г.Гомель, ул.Радужная, 15", "(023) 213-11-44");

        Receiver receiver3 = accountantService.addReceiver("Минский филиал ОАО 'Групп'",
                "г.Минск, ул.Скрыганова,14", "(017)355-55-52");


        Invoice invoice1 = accountantService.addInvoice(11111,
                LocalDate.of(2022,3, 11),
                "Договор №123 от 05.01.2022", 12312.22F, supplier1, receiver1);

        Invoice invoice2 = accountantService.addInvoice(11112,
                LocalDate.of(2022,3,12),
                "тестирование", 2312.22F, supplier4, receiver3);

        EquipmentDetail equipmentDetail1 = accountantService.addEquipmentDetail(
                "Коммутатор Quidway S5300-EI", "24 порта 100/1000Base-X, 4 порта 10/100/1000Base-T combo",
                "Huawei", 7);
        Equipment equipment1 = accountantService.addEquipment(100100200,
                "123QWE1AFD", "оформлено", invoice1, person1, equipmentDetail1);


        EquipmentDetail equipmentDetail2 = accountantService.addEquipmentDetail(
                "Модуль NTA-SFP+LR-10", "Оптический модуль SFP+, 10Гб/с, 1310nm, 10км, SingleMode, LC-duplex",
                "OPATOV", 5);
        Equipment equipment2 = accountantService.addEquipment(100100201,
                "23ASE1AFD", "тестирование", invoice2, person4, equipmentDetail2);
    }
}
