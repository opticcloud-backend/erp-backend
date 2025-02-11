package com.erpoticastec.backenderp.dto;

import java.util.List;

public record ResponseDTO (String email, String token, Long id_usuario, List<Long> id_oticas) { }
