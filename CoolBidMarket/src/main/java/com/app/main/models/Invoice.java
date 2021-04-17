package com.app.main.models;
// Generated Apr 3, 2021, 11:02:42 AM by Hibernate Tools 5.1.10.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Invoice generated by hbm2java
 */
@Entity
@Table(name = "invoice", catalog = "data_shop")
public class Invoice implements java.io.Serializable {

	private Integer auctionId;
	private Auction auction;
	private Status status;
	private Date time;

	public Invoice() {
	}

	public Invoice(Auction auction) {
		this.auction = auction;
	}

	public Invoice(Auction auction, Status status, Date time) {
		this.auction = auction;
		this.status = status;
		this.time = time;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "auction"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "auction_id", unique = true, nullable = false)
	public Integer getAuctionId() {
		return this.auctionId;
	}

	public void setAuctionId(Integer auctionId) {
		this.auctionId = auctionId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Auction getAuction() {
		return this.auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
