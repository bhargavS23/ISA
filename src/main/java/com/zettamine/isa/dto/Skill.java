package com.zettamine.isa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill implements IsaDto, SearchCriteria{
	private Integer skillId;
	private String skillDsec;
}
