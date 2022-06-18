package com.jap_study_shop.model.entity.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import com.jap_study_shop.model.entity.BaseEntity;
import com.jap_study_shop.model.entity.Category;

@Entity
//상속관계 정의 - 단일 테이블 전략
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//상속 종류를 결정하는 컬럼명 지정
@DiscriminatorColumn(name = "DTYPE")
//직접 사용하지 않고 상속받아 사용하므로 추상 클래스 선언
public abstract class Item extends BaseEntity{ 
	
	@Id
	@GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;
	
	private String name;
	
	private int price;
	
	private int stockQuantity;
	
	//관계의 주인이 아님
	//외래키 실소유 x
	@ManyToMany(mappedBy = "items")
	private List<Category> categorys = new ArrayList<Category>();

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public List<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}
}
