package in.theuniquemedia.brainbout.common.constants;

/**
 * Created by mahesh on 2/22/17.
 */
public interface AppConstants {
    public final Character STATUS_ACTIVE='A';
    public final Character STATUS_INACTIVE='I';
    public final String FACEBOOK_USER_AGENT = "facebookexternalhit";
    public final String LINKEDIN_USER_AGENT = "LinkedInBot";
    public final String TWITTER_USER_AGENT = "Twitterbot";
    public final String WHATSAPP_AGENT = "WhatsApp";
    public final String QUIZ_PREFIX="_QUIZSeq_";
    public final String USER_NOT_ELIGIBLE="USERNOTELIGIBLE";
    public final String USER_STATUS_SUBMITTED="submitted";

    public final String FETCH_QUIZ_LIST="Quiz.fetchQuizList";
    public final String FETCH_QUIZ_INFO="Quiz.fetchQuizInfo";
    public final String FETCH_OPTIONS_FOR_QUIZ="QuizOptions.fetchOptionsForQuiz";
    public final String FETCH_USER_BY_EMAIL="Participant.fetchUserByEmail";
    public final String FETCH_GENRE_LIST="Genre.findGenreList";
    public final String FETCH_COMPANY_LIST="Company.findCompanyList";
    public final String FETCH_COMPETITION_LIST="CompanyCompetition.findCompetitionList";
    public final String FETCH_QUIZ_LIST_BY_GENRE_CD="Quiz.fetchQuizListByGenreCd";
    public final String FETCH_USER_COMPETITION_DETAILS="CompetitionParticipant.fetchUserCompetitionDetails";
    public final String FETCH_USER_COMPETITION_DETAILS_BY_TOKEN="CompetitionParticipant.fetchUserCompetitionDetailsByToken";
    public final String FETCH_QUIZ_CORRECT_OPTION="QuizOptions.fetchQuizCorrectOption";
    public final String FETCH_COMPANY_COMPETITION_DETAILS="CompanyCompetition.fetchCompanyCompetitionDetails";
    public final String FETCH_COMPANY_COMPETITION_BY_SEQ="CompanyCompetition.fetchCompanyCompetitionDetailsBySeq";
    public final String FETCH_COMPANY_DOMAIN="CompanyDomain.fetchCompanyDomain";
    public final String FETCH_ALL_QUIZ="Quiz.fetchAllQuiz";
}
