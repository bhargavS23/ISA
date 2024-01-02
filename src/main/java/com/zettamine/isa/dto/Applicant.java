package com.zettamine.isa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applicant implements IsaDto {
	private Integer applicantId;
	private String applicantName;
	private String applicantEmail;
	private String applicantSkill;
	private String applicantPhNo;
	private String applicantQualification;
	private String stream;
	private String applicantRemarks;
	private Double applicantAggrePercentage;
	

}
