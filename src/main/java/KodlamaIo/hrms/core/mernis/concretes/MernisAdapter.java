package KodlamaIo.hrms.core.mernis.concretes;


import KodlamaIo.mernis.SQRKPSPublicSoap;
import org.springframework.stereotype.Service;

import KodlamaIo.hrms.core.mernis.abstracts.MernisService;
import KodlamaIo.hrms.entity.concretes.JobSeeker;

@Service
public class MernisAdapter implements MernisService{

	@Override
	public boolean checkMernisService(JobSeeker jobSeeker) {
		SQRKPSPublicSoap mernisService = new SQRKPSPublicSoap();
		Long identificationNo = Long.valueOf(jobSeeker.getIdentificationNo());
		Integer birthYear = jobSeeker.getBirthYear().getYear();
		try {
			return mernisService.TCKimlikNoDogrula(identificationNo,jobSeeker.getUserName()
					,jobSeeker.getUserSurname(),birthYear);
		} catch (Exception e) {
			return false;
		}
	}

}
