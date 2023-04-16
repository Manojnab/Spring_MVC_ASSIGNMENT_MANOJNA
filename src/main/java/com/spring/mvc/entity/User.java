package com.spring.mvc.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
	private String email;
	private String userName;
	private String password;
	@OneToMany(mappedBy = "user")
	private List<UserBook> books;
	
	
}
