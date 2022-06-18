package com.jap_study_shop.model.entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//자식의 종류를 식별할 수 있는 값 설정
@DiscriminatorValue("A")
public class Album extends Item{
	
	private String artist;
	
	private String etc;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}
}
