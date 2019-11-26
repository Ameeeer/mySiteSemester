package com.company.servlets.models;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Integer id;
    private String email;
    private String login;
    private String password;
    private String role;
    private String country;
    private String info;
    private String status;
    private String token;
//    public Integer getId() {
//        return id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public String getInfo() {
//        return info;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//    public static Builder newBuilder(){
//        return new User().new Builder();
//    }
//    public class Builder {
//        private Builder() {
//
//        }
//
//        public Builder setId(Integer id) {
//            User.this.id = id;
//            return this;
//        }
//
//        public Builder setEmail(String email) {
//            User.this.email = email;
//            return this;
//        }
//
//        public Builder setLogin(String login) {
//            User.this.login = login;
//            return this;
//        }
//
//        public Builder setPassword(String password) {
//            User.this.password = password;
//            return this;
//        }
//
//        public Builder setRole(String role) {
//            User.this.role = role;
//            return this;
//        }
//
//        public Builder setCountry(String country) {
//            User.this.country = country;
//            return this;
//        }
//
//        public Builder setInfo(String info) {
//            User.this.info = info;
//            return this;
//        }
//
//        public Builder setStatus(String status) {
//            User.this.status = status;
//            return this;
//        }
//        public User build(){
//            return User.this;
//        }
//    }
}
