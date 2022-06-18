package com.jap_study_shop.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jap_study_shop.model.entity.item.Item;

@Entity
public class Category extends BaseEntity{
	
	@Id
	@GeneratedValue
	@Column(name = "CATEGORY_ID")
	private Long id;
	
	private String name;
	
	//다대다 매핑 테이블을 생략하고 관계를 맺음
	//JoinTable name = 매핑 테이블 이름 
	//joinColumns = 정방향에서 조인할 기준의 키
	//inverseJoinColumns = 역방향에서 조인할 기준의 키
	//매핑 테이블의 관계를 건너뛰고 아이템들을 불러옴
	@ManyToMany
	@JoinTable(name = "CATEGORY_ITEM",
		joinColumns = @JoinColumn(name = "CATEGORY_ID"),
		inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
	)
	private List<Item> items = new ArrayList<Item>();
	
	//계층구조 DB의 부모 요소를 가져옴
	//부모 계층의 아이디를 기준으로 자신에게 조인함 
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	private Category parent;
	
	//계층구조 DB의 자식 요소를 가져옴
	//부모가 관계의 주인??
	@OneToMany(mappedBy = "parent")
	private List<Category> child = new ArrayList<Category>();

	//관계 편의 메서드
	//관계 그래프 탐색시 (양방향) 사용
	//하위 카테고리에 추가
	//하위 카테고리에도 상위 카테고리 추가
	public void addChildCategory(Category child) {
		this.child.add(child);
		child.setParent(child);
	}
	
	//카테고리 목록에 상품 추가
	public void addItem(Item itme) {
		items.add(itme);
	}
	
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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public List<Category> getChild() {
		return child;
	}

	public void setChild(List<Category> child) {
		this.child = child;
	}	
}
