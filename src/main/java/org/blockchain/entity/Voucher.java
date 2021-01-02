package org.blockchain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.blockchain.extra.enums.VoucherType;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Ghaith Hahi <geath-hahi@hotmail.com>
 */

@Entity
public class Voucher extends BaseEntity implements Serializable {

    @Column(nullable = false)
    @NotNull(message = "date is mandatory")
    private Date date = new Date();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "type is mandatory")
    VoucherType type;

    @OneToMany(mappedBy = "voucher")
    List<VoucherEntry> entries;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public VoucherType getType() {
        return type;
    }

    public void setType(VoucherType type) {
        this.type = type;
    }

    public List<VoucherEntry> getEntries() { return entries; }

    public void setEntries(List<VoucherEntry> entries) {
        this.entries = entries;
    }

    @JsonIgnore
    public Map<String, Double> getCreditEntries(){
        Map<String, Double> results = new HashMap<>();
        getEntries().stream().filter(e -> e.getCredit() > 0).
                forEach(ce -> results.put(ce.getUser().getName(), ce.getCredit()));
        return results;

    }

    @JsonIgnore
    public List<String> getDebitEntries(){
        List<String> results = new LinkedList<>();
        getEntries().stream().filter(e -> e.getDebit() > 0 ).
                forEach(ce -> results.add(ce.getUser().getName()));
        return results;
    }
}
