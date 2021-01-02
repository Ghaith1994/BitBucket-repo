package org.blockchain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.blockchain.extra.serialization.IdLabelSerializer;
import org.blockchain.extra.serialization.IdSerializer;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author Ghaith Hahi <geath-hahi@hotmail.com>
 */

@Entity
public class VoucherEntry extends BaseEntity implements Serializable {

    @ManyToOne
    @JsonSerialize(using = IdSerializer.class)
    Voucher voucher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = IdLabelSerializer.class)
    User user;

    @Column(nullable = false)
    @Min(0)
    @ColumnDefault("0")
    @NotNull(message = "debit is mandatory")
    double debit = 0d;

    @Column(nullable = false)
    @Min(0)
    @ColumnDefault("0")
    @NotNull(message = "credit is mandatory")
    double credit = 0d;

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
