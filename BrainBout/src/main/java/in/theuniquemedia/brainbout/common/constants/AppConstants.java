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
    public final String COMPETITION_STATUS_ACTIVE="ACTIVE";
    public final String COMPETITION_STATUS_CLOSED="CLOSED";
    public final String COMPETITION_STATUS_UPCOMING="UPCOMING";

    public final String FETCH_QUIZ_LIST="Quiz.fetchQuizList";
    public final String FETCH_QUIZ_INFO="Quiz.fetchQuizInfo";
    public final String FETCH_OPTIONS_FOR_QUIZ="QuizOptions.fetchOptionsForQuiz";
    public final String FETCH_USER_BY_EMAIL="Participant.fetchUserByEmail";
    public final String FETCH_GENRE_LIST="Genre.findGenreList";
    public final String FETCH_COMPANY_LIST="Company.findCompanyList";
    public final String FETCH_COMPETITION_LIST="CompanyCompetition.findCompetitionList";
    public final String FETCH_QUIZ_LIST_BY_GENRE_CD="Quiz.fetchQuizListByGenreCd";
    public final String FETCH_QUIZ_LIST_BY_GENRE_SEQ="Quiz.fetchQuizListByGenreSeq";
    public final String FETCH_SPONSORED_QUIZ_LIST="Quiz.fetchSponsoredQuizList";
    public final String FETCH_USER_COMPETITION_DETAILS="CompetitionParticipant.fetchUserCompetitionDetails";
    public final String FETCH_USER_COMPETITION_DETAILS_BY_TOKEN="CompetitionParticipant.fetchUserCompetitionDetailsByToken";
    public final String FETCH_QUIZ_CORRECT_OPTION="QuizOptions.fetchQuizCorrectOption";
    public final String FETCH_COMPANY_COMPETITION_DETAILS="CompanyCompetition.fetchCompanyCompetitionDetails";
    public final String FETCH_COMPANY_COMPETITION_BY_SEQ="CompanyCompetition.fetchCompanyCompetitionDetailsBySeq";
    public final String FETCH_COMPANY_DOMAIN="CompanyDomain.fetchCompanyDomain";
    public final String FETCH_ALL_QUIZ="Quiz.fetchAllQuiz";
    public final String FETCH_ACTIVE_COMPETITION_BY_COMPANY="CompanyCompetition.fetchActiveCompetitionByCompanyLocation";
    public final String FETCH_CLOSED_COMPETITION_BY_COMPANY="CompanyCompetition.fetchClosedCompetitionByCompanyLocation";
    public final String FETCH_UPCOMING_COMPETITION_BY_COMPANY="CompanyCompetition.fetchUpcomingCompetitionByCompanyLocation";
    public final String FETCH_COMPETITION_BY_TOKEN="CompanyCompetition.fetchCompetitionByToken";
    public final String FETCH_COMPETITION_COUNT="CompetitionParticipant.fetchCompetitionParticipantCount";
    public final String FETCH_COMPANY_TOP_PARTICIPANTS="CompetitionParticipant.fetchTopParticipants";
    public final String FETCH_COMPETITION_TOP_PARTICIPANTS="CompetitionParticipant.fetchTopCompetitionParticipants";
    public final String FETCH_COMPETITION_TOP_PARTICIPANTS_BY_LOCATION="CompetitionParticipant.fetchTopCompetitionParticipantsByLocation";
    public final String FETCH_USER_PROFILE_DETAIL_BY_SEQ="UserProfileDetail.fetchUserProfileDetailBySeq";
    public final String FETCH_USER_TOKEN="UserToken.fetchUserToken";
    public final String FETCH_ALL_LOCATIONS="LocationMstr.fetchAllLocations";
    public final String FETCH_LOCATIONS_BY_COMPANY="CompanyLocation.fetchLocationsByCompany";
    public final String FETCH_USER_PROFILE_BY_USER_ID="UserProfile.fetchUserProfileByUserId";
    public final String FETCH_USER_GENRE="UserGenre.fetchUserGenre";
}
