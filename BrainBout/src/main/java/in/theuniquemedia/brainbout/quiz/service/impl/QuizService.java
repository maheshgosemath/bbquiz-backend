package in.theuniquemedia.brainbout.quiz.service.impl;

import in.theuniquemedia.brainbout.admin.vo.AddQuestionVO;
import in.theuniquemedia.brainbout.admin.vo.QuestionVO;
import in.theuniquemedia.brainbout.common.constants.AppConstants;
import in.theuniquemedia.brainbout.common.delegate.CommonDelegate;
import in.theuniquemedia.brainbout.common.domain.Genre;
import in.theuniquemedia.brainbout.common.domain.Quiz;
import in.theuniquemedia.brainbout.common.domain.QuizOptions;
import in.theuniquemedia.brainbout.common.repository.IRepository;
import in.theuniquemedia.brainbout.common.util.CommonUtil;
import in.theuniquemedia.brainbout.quiz.service.IQuiz;
import in.theuniquemedia.brainbout.quiz.vo.OptionVO;
import in.theuniquemedia.brainbout.quiz.vo.QuizOptionVO;
import in.theuniquemedia.brainbout.quiz.vo.QuizVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mahesh on 2/22/17.
 */
@Service
public class QuizService implements IQuiz {

    @Autowired
    CommonDelegate commonDelegate;

    @Autowired
    IRepository<Quiz, Integer> quizRepository;

    @Autowired
    IRepository<QuizOptions, Integer> quizOptionsRepository;

    @Override
    @Transactional
    public List<Integer> fetchQuizList(Integer competitionSeq, Integer maxCount) {

        List<Integer> quizSeqList = new ArrayList<>();
        List<Integer> quizList = null;

        List<Genre> genreList = commonDelegate.fetchGenreList();
        if(genreList != null && genreList.size() > 0) {
            for(Genre genre: genreList) {
                quizList = fetchQuizListByGenre(competitionSeq, genre.getGenreCd(), maxCount);
                if(quizList != null && quizList.size() > 0) {
                    quizSeqList.addAll(quizList);
                }
            }
        }
        return quizSeqList;
    }

