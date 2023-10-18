package com.emagia.ach.entity.staples_emagia;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CashCustomerId implements Serializable {
    private static final long serialVersionUID = -486402715949360233L;
    @Column(name = "CASH_CUS_ID", nullable = false, length = 50)
    private String cashCusId;

    @Column(name = "CASH_CUS_COMPANY_ID", nullable = false, length = 100)
    private String cashCusCompanyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CashCustomerId entity = (CashCustomerId) o;
        return Objects.equals(this.cashCusCompanyId, entity.cashCusCompanyId) &&
                Objects.equals(this.cashCusId, entity.cashCusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cashCusCompanyId, cashCusId);
    }

}