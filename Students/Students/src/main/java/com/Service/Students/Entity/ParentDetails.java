package com.Service.Students.Entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentDetails {
    private String p_name;
    private String p_contact;
    private String p_address;
}