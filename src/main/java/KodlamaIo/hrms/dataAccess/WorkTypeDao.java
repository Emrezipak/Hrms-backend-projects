package KodlamaIo.hrms.dataAccess;

import KodlamaIo.hrms.entity.concretes.WorkTime;
import KodlamaIo.hrms.entity.concretes.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTypeDao extends JpaRepository<WorkType,Integer> {

    WorkType getByTypeName(String typeName);
}
