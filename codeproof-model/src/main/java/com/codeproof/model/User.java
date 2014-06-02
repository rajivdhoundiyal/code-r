package com.codeproof.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Entity
@Table(name="user")
public class User implements Serializable {
	
	@Id
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "user_id")
	private String userId;

	@Indexed(unique=true, direction=IndexDirection.DESCENDING, dropDups=true)
	@Column(name = "user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="status")
	private String status;
	
	@Column(name="enabled")
	private Boolean enabled;
	
	@DBRef
	@Transient
	private List<UserRole> roles;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void addRole(UserRole role) {
		this.roles.add(role);
	}
	
	public void removeRole(UserRole role) {
		//use iterator to avoid java.util.ConcurrentModificationException with foreach
		for (Iterator<UserRole> iter = this.roles.iterator(); iter.hasNext(); )
		{
		   if (iter.next().equals(role))
		      iter.remove();
		}
	}
	
	public String getRolesCSV() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<UserRole> iter = this.roles.iterator(); iter.hasNext(); )
		{
		   sb.append(iter.next().getRoleId());
		   if (iter.hasNext()) {
			   sb.append(',');
		   }
		}
		return sb.toString();
	}	
	
}
