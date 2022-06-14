package managment.interfaces;

import accounting.entity.ResponsiblePerson;
import dto.RPersonDto;

import java.util.List;

public interface ResPersonService {


    ResponsiblePerson addPerson(String name, String surname,
                                Integer idBranch,
                                Integer idDepartment);

    void delPerson(Integer id);

    void updateResPerson(Integer id, String name,
                         String surname);

    List<ResponsiblePerson> getAllPersons();

    ResponsiblePerson getPerson(Integer id);

    List<RPersonDto> getAllPersonsDto();

    void editDepAndBranchForRPerson(Integer idRPerson,
                                    Integer idBranch, Integer idDepartment);
}
