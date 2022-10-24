package KodlamaIo.hrms.dataAccess;

import KodlamaIo.hrms.entity.concretes.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTimeDao extends JpaRepository<WorkTime, Integer> {

    WorkTime getByTimeName(String timeName);


}
