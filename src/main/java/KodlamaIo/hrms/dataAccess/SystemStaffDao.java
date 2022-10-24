package KodlamaIo.hrms.dataAccess;

import KodlamaIo.hrms.entity.concretes.SystemStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SystemStaffDao extends JpaRepository<SystemStaff,Long> {

    Optional<SystemStaff> getSystemStaffByEmail(String email);
    boolean existsSystemStaffByEmail(String email);
}
