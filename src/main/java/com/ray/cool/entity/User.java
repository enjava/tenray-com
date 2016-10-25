package com.ray.cool.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name="user_infoABC")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
    @OneToMany
    @JoinColumn(name = "userId")
	private Set<Books> bookses=new HashSet<Books>();

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=20,nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(length=50,nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    public Set<Books> getBookses() {
        return bookses;
    }

    public void setBookses(Set<Books> bookses) {
        this.bookses = bookses;
    }
}
