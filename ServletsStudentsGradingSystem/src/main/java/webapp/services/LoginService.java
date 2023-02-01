package webapp.services;

import webapp.data.StudentDaoUser;

public class LoginService {
	public boolean validateUser(String user, String password) {
		StudentDaoUser studentDaoUser = new StudentDaoUser();
		return studentDaoUser.validateCredentials(user, password);
	}

}