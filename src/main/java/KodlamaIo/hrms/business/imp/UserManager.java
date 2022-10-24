package KodlamaIo.hrms.business.imp;


import KodlamaIo.hrms.business.abstracts.UserService;
import KodlamaIo.hrms.core.emailService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {

	private EmailService emailService;
	@Autowired
	public UserManager(EmailService emailService) {
		this.emailService = emailService;
	}

	@Override
	public void sendMesagge(String message) {
		this.emailService.sendMessage(message);
	}
}
