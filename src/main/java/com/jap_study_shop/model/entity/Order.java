package com.jap_study_shop.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ORDERS")
public class Order {
	
	@Id 
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;
	
	//연관관계의 주인이 아님 - 외래키 소유 X
	//제네릭 타입 오더 아이템을 찾은 뒤 
	//대상 테이블과 오더 필드를 찾고 
	//조인 컬럼을 확인 후 다시 돌아와 Order에 선언된 컬럼 아이디를 기준으로 쿼리를 생성하여 가져오는듯함
	@OneToMany(mappedBy = "order") // 필드 이름
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	//연관관계의 주인 - 외래키 소유 O
	//Get Member 시 member_id로 조인하여 가져옴
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID") //컬럼 이름
	private Member member;
	
	//일대일 관계 
	//관계의 주인 
	@OneToOne
	@JoinColumn(name = "DELIVERY_ID")
	private Delivery delivery;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Member getMember() {
		return member;
	}
	
	//오더 1이 회원 1에 추가되어 있을 시 
	//오더 1이 회원 2로 변경되었을때 회원 1에 남아있는
	//오더 1의 관계를 끊기 위함
	//이렇게 바꾸는 상황이 자주 있을지는 의문
	public void setMember(Member member) {
		
		if(this.member != null) {
			this.member.getOrders().remove(this);
		}
		
		this.member = member;
		member.getOrders().add(this);
	}
	
	//오더 아이템을 추가시키면 
	//오더에서 오더 아이템 리스트에 저장 
	//추가된 오더 아이템에도 오더를 저장
	//양방향 연관관계 성립
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	public Delivery getDelivery() {
		return delivery;
	}

	//양방향 관계 설정
	//배송지 정보를 입력하면 
	//배송지에도 주문 정보가 세팅됨
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}
}

