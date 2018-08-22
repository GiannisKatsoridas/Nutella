package WebApplication.Model.Requests;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UpdateExperienceRequest {

    private long experienceId;
    private String companyTitle;
    private String position;
    private java.sql.Date DateFrom;
    private java.sql.Date DateTo;

    public long getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(long experienceId) {
        this.experienceId = experienceId;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        DateFrom = dateFrom;
    }

    public Date getDateTo() {
        return DateTo;
    }

    public void setDateTo(Date dateTo) {
        DateTo = dateTo;
    }
}
