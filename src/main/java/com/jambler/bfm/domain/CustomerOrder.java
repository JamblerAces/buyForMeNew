package com.jambler.bfm.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CustomerOrder {


    private Long id;
    private Date creationDate;
    private Date statusUpdateDate;
    private int status;

    private Customer customer;
    private Manager manager;

    @OneToOne
    private OrderItem item;

    public CustomerOrder() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStatusUpdateDate() {
        return statusUpdateDate;
    }

    public void setStatusUpdateDate(Date statusUpdateDate) {
        this.statusUpdateDate = statusUpdateDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("creationDate", creationDate)
                .add("statusUpdateDate", statusUpdateDate)
                .add("status", status)
                .add("customer", customer)
                .add("manager", manager)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerOrder that = (CustomerOrder) o;

        return Objects.equal(this.id, that.id) &&
                Objects.equal(this.creationDate, that.creationDate) &&
                Objects.equal(this.statusUpdateDate, that.statusUpdateDate) &&
                Objects.equal(this.status, that.status) &&
                Objects.equal(this.customer, that.customer) &&
                Objects.equal(this.manager, that.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, creationDate, statusUpdateDate, status, customer, manager);
    }

    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
