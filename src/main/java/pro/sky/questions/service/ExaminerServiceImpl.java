package pro.sky.questions.service;

import pro.sky.questions.exceptions.NotEnoughQuestionException;
import pro.sky.questions.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ExaminerServiceImpl implements ExaminerService {
    public final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount){
        var allQuestions = questionService.getAll();
        if (amount > allQuestions.size()) {
            throw new NotEnoughQuestionException();
        }
        if (amount == questionService.getAll().size()) {
            return allQuestions;
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }

        return questions;
    }
}
