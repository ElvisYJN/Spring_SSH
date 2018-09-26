package com.xinhuanet.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tb_evt_attachment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.xinhuanet.app.entity.Attachment")
public class Attachment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3005850540191677801L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 稿件来源类型 0稿源库,1签发库
	@Column(length = 2)
	private Integer libid;

	// 附件标题
	@Column(length = 1000)
	private String title;

	// 附件描述
	@Column(length = 1000)
	private String description;

	// 附件路径
	@Column(length = 512, nullable = false)
	private String attpath;

	// 附件类型 2：标题图片 1：正文中的图片 0：普通附件（CEB、PDF、WORD、多媒体文件等
	@Column(length = 2, nullable = false)
	private Integer type;

	// 附件文件扩展名 用于区分文件类型
	@Column(length = 100)
	private String extraname;

	// 文件类型 titlepic,pic,bigpic,smallpic,video,flash,document,htm
	@Column(length = 20)
	private String atttype;

	// 标题图显示顺序
	@Column(length = 2)
	private String titlepicsort;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLibid() {
		return libid;
	}

	public void setLibid(Integer libid) {
		this.libid = libid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAttpath() {
		return attpath;
	}

	public void setAttpath(String attpath) {
		this.attpath = attpath;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getExtraname() {
		return extraname;
	}

	public void setExtraname(String extraname) {
		this.extraname = extraname;
	}

	public String getAtttype() {
		return atttype;
	}

	public void setAtttype(String atttype) {
		this.atttype = atttype;
	}

	public String getTitlepicsort() {
		return titlepicsort;
	}

	public void setTitlepicsort(String titlepicsort) {
		this.titlepicsort = titlepicsort;
	}
}
