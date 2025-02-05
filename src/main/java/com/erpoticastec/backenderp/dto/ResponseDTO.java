package com.erpoticastec.backenderp.dto;

import java.util.List;

public record ResponseDTO (String email, String token, List<Long> id_oticas) { }
