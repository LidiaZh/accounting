package managment.implementation;

import accounting.dao.EntityDaoImplBranch;
import accounting.dao.EntityDaoImplDepartment;
import accounting.dao.EntityDaoImplResponsiblePerson;
import accounting.entity.Branch;
import accounting.entity.Department;
import accounting.entity.ResponsiblePerson;
import dto.RPersonDto;
import managment.interfaces.ResPersonService;

import java.util.List;
import java.util.stream.Collectors;

public class ResPersonServiceImpl
        implements ResPersonService {

    private final EntityDaoImplBranch daoImplBranch
            = new EntityDaoImplBranch();

    private final EntityDaoImplResponsiblePerson daoImplResPerson
            = new EntityDaoImplResponsiblePerson();

    private final EntityDaoImplDepartment daoImplDepartment
            = new EntityDaoImplDepartment();

    @Override
    public ResponsiblePerson addPerson(String name, String surname,
                                       Integer idBranch,
                                       Integer idDepartment) {
        Branch branch = daoImplBranch.getEntity(idBranch);
        Department department = daoImplDepartment.getEntity(idDepartment);
        ResponsiblePerson rPerson = ResponsiblePerson.builder()
                .name(name)
                .surname(surname)
                .department(department)
                .branch(branch)
                .build();
        daoImplResPerson.insert(rPerson);
        return rPerson;
    }

    @Override
    public void delPerson(Integer id) {
        daoImplResPerson.deleteById(id);
    }

    @Override
    public void updateResPerson(Integer id, String name,
                                String surname) {
        ResponsiblePerson rPerson = getPerson(id);
        rPerson.setName(name);
        rPerson.setSurname(surname);
        daoImplResPerson.update(rPerson);
    }

    @Override
    public List<ResponsiblePerson> getAllPersons() {
        return daoImplResPerson.select();
    }

    @Override
    public ResponsiblePerson getPerson(Integer id) {
        return daoImplResPerson.getEntity(id);
    }

    @Override
    public List<RPersonDto> getAllPersonsDto() {
        return daoImplResPerson.select().stream()
                .map(person -> RPersonDto.builder()
                        .id(person.getId())
                        .surname(person.getSurname())
                        .name(person.getName())
                        .idBranch(person.getBranch().getId())
                        .branchName(person.getBranch().getName())
                        .idDepartment(person.getDepartment().getId())
                        .departmentName(person.getDepartment().getName())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public void editDepAndBranchForRPerson(Integer idRPerson,
                                           Integer idBranch, Integer idDepartment) {
        ResponsiblePerson rPerson = daoImplResPerson.getEntity(idRPerson);
        rPerson.setDepartment(daoImplDepartment.getEntity(idDepartment));
        rPerson.setBranch(daoImplBranch.getEntity(idBranch));
        daoImplResPerson.update(rPerson);
    }
}
