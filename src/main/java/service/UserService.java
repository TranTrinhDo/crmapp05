package service;

import java.util.List;

import entity.Role;
import entity.User;
import repository.RoleRepository;
import repository.UserRepository;

public class UserService {

	private RoleRepository roleRepository = new RoleRepository();
	private UserRepository userRepository = new UserRepository();

	
	
	public boolean insertUser(String fullname, String email, String password, String phone, int idRole) {
		int count = userRepository.save(fullname, email, password, phone, idRole);
		return count > 0;
	}
	
	public List<User> getAllUser(){
		return userRepository.getAll();
	}
	
	public List<User> getListUser(){
		return userRepository.getListUser();
	}
	
	public boolean deleteUserById(int id) {
		return userRepository.deleteUserById(id) > 0;
	}
	
	public boolean updateUser(int id,String fullname, String email, String password, String phone,String firstName,String lastName, int idRole) {
		int count = userRepository.updateUser(id, fullname, email, password, phone, firstName, lastName, idRole);
		return count > 0;
	}
	
	public User findUserById(int id) {
		return userRepository.findUserById(id);
	}
}
