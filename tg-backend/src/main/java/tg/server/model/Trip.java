/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.server.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.IndexColumn;

/**
 *
 * @author lokesh
 */
@Entity
@Table(name = "trip")
public class Trip implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String country;
    private String region;
    private String place;
    private String bestMonthToVisit;
    private String description;
    private Integer likes;
    private List<Review> reviews;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getBestMonthToVisit() {
        return bestMonthToVisit;
    }

    public void setBestMonthToVisit(String bestMonthToVisit) {
        this.bestMonthToVisit = bestMonthToVisit;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @IndexColumn( name = "trip_review_position", base = 0, nullable = true )
    @org.hibernate.annotations.NotFound( action = org.hibernate.annotations.NotFoundAction.IGNORE )
    @Cascade( org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trip)) {
            return false;
        }
        Trip other = (Trip) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tg.server.model.Trip[ id=" + id + " ]";
    }
    
}
