package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.JobAdvertisementService;
import KodlamaIo.hrms.business.abstracts.SystemStaffService;
import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.dataAccess.SystemStaffDao;
import KodlamaIo.hrms.entity.concretes.JobAdvertisement;
import KodlamaIo.hrms.entity.concretes.SystemStaff;
import KodlamaIo.hrms.payload.request.SystemStaffUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SystemStaffManager implements SystemStaffService {

    private SystemStaffDao systemStaffDao;
    private JobAdvertisementService jobAdvertisementService;

    public SystemStaffManager(SystemStaffDao systemStaffDao, JobAdvertisementService jobAdvertisementService) {
        this.systemStaffDao = systemStaffDao;
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @Override
    public DataResult<SystemStaff> addSystemStaff(SystemStaff systemStaff) {
        if (!systemStaffDao.existsSystemStaffByEmail(systemStaff.getEmail())) {
            return new SuccessDataResult(systemStaffDao.save(systemStaff), "added new System Staff");
        }
        return new ErrorDataResult("There is a person registered with this email");
    }

    @Override
    public DataResult<SystemStaff> updateSystemStaffByEmail(String email,
                                                            SystemStaffUpdateRequest newSystemStaff) {
        Optional<SystemStaff> systemStaff = systemStaffDao.getSystemStaffByEmail(email);
        if (systemStaff.isPresent()) {
            SystemStaff updatedSystemStaff = updateSystemStaff(systemStaff.get(), newSystemStaff);
            return new SuccessDataResult(systemStaffDao.save(updatedSystemStaff), "Success");
        }

        return new ErrorDataResult("not found system staff");
    }

    @Override
    public Result approvalToAdverts(Long advertId) {
        DataResult<JobAdvertisement> jobAdvertisement = jobAdvertisementService.getAdvertById(advertId);
        if (jobAdvertisement.isSuccess()) {
            jobAdvertisementService.updateAdvert(changeStatusJobAdvert(jobAdvertisement.getData()));
            return new SuccessResult("job posting status changed successfully!");
        }
        return new ErrorResult("not found job advert!");
    }

    private SystemStaff updateSystemStaff(SystemStaff systemStaff, SystemStaffUpdateRequest newSystemStaff) {
        systemStaff.setName(newSystemStaff.getName());
        systemStaff.setSurname(newSystemStaff.getSurname());
        systemStaff.setDepartmentName(newSystemStaff.getDepartmentName());
        return systemStaff;
    }

    public JobAdvertisement changeStatusJobAdvert(JobAdvertisement jobAdvertisement) {
        boolean advertCondition = jobAdvertisement.isJobCondition();
        jobAdvertisement.setJobCondition(!advertCondition);
        jobAdvertisement.setId(jobAdvertisement.getId());
        return jobAdvertisement;
    }
}
