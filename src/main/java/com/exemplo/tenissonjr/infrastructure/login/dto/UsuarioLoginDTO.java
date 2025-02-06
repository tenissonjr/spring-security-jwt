package com.exemplo.tenissonjr.infrastructure.login.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsuarioLoginDTO {
	private String ponto;
	private String nome;
	private String token;
	private Long tokenExpirationTime;	

}
