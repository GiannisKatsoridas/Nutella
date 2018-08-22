package WebApplication.Model.Responses;


import WebApplication.Model.Entities.EducationEntity;
import WebApplication.Model.Entities.ExperienceEntity;
import WebApplication.Model.Entities.SkillsEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetPersonalInfoResponse {

    private List<ExperienceEntity> experience;
    private List<EducationEntity> education;
    private List<SkillsEntity> skills;

    public List<ExperienceEntity> getExperience() {
        return experience;
    }

    public void setExperience(List<ExperienceEntity> experience) {
        this.experience = experience;
    }

    public List<EducationEntity> getEducation() {
        return education;
    }

    public void setEducation(List<EducationEntity> education) {
        this.education = education;
    }

    public List<SkillsEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillsEntity> skills) {
        this.skills = skills;
    }

    public GetPersonalInfoResponse(List<ExperienceEntity> experience, List<EducationEntity> education, List<SkillsEntity> skills) {
        this.experience = experience;
        this.education = education;
        this.skills = skills;
    }
}
