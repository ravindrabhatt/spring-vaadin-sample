package org.kun.vaadin.backend.data.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;
/*
create table USER_INFO (
  ID VARCHAR2(382 CHAR) ,
  EMAIL VARCHAR2(382 CHAR),
  PASSWORD_HASH VARCHAR2(382 CHAR),
  FIRST_NAME VARCHAR2(382 CHAR),
  LAST_NAME VARCHAR2(382 CHAR),
  ROLE VARCHAR2(382 CHAR),
  VERSION NUMBER(10)
  CONSTRAINT MS_OPS_USER_INFO_PK PRIMARY KEY (ID),
  CONSTRAINT MS_OPS_USER_INFO_UK UNIQUE (EMAIL)
);
 */
@Entity(name="UserInfo")
@Table(name = "USER_INFO")
public class User extends AbstractEntity {

	@NotEmpty
	@Email
	@Size(max = 255)
	@Column(unique = true)
	private String email;

	@NotNull
	@Size(min = 4, max = 255)
	private String passwordHash;

	@NotBlank
	@Size(max = 255)
	private String firstName;

	@NotBlank
	@Size(max = 255)
	private String lastName;

	@NotBlank
	@Size(max = 255)
	private String role;

	@PrePersist
	@PreUpdate
	private void prepareData(){
		this.email = email == null ? null : email.toLowerCase();
	}

	public User() {
		// An empty constructor is needed for all beans
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		User that = (User) o;
		return Objects.equals(email, that.email) &&
				Objects.equals(firstName, that.firstName) &&
				Objects.equals(lastName, that.lastName) &&
				Objects.equals(role, that.role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), email, firstName, lastName, role);
	}
}
