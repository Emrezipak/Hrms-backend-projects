package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.SystemStaff;
import KodlamaIo.hrms.payload.request.SystemStaffUpdateRequest;

public interface SystemStaffService {

    DataResult<SystemStaff> addSystemStaff(SystemStaff systemStaff);
    DataResult<SystemStaff> updateSystemStaffByEmail(String email ,
                                                     SystemStaffUpdateRequest systemStaff);
    Result approvalToAdverts(Long advertId);
}
