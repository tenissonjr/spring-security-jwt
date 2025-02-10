package com.exemplo.tenissonjr.infrastructure.login.dto;

import java.util.List;

public record  UsuarioLoginDTO (
	 String ponto,
	 String nome,
	 String ramal,
	 List<String> authorities,
	 String token,
	 Long tokenExpirationTime) {}


