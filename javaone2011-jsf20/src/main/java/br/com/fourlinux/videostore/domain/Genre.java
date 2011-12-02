package br.com.fourlinux.videostore.domain;

public enum Genre {
	DRAMA("Drama"), CRIME("Crime"), ACTION("Action"), ADVENTURE("Adventure");

	private String value;

	Genre(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
