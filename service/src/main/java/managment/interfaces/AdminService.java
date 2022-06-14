package managment.interfaces;

import accounting.entity.Branch;
import accounting.entity.Department;
import accounting.entity.ResponsiblePerson;

import java.util.List;

public interface AdminService {

    Branch addBranch(String name, String address, String phone);

    void delBranch(Integer id);

    void updateBranch(Integer id, String name,
                      String address, String phone);

    List<?> getAllBranches();

    Branch getBranch(Integer id);

    Department addDepartment(String name);

    void delDepartment(Integer id);

    void updateDepartment(Integer id, String name);

    List<?> getAllDepartments();

    Department getDepartment(Integer id);

    ResponsiblePerson addPerson(String name,String surname,
                                Branch branch, Department department);

    void delPerson(Integer id);

    void updatePersonNameAndSurname(Integer id, String name, String surname);

    void updatePersonBranchAndDepartment
            (Integer id, Branch branch, Department department);

    List<?> getAllPersons();

    ResponsiblePerson getPerson(Integer id);
}
