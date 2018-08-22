package WebApplication.Model.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "experience", schema = "nutella", catalog = "")
@NamedQueries({
        @NamedQuery(
            name = "ExperienceEntity.GetExperience",
            query = "select e from ExperienceEntity e where e.userId = :userId"
        )
})
public class ExperienceEntity {
    private long userId;
    private long experienceId;
    private String companyTitle;
    private String position;
    private Date dateFrom;
    private Date dateTo;

    @Basic
    @Column(name = "UserID")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "ExperienceID")
    public long getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(long experienceId) {
        this.experienceId = experienceId;
    }

    @Basic
    @Column(name = "CompanyTitle")
    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    @Basic
    @Column(name = "Position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "DateFrom")
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Basic
    @Column(name = "DateTo")
    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperienceEntity that = (ExperienceEntity) o;
        return userId == that.userId &&
                experienceId == that.experienceId &&
                Objects.equals(companyTitle, that.companyTitle) &&
                Objects.equals(position, that.position) &&
                Objects.equals(dateFrom, that.dateFrom) &&
                Objects.equals(dateTo, that.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, experienceId, companyTitle, position, dateFrom, dateTo);
    }
}
