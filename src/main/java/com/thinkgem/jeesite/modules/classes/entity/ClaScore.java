/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.classes.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 成绩管理Entity
 * @author Kiritsugu
 * @version 2019-10-23
 */
public class ClaScore extends DataEntity<ClaScore> {
	
	private static final long serialVersionUID = 1L;
	private String stuId;		// stu_id
	private String lessonId;		// lesson_id
	private String lessonName;		// lesson_name
	private String score;		// score
	private String teacherId;		// teacher_id
	
	public ClaScore() {
		super();
	}

	public ClaScore(String id){
		super(id);
	}

	@Length(min=0, max=64, message="stu_id长度必须介于 0 和 64 之间")
	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	
	@Length(min=0, max=64, message="lesson_id长度必须介于 0 和 64 之间")
	public String getLessonId() {
		return lessonId;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	
	@Length(min=0, max=255, message="lesson_name长度必须介于 0 和 255 之间")
	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Length(min=0, max=255, message="teacher_id长度必须介于 0 和 255 之间")
	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
}