package com.jefflee.entity.information;

import com.jefflee.po.information.TclassPo;

public class Tclass {
	private Integer tclassId;
	private String tclassNo;
	private String name;
	private String shortName;
	private Integer type;
	private Integer year;
	private Integer level;

	public Tclass() {
	}

	public Tclass(Integer tclassId) {
		this.tclassId = tclassId;
	}

	public Tclass(TclassPo tclassPo) {
		tclassId = tclassPo.getTclassId();
		tclassNo = tclassPo.getTclassNo();
		type = tclassPo.getType();
		year = tclassPo.getYear();
		level = tclassPo.getLevel();
	}

	public TclassPo toPo() {
		TclassPo tclassPo = new TclassPo();
		tclassPo.setTclassId(tclassId);
		tclassPo.setTclassNo(tclassNo);
		tclassPo.setType(type);
		tclassPo.setYear(year);
		tclassPo.setLevel(level);
		return tclassPo;
	}

	public Integer getTclassId() {
		return tclassId;
	}

	public void setTclassId(Integer tclassId) {
		this.tclassId = tclassId;
	}

	public String getTclassNo() {
		return tclassNo;
	}

	public void setTclassNo(String tclassNo) {
		this.tclassNo = tclassNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}
