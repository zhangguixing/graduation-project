package com.jefflee.entity.shiro;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {
	/**
	 * "title" : "二级菜单演示", "icon" : "&#xe61c;", "href" : "", "spread" : false,
	 * "children" : [
	 */
	private String title;
	private String icon;
	private String href;
	private String spread;
	private List<Menu> children;

	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getSpread() {
		return spread;
	}

	public void setSpread(String spread) {
		this.spread = spread;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Menu [title=" + title + ", icon=" + icon + ", href=" + href + ", spread=" + spread + ", children="
				+ children + "]";
	}

}
