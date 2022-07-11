package com.alexbonetskiy.restaurantvoting.dto;

import com.alexbonetskiy.restaurantvoting.HasIdAndEmail;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@EqualsAndHashCode(callSuper = true)
public class UserTo extends NamedTo implements HasIdAndEmail {


    @Email
    @NotBlank
    @Size(max = 128)
    String email;

    @NotBlank
    @Size(min = 5, max = 32)
    String password;

    @JsonCreator
    public UserTo(@JsonProperty("id") Integer id,@JsonProperty("name") String name,@JsonProperty("email") String email,@JsonProperty("password") String password) {
        super(id, name);
        this.email = email;
        this.password = password;
    }


    @Override
    public String toString() {
        return "UserTo:" + id + '[' + email + ']';
    }
}

