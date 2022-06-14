package managment.implementation;

import accounting.dao.EntityDaoImplDepartment;
import accounting.entity.Department;
import managment.interfaces.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private final EntityDaoImplDepartment daoImplDepartment
            = new EntityDaoImplDepartment();

    @Override
    public Department addDepartment(String name) {
        Department department = Department.builder()
                .name(name)
                .build();
        daoImplDepartment.insert(department);
        return department;
    }

    @Override
    public void delDepartment(Integer id) {
        daoImplDepartment.deleteById(id);

    }

    @Override
    public void updateDepartment(Integer id, String name) {
        Department department = getDepartment(id);
        department.setName(name);
        daoImplDepartment.update(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return daoImplDepartment.select();
    }

    @Override
    public Department getDepartment(Integer id) {
        return daoImplDepartment.getEntity(id);
    }

}
