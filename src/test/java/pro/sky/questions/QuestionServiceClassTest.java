package pro.sky.questions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pro.sky.questions.exceptions.NotFoundQuestionsException;
import pro.sky.questions.model.Question;
import pro.sky.questions.service.QuestionServiceClass;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class QuestionServiceClassTest {
    @Mock
    @InjectMocks
    QuestionServiceClass service;

    @BeforeEach
    void setUp() {
        when(service.getAll()).thenReturn(List.of(
                new Question("test1","answer2"),
                new Question("test2","answer3"),
                new Question("test3","answer4")));
    }
    @Test
    void testRandomQuestion() {

    }
    @Test
    void testEmptyQuestions() {
        when(service.getAll()).thenReturn(List.of());


        assertThrows(NotFoundQuestionsException.class, () -> service.getRandomQuestion());

    }
}
