package WebApplication.Model.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "skills", schema = "nutella", catalog = "")
@NamedQueries({
        @NamedQuery(
                name = "SkillsEntity.GetSkills",
                query = "select s from SkillsEntity s where s.userId = :userId"
        )
})
public class SkillsEntity {
    private long userId;
    private long skillId;
    private String skill;

    @Basic
    @Column(name = "UserID")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "SkillID")
    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    @Basic
    @Column(name = "Skill")
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillsEntity that = (SkillsEntity) o;
        return userId == that.userId &&
                skillId == that.skillId &&
                Objects.equals(skill, that.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, skillId, skill);
    }
}
