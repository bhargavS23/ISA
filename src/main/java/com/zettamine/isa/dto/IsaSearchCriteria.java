package com.zettamine.isa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IsaSearchCriteria implements SearchCriteria {
	
	//search criteria for the recruiter
	private String recrName;
	private String recrEmail;
	private String recrPassword;
	private String interviewerId;
	
}
