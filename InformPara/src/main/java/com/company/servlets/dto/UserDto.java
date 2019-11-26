package com.company.servlets.dto;

import lombok.Builder;

@Builder
public class UserDto implements DtoInterface {
    private String email;
    private String role;
    private Long id;
    private String infoAboutUser;
    private String country;
    private String status;
    private String token;

//    private UserDto() {
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getInfoAboutUser() {
//        return infoAboutUser;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//
//    public static Builder newBuilder(){
//        return new UserDto().new Builder();
//    }
//    public class Builder {
//        private Builder() {
//        }
//
//        public Builder setStatus(String status) {
//            UserDto.this.status = status;
//            return this;
//        }
//        public Builder setEmail(String email) {
//            UserDto.this.email = email;
//            return this;
//        }
//
//        public Builder setRole(String role) {
//            UserDto.this.role = role;
//            return this;
//        }
//
//        public Builder setId(Long id) {
//            UserDto.this.id = id;
//            return this;
//        }
//
//        public Builder setInfoAboutUser(String infoAboutUser) {
//            UserDto.this.infoAboutUser = infoAboutUser;
//            return this;
//        }
//
//        public Builder setCountry(String country) {
//            UserDto.this.country = country;
//            return this;
//        }
//        public UserDto build(){
//            return UserDto.this;
//        }
//    }
}
