package com.example.fleetmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {
    @NotEmpty
    @Pattern(regexp = "^(P|C)", message = "Barcode must be correct format.")
    private String barcode;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer state;

}