    @Transactional
    private List<Integer> fetchQuizListByGenre(Integer competitionSeq, String genreCd, Integer maxCount) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("genreCd", genreCd);
        queryParams.put("competitionSeq", competitionSeq);
        List<Integer> quizList = (List<Integer>)quizRepository.findObjectListByNamedQuery(AppConstants.FETCH_QUIZ_LIST_BY_GENRE_CD,
                queryParams, 0, maxCount);
        return quizList;
    }

    @Transactional
    public List<Quiz> fetchAllQuiz() {
        List<Quiz> quizList = quizRepository.findByNamedQuery(AppConstants.FETCH_ALL_QUIZ);
        if(quizList != null && quizList.size() > 0) {
            return quizList;
        }
        return null;
    }

    @Override
    @Transactional
    public QuizVO fetchQuiz(Integer quizSeq) {
        Quiz quiz =  fetchQuizBySeq(quizSeq);
        List<QuizOptions> optionList = fetchOptionsForQuiz(quizSeq);
        if(quiz != null && optionList != null) {
            return fetchQuizDetails(quiz, optionList);
        }
        return null;
    }

    @Transactional
    public Quiz fetchQuizBySeq(Integer quizSeq) {
        return quizRepository.findById(Quiz.class, quizSeq);
    }

    @Transactional
    public List<QuizOptions> fetchOptionsForQuiz(Integer quizSeq) {
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("quizSeq", quizSeq);
        return quizOptionsRepository.findByNamedQuery(AppConstants.FETCH_OPTIONS_FOR_QUIZ, queryParams);
    }

    @Override
    @Transactional
    public List<QuizVO> fetchQuizList(List<Integer> quizList) {
        List<QuizVO> quizVOList = new ArrayList<>();
        for(Integer quiz: quizList) {
            QuizVO quizVO = fetchQuiz(quiz);
            if(quizVO != null) {
                quizVOList.add(quizVO);
            }
        }
        return quizVOList;
    }

    @Override
    @Transactional
    public List<QuizOptionVO> fetchQuizCorrectOptionList(List<Integer> quizSeqList) {
        List<QuizOptionVO> quizOptionVOList = new ArrayList<>();
        for(Integer quizSeq: quizSeqList) {
            QuizOptionVO quizOptionVO = fetchQuizCorrectOption(quizSeq);
            if(quizOptionVO != null) {
                quizOptionVOList.add(quizOptionVO);
            }
        }
        return quizOptionVOList;
    }

    @Override
    @Transactional
    public HashMap<Integer, Integer> fetchQuizCorrectOptionMap(List<Integer> quizSeqList) {
        HashMap<Integer, Integer> correctOptions = new HashMap<>();
        for(Integer quizSeq: quizSeqList) {
            QuizOptionVO quizOptionVO = fetchQuizCorrectOption(quizSeq);
            if(quizOptionVO != null) {
                correctOptions.put(quizOptionVO.getQuizSeq(), quizOptionVO.getOptionSeq());
            }
        }
        return correctOptions;
    }

    @Override
    @Transactional
    public QuizOptionVO fetchQuizCorrectOption(Integer quizSeq) {
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("quizSeq", quizSeq);
        List<QuizOptionVO> quizOptionVOList = (List<QuizOptionVO>) quizOptionsRepository.findVOByNamedQuery(QuizOptionVO.class,
                AppConstants.FETCH_QUIZ_CORRECT_OPTION, queryParams);
        if(quizOptionVOList != null && quizOptionVOList.size() > 0) {
            return quizOptionVOList.get(0);
        }
        return null;
    }

    //TODO move to converter
    private QuizVO fetchQuizDetails(Quiz quiz, List<QuizOptions> optionList) {
        List<OptionVO> optionVOList = new ArrayList<>();
        QuizVO quizVO = new QuizVO();
        quizVO.setQuizSeq(quiz.getQuizSeq());
        quizVO.setQuizTitle(quiz.getQuizTitle());
        quizVO.setQuizImg(quiz.getImgUrl());

        for(QuizOptions option:optionList) {
            OptionVO optionVO = new OptionVO();
            optionVO.setOptionSeq(option.getQuizOptionsSeq());
            optionVO.setOptionTitle(option.getOptionTitle());
            optionVOList.add(optionVO);
        }
        quizVO.setOptionList(optionVOList);
        return quizVO;
    }

    @Override
    @Transactional
    public void createQuestions(AddQuestionVO addQuestionVO) {
        Quiz quiz = new Quiz();

        Genre genre = commonDelegate.fetchGenreBySeq(addQuestionVO.getGenreSeq());
        quiz.setQuizTitle(addQuestionVO.getQuizVO().getQuizTitle());
        quiz.setGenre(genre);
        quiz.setCompetition(null);
        quiz.setStatus(AppConstants.STATUS_ACTIVE);
        Integer quizSeq = quizRepository.save(quiz);
        saveQuizOptions(quizSeq, addQuestionVO.getQuizVO().getOptionList());
        if(addQuestionVO.getMultipartFile() != null) {
            CommonUtil.uploadFile(addQuestionVO.getMultipartFile(), "/home/brainbout/images/", AppConstants.QUIZ_PREFIX + String.valueOf(quizSeq));
            quiz.setImgUrl(AppConstants.QUIZ_PREFIX + String.valueOf(quizSeq) + addQuestionVO.getMultipartFile().getOriginalFilename());
        }
        quizRepository.merge(quiz);
    }

    @Override
    @Transactional
    public void updateQuestion(AddQuestionVO addQuestionVO) {
        Integer quizSeq = addQuestionVO.getQuizVO().getQuizSeq();
        Quiz quiz = fetchQuizBySeq(addQuestionVO.getQuizVO().getQuizSeq());

        if(quiz != null) {
            Genre genre = commonDelegate.fetchGenreBySeq(addQuestionVO.getGenreSeq());
            quiz.setQuizTitle(addQuestionVO.getQuizVO().getQuizTitle());
            quiz.setGenre(genre);
            quiz.setCompetition(null);
            quiz.setStatus(AppConstants.STATUS_ACTIVE);
            quizRepository.merge(quiz);
            inactivateOptions(quizSeq);
            saveQuizOptions(quizSeq, addQuestionVO.getQuizVO().getOptionList());
            if (addQuestionVO.getMultipartFile() != null) {
                CommonUtil.uploadFile(addQuestionVO.getMultipartFile(), "/home/brainbout/images/", AppConstants.QUIZ_PREFIX + String.valueOf(quizSeq));
                quiz.setImgUrl(AppConstants.QUIZ_PREFIX + String.valueOf(quizSeq) + addQuestionVO.getMultipartFile().getOriginalFilename());
            } else {
                quiz.setImgUrl(null);
            }
            quizRepository.merge(quiz);
        }
    }

    @Transactional
    private void inactivateOptions(Integer quizSeq) {
        List<QuizOptions> quizOptionsList = fetchOptionsForQuiz(quizSeq);
        if(quizOptionsList != null && quizOptionsList.size() > 0) {
            for(QuizOptions quizOptions: quizOptionsList) {
                quizOptions.setStatus(AppConstants.STATUS_INACTIVE);
                quizOptionsRepository.merge(quizOptions);
            }
        }
    }


    @Transactional
    public void saveQuizOptions(Integer quizSeq, List<OptionVO> optionVOList) {
        Quiz quiz = fetchQuizBySeq(quizSeq);
        for(OptionVO optionVO: optionVOList) {
            QuizOptions quizOptions = new QuizOptions();
            quizOptions.setQuiz(quiz);
            quizOptions.setOptionTitle(optionVO.getOptionTitle());
            quizOptions.setIsCorrect(optionVO.getIsCorrect());
            quizOptions.setStatus(AppConstants.STATUS_ACTIVE);
            quizOptionsRepository.save(quizOptions);
        }
    }

    @Override
    @Transactional
    public List<QuestionVO> fetchQuizList() {
        List<QuestionVO> questionVOList = new ArrayList<>();
        List<Quiz> quizList = fetchAllQuiz();
        if(quizList != null && quizList.size() > 0) {
            for(Quiz quiz: quizList) {
                QuestionVO questionVO = new QuestionVO();
                questionVO.setQuestionTitle(quiz.getQuizTitle());
                questionVO.setQuestionSeq(quiz.getQuizSeq());
                questionVO.setImgUrl(quiz.getImgUrl());
                questionVO.setGenre(quiz.getGenre().getGenreText());
                questionVOList.add(questionVO);
            }
        }
        return questionVOList;
    }

    @Override
    @Transactional
    public AddQuestionVO fetchQuizDetails(Integer quizSeq) {
        AddQuestionVO addQuestionVO = new AddQuestionVO();
        Quiz quiz = fetchQuizBySeq(quizSeq);
        if(quiz != null) {
            QuizVO quizVO = fetchQuiz(quizSeq);
            addQuestionVO.setQuizVO(quizVO);
            addQuestionVO.setGenreSeq(quiz.getGenre().getGenreSeq());
            QuizOptionVO quizOptionVO = fetchQuizCorrectOption(quizSeq);
            if(quizOptionVO != null) {
                addQuestionVO.setCorrectAnswer(quizOptionVO.getOptionSeq());
            }
        }
        return addQuestionVO;
    }
}
