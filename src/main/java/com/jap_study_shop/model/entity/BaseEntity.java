package com.jap_study_shop.model.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;

//DB와 매핑시키진 않고 매핑시킬 정보를 상속해주는 클래스
//공통 요소가 포함되어야 할때 사용
@MappedSuperclass
public class BaseEntity {
	
	private Date createDate;
	
	private Date lastModifiedDate;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
