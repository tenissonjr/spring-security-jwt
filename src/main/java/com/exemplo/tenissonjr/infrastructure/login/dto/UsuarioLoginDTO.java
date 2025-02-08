package com.exemplo.tenissonjr.infrastructure.login.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsuarioLoginDTO {
	private String ponto;
	private String nome;
	private String ramal;
	private List<String> authorities;
	private String token;
	private Long tokenExpirationTime;	

}
