package com.exemplo.tenissonjr.infrastructure.login.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginParamDTO {
	private String ponto;
	private String senha;
}
