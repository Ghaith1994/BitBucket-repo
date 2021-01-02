package org.blockchain.entity;

import org.hibernate.annotations.Formula;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author Ghaith Hahi <geath-hahi@hotmail.com>
 */

@Entity
public class User extends BaseEntity implements Serializable {

    @Column(nullable = false)
    @NotNull(message = "name type is mandatory")
    private String name;

    @Email
    @Column(nullable = false,unique = true)
    @NotNull(message = "email is mandatory")
    private  String email;

    @Column(nullable = false)
    @NotNull(message = "password is mandatory")
    private String password;

    @Formula("(select  IFNULL(sum(e.credit) - sum(e.debit), 0) from voucher_entry e where e.user_id = id)")
    private Double vc ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getVc() {
        return vc;
    }

    @Override
    public String getLabel() {
        return this.name;
    }
}
