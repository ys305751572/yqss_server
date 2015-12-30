package com.gcs.aol.entity;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.gcs.sysmgr.entity.IdAbstartEntity;

@Entity
@Table(name="helper")
public class Helper extends IdAbstartEntity{

	private static final long serialVersionUID = -3323521562724225441L;

	@Column(name="type")
	private String type;
	
	@Column(name="question")
	private String question;
	
	@Column(name="answer")
	private String answer;
	
	@Column(name="create_date")
	private Long createDate = System.currentTimeMillis();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
}
