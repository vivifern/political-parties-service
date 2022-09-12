package com.example.demo.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Size(min = 5, max = 100)
	@Pattern(regexp = "^[a-zA-z\\s]*$")
	private String name;
	@Size(min = 5, max = 100)
	@Pattern(regexp = "^[a-zA-z\\s]*$")
	private String founderName;
	@Min(1947)
	@Max(2022)
	private Short foundationYear;
	
	protected Party() {
	}

	public Party(String name, String founderName, Short foundationYear) {
		this.name = name;
		this.founderName = founderName;
		this.foundationYear = foundationYear;
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
	public String getFounderName() {
		return founderName;
	}
	public void setFounderName(String founderName) {
		this.founderName = founderName;
	}
	public Short getFoundationYear() {
		return foundationYear;
	}
	public void setFoundationYear(Short foundationYear) {
		this.foundationYear = foundationYear;
	}
	
	@Override
	public String toString() {
		return "Party [id=" + id + ", name=" + name + ", founderName=" + founderName + ", foundationYear="
				+ foundationYear + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(foundationYear, founderName, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Party other = (Party) obj;
		return Objects.equals(foundationYear, other.foundationYear) && Objects.equals(founderName, other.founderName)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
}
