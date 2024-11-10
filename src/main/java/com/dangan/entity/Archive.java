
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
* 档案管理
*/
@Entity
@Table(name = "t_archive")
public class Archive {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length=30)
	private  String name;//
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;// 学生id
	@Column(length=50)
	private  String bianhao;//档案编号
	
	
	@Column(length=330)
	private  String luqu;//录取通知书  ，基础档案
	@Column(length=330)
	private  String  tijian;//体检表，基础档案
	@Column(length=330)
	private  String gaokao;//高考档案，基础档案
	@Column(length=330)
	private  String dengji;//入学登记表，基础档案
	
	
	
	
	
	public String getLuqu() {
		return luqu;
	}
	public void setLuqu(String luqu) {
		this.luqu = luqu;
	}
	public String getTijian() {
		return tijian;
	}
	public void setTijian(String tijian) {
		this.tijian = tijian;
	}
	public String getGaokao() {
		return gaokao;
	}
	public void setGaokao(String gaokao) {
		this.gaokao = gaokao;
	}
	public String getDengji() {
		return dengji;
	}
	public void setDengji(String dengji) {
		this.dengji = dengji;
	}
	public String getBianhao() {
		return bianhao;
	}
	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
}


