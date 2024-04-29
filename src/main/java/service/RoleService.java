package service;

import java.util.List;

import entity.Role;
import repository.RoleRepository;

public class RoleService {
	private RoleRepository roleRepository = new RoleRepository();

	public boolean insertRole(String name, String description) {
		int count = roleRepository.insert(name, description);
		
		return count > 0;
	}
	
	public List<Role> getListRole(){
		return roleRepository.getListRole();
	}
	
	public boolean deleteRoleById(int id) {
		int count = roleRepository.deleteRoleById(id);
		return count > 0;
	}
	public boolean updateRole(int id,String name,String description) {
		int count = roleRepository.updateRole(id, name, description);
		return count >0;
	}
	
	public Role findRoleById(int id) {
		return roleRepository.findRoleById(id);
	}
}
