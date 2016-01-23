package com.android3.icontacts;

import java.io.Serializable;

public class Contact implements Serializable{
	private static final long serialVersionUID=7865096419340919344L;
	Integer img=null;
	Integer imgt=null;
	Integer id=null;
	String name = null;
	String note = null;
	String mobph = null;
	String telph = null;
	String addr = null;
	String email = null;
	String firm = null;
	String blog = null;
	
	public Contact(){}
	
	public Contact(Integer img,Integer imgt,Integer id,String name, String note, String mobph, String telph, String addr, String email,String firm, String blog){
		this.img=img;
		this.imgt=imgt;
		this.id=id;
		this.name = name;
		this.note = note;
		this.mobph = mobph;
		this.telph = telph;
		this.addr = addr;
		this.email = email;
		this.firm = firm;
		this.blog = blog;
	}
	
	
	public Integer getimg() {
	
		return img;
	}
	
	public Integer setimg(Integer img) {
		this.imgt=img;
		return	this.img = img;
	}
	public Integer getid() {
		return id;
	}
	
	public Integer setid(Integer id) {
		return	this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public String setName(String name) {
		return	this.name = name;
	}

	public String getnote() {
		return note;
	}

	public String setnote(String note) {
		return this.note = note;
	}
	
	public String getmobph() {
		return mobph;
	}

	public String setmobph(String mobph) {
		return this.mobph = mobph;
	}

	public String gettelph() {
		return telph;
	}

	public String settelph(String telph) {
		return this.telph = telph;
	}

	public String getaddr() {
		return addr;
	}

	public String setaddr(String addr) {
		return this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public String setEmail(String email) {
		return this.email = email;
	}

	public String getfirm() {
		return firm;
	}

	public String setfirm(String firm) {
		return this.firm = firm;
	}
	
	public String getBlog() {
		return blog;
	}

	public String setBlog(String blog) {
		return this.blog = blog;
	}
}

