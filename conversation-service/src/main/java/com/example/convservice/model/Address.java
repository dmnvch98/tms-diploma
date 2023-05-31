package com.example.convservice.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.NumberFormat;

@Value
@Builder
@Table("addresses")
@Jacksonized
public class Address {
    @Id
    @NumberFormat
    Long addressId;
    @Column("address")
    String address;
    @Column("city")
    String city;
    @Column("lat")
    String latitude;
    @Column("long")
    String longitude;
    @Column("tutor_id")
    Long tutorId;
    @Column("country_id")
    Long countryId;

}
