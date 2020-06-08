package com.appero.streamradio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SingleStation.
 */
@Entity
@Table(name = "single_station")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SingleStation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "url", nullable = false)
    private String url;

    @NotNull
    @Column(name = "license", nullable = false, unique = true)
    private String license;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "singleStations", allowSetters = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SingleStation name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public SingleStation url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLicense() {
        return license;
    }

    public SingleStation license(String license) {
        this.license = license;
        return this;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public User getUser() {
        return user;
    }

    public SingleStation user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SingleStation)) {
            return false;
        }
        return id != null && id.equals(((SingleStation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SingleStation{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            ", license='" + getLicense() + "'" +
            "}";
    }
}
