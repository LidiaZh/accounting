package managment.interfaces;

import accounting.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department addDepartment(String name);

    void delDepartment(Integer id);

    void updateDepartment(Integer id, String name);

    List<Department> getAllDepartments();

    Department getDepartment(Integer id);
}
