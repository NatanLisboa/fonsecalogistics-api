package com.fonsecaworks.fonsecalogistics.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fonsecaworks.fonsecalogistics.domain.exception.BusinessRuleException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Delivery {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Customer customer;
	
	@Embedded
	private Recipient recipient;
	
	private String items;
	
	@NotNull
	private BigDecimal tax;
	
	@OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
	private List<Occurrence> occurrences = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;

	private OffsetDateTime orderDate;
	
	private OffsetDateTime completionDate;

	public Occurrence addOccurrence(String description) {
		Occurrence occurrence = new Occurrence();
		occurrence.setDescription(description);
		occurrence.setRegisterDate(OffsetDateTime.now());
		occurrence.setDelivery(this);
		
		this.getOccurrences().add(occurrence);
		
		return occurrence;
	}

	public void checkout() {
		
		if (cannotBeCompleted()) {
			throw new BusinessRuleException("Delivery cannot be completed");
		}
		
		this.setStatus(DeliveryStatus.COMPLETED);
		this.setCompletionDate(OffsetDateTime.now());
		
	}
	
	public boolean canBeCompleted() {
		return DeliveryStatus.PENDING.equals(this.getStatus());
	}
	
	public boolean cannotBeCompleted() {
		return !canBeCompleted();
	}

}
