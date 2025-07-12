package com.cetpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="micro_user")
public class User
{
	@Id
	@Column(name="ID")
private String userId;
	@Column(name="NAME",length=20)
private String name;
	@Column(name="EMAIL")
private String email;
	@Column(name="ABOUT")
private String about;
	
	@Transient
	private List<Rating>rating=new ArrayList<>();
	//other user properties that you want;
	
}
