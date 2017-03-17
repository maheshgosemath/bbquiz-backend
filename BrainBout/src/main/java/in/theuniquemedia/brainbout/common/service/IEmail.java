package in.theuniquemedia.brainbout.common.service;

import in.theuniquemedia.brainbout.common.vo.EmailVO;

/**
 * Created by mahesh on 3/16/17.
 */
public interface IEmail {
    public boolean sendMail(EmailVO emailVO);
}
