package in.theuniquemedia.brainbout.common.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 4/2/17.
 */
public class CompetitionDetailsVO implements Serializable {
    private String competitionTitle;
    private String competitionSubTitle;
    private String competitionText;

    public CompetitionDetailsVO() {
    }

    public CompetitionDetailsVO(String competitionTitle, String competitionSubTitle, String competitionText) {
        this.competitionTitle = competitionTitle;
        this.competitionSubTitle = competitionSubTitle;
        this.competitionText = competitionText;
    }

    public String getCompetitionTitle() {
        return competitionTitle;
    }

    public void setCompetitionTitle(String competitionTitle) {
        this.competitionTitle = competitionTitle;
    }

    public String getCompetitionSubTitle() {
        return competitionSubTitle;
    }

    public void setCompetitionSubTitle(String competitionSubTitle) {
        this.competitionSubTitle = competitionSubTitle;
    }

    public String getCompetitionText() {
        return competitionText;
    }

    public void setCompetitionText(String competitionText) {
        this.competitionText = competitionText;
    }
}
