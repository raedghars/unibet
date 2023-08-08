package com.kindredgroup.unibetlivetest.types.Request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BetRequest {
    private Long selectionId;
    private BigDecimal cote;
    private double mise;
}
