package com.gl.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private long userId;
	
	private String username;
	
	private String password;
	
	private String emailAddress;
	
	@OneToMany(mappedBy="users",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Role> roles;
	
	public void addRole(Role role)
	{
		if(this.roles==null)
		{
			this.roles=new HashSet<>();
			
		}
		this.roles.add(role);
		role.setUsers(this);
		
	}
}
