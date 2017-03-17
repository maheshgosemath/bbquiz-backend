package in.theuniquemedia.brainbout.common.constants;

/**
 * Created by mahesh on 2/24/17.
 */
public interface ResponseCode {
    public final String COMPETITION_CLOSED="competition_closed";
    public final String COMPETITION_NOT_STARTED="competition_not_started";
    public final String COMPETITION_RUNNING="competition_running";

    public final String EMAIL_VERIFICATION_SUCCESSFUL="VERIFY_SUCCESS";
    public final String VERIFICATION_TOKEN_NOT_FOUND="INVALID_TOKEN";
    public final String EMAIL_ALREADY_VERIFIED="EXISTING_EMAIL";
    public final String EMAIL_VERIFICATION_ERROR="VERIFY_ERROR";

    public final String LOGIN_VERIFICATION_PENDING="VERIFY_PENDING";
    public final String LOGIN_USER_NOT_EXISTS="INVALID_EMAIL";
    public final String VERIFIED_USER="VALID_EMAIL";
    public final String LOGIN_SUCCESS="LOGIN_SUCCESS";
}
