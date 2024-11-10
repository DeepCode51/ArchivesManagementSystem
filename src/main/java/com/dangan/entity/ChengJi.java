
package com.dangan.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
* #成绩
*/
@Entity
@Table(name="t_chengji")
public class ChengJi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length=10)
	private Integer score;//分
	@ManyToOne
	@JoinColumn(name="userId")
	private User user ; // 哪个用户 
	@Column(length=50)
	private  String kemu;//科目 
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	 	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getKemu() {
		return kemu;
	}
	public void setKemu(String kemu) {
		this.kemu = kemu;
	}
	
	
	
	
	
}
