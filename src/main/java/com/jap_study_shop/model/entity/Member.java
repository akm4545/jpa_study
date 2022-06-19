package com.jap_study_shop.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Member extends BaseEntity{
	
	@Id 
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	private String name;
	
	//값 타입을 사용하므로 불필요 필드
//	private String city;
//
//	private String street;
//	
//	private String zipcode;
	
	//값 타입 사용시 선언 (사용하는 부분)
	//결과적으로는 위와 똑같은 코드임
	//값타입 컬렉션의 경우 OneToMany와 똑같이 작동
	//엔티티 = 식별자가 필요, 지속해서 값을 구분하고 변경해야 하는 경우
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<Order>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
