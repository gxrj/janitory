package io.github.gxrj.janitory.core.Call;

import io.github.gxrj.janitory.core.Address.Address;
import io.github.gxrj.janitory.core.Dept.Dept;
import io.github.gxrj.janitory.core.Duty.Duty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
@JsonNaming( PropertyNamingStrategies.SnakeCaseStrategy.class )
@JsonIgnoreProperties( value = "createdAt", allowGetters = true )
public class CallDto implements Serializable {

    Duty duty;
    String status;
    Address address;
    String protocol;
    String createdAt;
    Dept destination;
    String description;
    String authorEmail;

    @JsonValue
    public static CallDto serialize( Call call ) {

        return CallDto.builder()
                      .duty( call.getDuty() )
                      .address( call.getAddress() )
                      .protocol( call.getProtocol() )
                      .destination( call.getDestination() )
                      .description( call.getDescription() )
                      .status( call.getStatus().getValue() )
                      .authorEmail( call.getAuthor().getEmail() )
                      .createdAt( call.getCreationDate().toString() )
                      .build();
    }
}
