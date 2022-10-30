package com.mykola.eshopmykola.dtos.user;

import com.mykola.eshopmykola.models.user.Role;

public class UserDTO {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String matchingPassword;
	private Role role;

	public UserDTO() {
	}

	public UserDTO(String firstName, String lastName, String email, String password, String matchingPassword, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.role = role;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static BuilderUserDTO builder() {
		return new BuilderUserDTO();
	}

	public static class BuilderUserDTO {
		private String firstName;
		private String lastName;
		private String email;
		private String password;
		private String matchingPassword;
		private Role role;

		BuilderUserDTO() {

		}

		public BuilderUserDTO lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public BuilderUserDTO firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public BuilderUserDTO email(String email) {
			this.email = email;
			return this;
		}

		public BuilderUserDTO password(String password) {
			this.password = password;
			return this;
		}

		public BuilderUserDTO matchingPassword(String matchingPassword) {
			this.matchingPassword = matchingPassword;
			return this;
		}

		public BuilderUserDTO role(Role role) {
			this.role = role;
			return this;
		}

		public UserDTO build() {
			return new UserDTO(this.firstName, this.lastName, this.email, this.password, this.matchingPassword, this.role);
		}
	}
}

