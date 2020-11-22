package org.acme.quarkus.sample;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name="PRODUTO")
public class Produto extends PanacheEntity {
	public String name;
	public BigDecimal value;
	
	@CreationTimestamp
	public Date dateCreation;
	@UpdateTimestamp
	public Date dateUpdate;

}
