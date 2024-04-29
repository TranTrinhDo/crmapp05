package entity;

public class User {

	private int id;
	private String fullname;
	private String email;
	private String password;
	private String phone;
	private String firstName;
	private String lastName;
	private String idRole;
	/*
	 * Lưu ý: nếu như cột là khóa ngoại của bảng khác thì sẽ tạo ra đối tượng thế cho id khóa ngoại
	 * */
	private Role role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public User(String email, String password, String firstName, String lastName,Role role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        setRole(role);
    }

    public User(int id, String email, String password, String firstName, String lastName,Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        setRole(role);
    }
	public User() {
	}
	
}
