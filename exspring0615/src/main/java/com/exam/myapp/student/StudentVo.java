package com.exam.myapp.student;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//표준 명세 Bean Validation 2.0부터 사용가능
//@NotEmpty, @NotBlank, @Email
//@Positive, @PositiveOrZero, @Negative, @NegativeOrZero
//@Future, @FutureOrPresent, @Past, @PastOrPresent

public class StudentVo {
	@NotBlank @Size(min = 1, max = 50) @Email
	private String stuNo;
	@NotNull @Size(min = 1, max = 50, message = "학생이름은 1~50글자로 입력하세요" )
	private String stuName;
	@Digits(integer = 10, fraction = 0)
	private int stuScore;
	
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getStuScore() {
		return stuScore;
	}
	public void setStuScore(int stuScore) {
		this.stuScore = stuScore;
	}
}
