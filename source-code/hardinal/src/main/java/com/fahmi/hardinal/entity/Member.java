package com.fahmi.hardinal.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Member {
    
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    
    @Column(nullable = false)
    @NotEmpty(message = " YOur Fullname is required and cannot be empty")
    @Size(min = 3,max = 150, message = "\"${validatedValue}\" is ${validatedValue.length() < 3 ? 'way' : ''} too short.")
    @Pattern(regexp = "[a-z-A-Z- ]*", message = "Fullname has invalid characters")
    private String fullname;
    
    @Column(nullable = false)
    @NotEmpty(message = "Your Nickname is required and cannot be empty")
    @NotNull
    @Size(min = 3,max = 30, message = "\"${validatedValue}\" is ${validatedValue.length() < 3 ? 'way' : ''} too short.")
    @Pattern(regexp = "[a-z-A-Z]*", message = "nickname has invalid characters")
    private String nickname;
    
    @Column(nullable = false, unique = true)
    @Email (message = "Please fill the valid Email")
    @NotNull
    @NotEmpty (message = "Your Email is required and cannot be emptyd")
    private String email;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @Past(message = "Your Date of Birth must be the past")
    @NotNull(message = "Your Birthdate is required and cannot be empty")
    private Date birthdate;
    
    @Column(nullable = false)
    @NotNull
    @Size( min = 10, max = 15, message = "\"${validatedValue}\" is ${validatedValue.length() < 11 ? 'way' : ''} too short.")
    @NotEmpty(message = " Your Phone Number is required and cannot be empty")
    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Number Only")
    private String phoneno;
    
    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "Your Address is required and cannot be empty")
    private String address;
    
    @Column(nullable = false)
    @NotNull
    @NotEmpty (message = "Your Password is required and cannot be empty")
    private String passwd;

        public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
    
    
    
}
