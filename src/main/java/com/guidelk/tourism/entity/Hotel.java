package com.guidelk.tourism.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hotel", schema = "tourism")
public class Hotel extends SharedModel{
    private Integer hotelId;
    private String hotelName;
    private Integer categoryId;
    private Integer starGradingId;
    private Integer roomCount;
    private Date dateOfStart;
    private Integer addressBookId;

    private AddressBook addressBook;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOTEL_G1")
    @SequenceGenerator(name = "HOTEL_G1", sequenceName = "hotel_id", schema = "tourism", allocationSize = 1)
    @Column(name = "hotel_id", nullable = false, precision = 0, unique = true)
    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    @Basic
    @Column(name = "hotel_name", nullable = false)
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    @Basic
    @Column(name = "category_id", nullable = false)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "star_grading_id", nullable = false)
    public Integer getStarGradingId() {
        return starGradingId;
    }

    public void setStarGradingId(Integer starGradingId) {
        this.starGradingId = starGradingId;
    }

    @Basic
    @Column(name = "room_count", nullable = false)
    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    @Basic
    @Column(name = "date_of_start", nullable = false)
    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    @Transient
    public Integer getAddressBookId() {
        return addressBookId;
    }

    public void setAddressBookId(Integer addressBookId) {
        this.addressBookId = addressBookId;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_book_id")
    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }
}
