package com.spring.mvc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user_book")
@NoArgsConstructor
@Getter
@Setter
@IdClass(ProjectAssociationId.class)
public class UserBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int autoId;
	private String email;
	private int id;
	private String isLiked;
	private String isReadLater;
	
	@ManyToOne
	//@PrimaryKeyJoinColumn(name="email", referencedColumnName="ID")
	 @JoinColumn(name = "email", updatable = false, insertable = false)
	private User user;
	@ManyToOne
	//@PrimaryKeyJoinColumn(name="id", referencedColumnName="ID")
	 @JoinColumn(name = "id", updatable = false, insertable = false)
	private Book book;

}
