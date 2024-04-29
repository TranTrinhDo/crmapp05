package service;

import java.util.List;

import entity.User;
import repository.UserRepository;

public class LoginService {

	UserRepository userRepository = new UserRepository();
	
	public boolean checkLogin(String email, String password) {
		List<User> list = userRepository.getUserByEmailAndPassWord(email, password);
		return list.size() > 0;
	}
}
