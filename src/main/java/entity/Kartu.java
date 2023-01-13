/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "kartu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kartu.findAll", query = "SELECT k FROM Kartu k"),
    @NamedQuery(name = "Kartu.findById", query = "SELECT k FROM Kartu k WHERE k.id = :id"),
    @NamedQuery(name = "Kartu.findByNik", query = "SELECT k FROM Kartu k WHERE k.nik = :nik"),
    @NamedQuery(name = "Kartu.findByNama", query = "SELECT k FROM Kartu k WHERE k.nama = :nama"),
    @NamedQuery(name = "Kartu.findByTl", query = "SELECT k FROM Kartu k WHERE k.tl = :tl"),
    @NamedQuery(name = "Kartu.findByTimeStamp", query = "SELECT k FROM Kartu k WHERE k.timeStamp = :timeStamp")})
public class Kartu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NIK")
    private String nik;
    @Column(name = "NAMA")
    private String nama;
    @Column(name = "TL")
    @Temporal(TemporalType.DATE)
    private Date tl;
    @Lob
    @Column(name = "Photo")
    private byte[] photo;
    @Basic(optional = false)
    @Column(name = "TimeStamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    public Kartu() {
    }

    public Kartu(Integer id) {
        this.id = id;
    }

    public Kartu(Integer id, Date timeStamp) {
        this.id = id;
        this.timeStamp = timeStamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTl() {
        return tl;
    }

    public void setTl(Date tl) {
        this.tl = tl;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
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
        if (!(object instanceof Kartu)) {
            return false;
        }
        Kartu other = (Kartu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Kartu[ id=" + id + " ]";
    }
    
}
