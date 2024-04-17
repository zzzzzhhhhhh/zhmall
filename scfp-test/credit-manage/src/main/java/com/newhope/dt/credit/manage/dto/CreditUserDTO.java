package com.newhope.dt.crpt.service.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/***
 * @author LCM
 * @date 2023/3/12 12:03
 */
public class ScfpUserDTO extends User {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String userAccount;
    private String userId;
    private String phone;
    private List<String> roleIds;


    public ScfpUserDTO(String username, String password, String userAccount, String userId, String phone) {
        super(username, password, AuthorityUtils.NO_AUTHORITIES);
        this.password = password;
        this.username = username;
        this.userAccount = userAccount;
        this.userId = userId;
        this.phone = phone;
    }

    public ScfpUserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public ScfpUserDTO(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "ScfpUserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userId='" + userId + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
