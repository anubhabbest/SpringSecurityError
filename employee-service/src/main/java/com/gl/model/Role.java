package com.gl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_Id")
	private int roleId;
	private String roleName;
	
//	@ManyToMany
//	@JoinTable(
//				name="user_roles",
//				joinColumns=@JoinColumn(name="role_Id"),
//				inverseJoinColumns=@JoinColumn(name="user_Id")
//				)
	@ManyToOne
	@JoinColumn(name="user_id_fk",nullable=false)
	private User users;
}
