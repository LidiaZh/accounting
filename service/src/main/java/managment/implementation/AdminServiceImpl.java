package managment.implementation;

import accounting.dao.EntityDaoImplBranch;
import accounting.dao.EntityDaoImplDepartment;
import accounting.dao.EntityDaoImplResponsiblePerson;
import accounting.entity.Branch;
import accounting.entity.Department;
import accounting.entity.ResponsiblePerson;
import managment.interfaces.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private final EntityDaoImplBranch entityDaoImplBranch
            = new EntityDaoImplBranch();

    private final EntityDaoImplDepartment entityDaoImplDepartment
            = new EntityDaoImplDepartment();

    private final EntityDaoImplResponsiblePerson entityDaoImplRespPerson
            = new EntityDaoImplResponsiblePerson();

    @Override
    public Branch addBranch(String name, String address, String phone) {
        Branch branch = Branch.builder()
                .name(name)
                .address(address)
                .phone(phone)
                .build();
        entityDaoImplBranch.insert(branch);
        return branch;
    }

    @Override
    public void delBranch(Integer id) {
        entityDaoImplBranch.deleteById(id);
    }

    @Override
    public void updateBranch(Integer id, String name,
                             String address, String phone) {
        Branch branch = getBranch(id);
        branch.setName(name);
        branch.setAddress(address);
        branch.setPhone(phone);
        entityDaoImplBranch.update(branch);
    }

    @Override
    public Branch getBranch(Integer id) {
        return entityDaoImplBranch.getEntity(id);
    }

    @Override
    public List<?> getAllBranches() {
        return entityDaoImplBranch.select();
    }

    @Override
    public Department addDepartment(String name) {
        Department department = Department.builder()
                .name(name)
                .build();
        entityDaoImplDepartment.insert(department);
        return department;
    }

    @Override
    public void delDepartment(Integer id) {
        entityDaoImplDepartment.deleteById(id);

    }

    @Override
    public void updateDepartment(Integer id, String name) {
        Department department = getDepartment(id);
        department.setName(name);
        entityDaoImplDepartment.update(department);
    }

    @Override
    public List<?> getAllDepartments() {
        return entityDaoImplDepartment.select();
    }

    @Override
    public Department getDepartment(Integer id) {
        return entityDaoImplDepartment.getEntity(id);
    }

    @Override
    public ResponsiblePerson addPerson(String name, String surname,
                                       Branch branch,
                                       Department department) {
        ResponsiblePerson rPerson = ResponsiblePerson.builder()
                .name(name)
                .surname(surname)
                .branch(branch)
                .department(department)
                .build();
        entityDaoImplRespPerson.insert(rPerson);
        return rPerson;
    }

    @Override
    public void delPerson(Integer id) {
        entityDaoImplRespPerson.deleteById(id);
    }

    @Override
    public void updatePersonNameAndSurname(Integer id,
                                           String name, String surname) {
        ResponsiblePerson rPerson = getPerson(id);
        rPerson.setName(name);
        rPerson.setSurname(surname);
        entityDaoImplRespPerson.update(rPerson);
    }

    @Override
    public void updatePersonBranchAndDepartment
            (Integer id, Branch branch, Department department) {
        ResponsiblePerson rPerson = getPerson(id);
        rPerson.setBranch(branch);
        rPerson.setDepartment(department);
        entityDaoImplRespPerson.update(rPerson);
    }

    @Override
    public List<?> getAllPersons() {
        return entityDaoImplRespPerson.select();
    }

    @Override
    public ResponsiblePerson getPerson(Integer id) {
        return entityDaoImplRespPerson.getEntity(id);
    }
}
