package com.codeproof.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User implements Serializable {
	
	@Id
	private String id;

	@Indexed(unique=true, direction=IndexDirection.DESCENDING, dropDups=true)
	private String userName;
	
	private String password;
	private String firstName;
	private String lastName;
	private String status;
	private Boolean enabled;
	
	@DBRef
	private List<Role> roles = new ArrayList<Role>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	public void removeRole(Role role) {
		//use iterator to avoid java.util.ConcurrentModificationException with foreach
		for (Iterator<Role> iter = this.roles.iterator(); iter.hasNext(); )
		{
		   if (iter.next().equals(role))
		      iter.remove();
		}
	}
	
	public String getRolesCSV() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<Role> iter = this.roles.iterator(); iter.hasNext(); )
		{
		   sb.append(iter.next().getRoleId());
		   if (iter.hasNext()) {
			   sb.append(',');
		   }
		}
		return sb.toString();
	}	
	
	/*public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        User rhs = (User) obj;
        return new EqualsBuilder().append(id, rhs.id).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(id).append(userName).toHashCode();
    }*/
	
}
