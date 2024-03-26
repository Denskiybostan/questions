package pro.sky.questions.service;

import pro.sky.questions.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
