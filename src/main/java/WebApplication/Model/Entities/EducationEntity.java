package WebApplication.Model.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "education", schema = "nutella", catalog = "")
@NamedQueries({
        @NamedQuery(
                name = "EducationEntity.GetEducation",
                query = "select e from EducationEntity e where e.userId = :userId"
        )
})
public class EducationEntity implements Serializable {
    private long userId;
    private long educationId;
    private String degree;
    private String institution;
    private String yearFrom;
    private String yearTo;

    @Basic
    @Column(name = "UserID")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "EducationID")
    public long getEducationId() {
        return educationId;
    }

    public void setEducationId(long educationId) {
        this.educationId = educationId;
    }

    @Basic
    @Column(name = "Degree")
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Basic
    @Column(name = "Institution")
    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    @Basic
    @Column(name = "YearFrom")
    public String getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(String yearFrom) {
        this.yearFrom = yearFrom;
    }

    @Basic
    @Column(name = "YearTo")
    public String getYearTo() {
        return yearTo;
    }

    public void setYearTo(String yearTo) {
        this.yearTo = yearTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationEntity that = (EducationEntity) o;
        return userId == that.userId &&
                educationId == that.educationId &&
                Objects.equals(degree, that.degree) &&
                Objects.equals(institution, that.institution) &&
                Objects.equals(yearFrom, that.yearFrom) &&
                Objects.equals(yearTo, that.yearTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, educationId, degree, institution, yearFrom, yearTo);
    }
}
