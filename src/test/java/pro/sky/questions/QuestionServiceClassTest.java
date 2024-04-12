package pro.sky.questions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.questions.exceptions.NotEnoughQuestionException;
import pro.sky.questions.exceptions.NotFoundQuestionsException;
import pro.sky.questions.model.Question;
import pro.sky.questions.service.ExaminerServiceImpl;
import pro.sky.questions.service.QuestionServiceClass;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class QuestionServiceClassTest {
        static List<Question> JAVA_QUESTIONS = List.of(
                new Question("book", "read"),
                new Question("song", "sing"),
                new Question("program", "write"));

        @Mock
        QuestionServiceClass questionServiceClass;
        ExaminerServiceImpl examinerService;

        @BeforeEach
        void setUp() {
            examinerService = new ExaminerServiceImpl(questionServiceClass);
            when(questionServiceClass.getAll()).thenReturn(JAVA_QUESTIONS);
        }

        @Test
        void testNotEnoughQuestion() {
            assertThrows(NotEnoughQuestionException.class, () -> examinerService.getQuestions(100000));
        }

        @Test
        void testRandomQuestion() {
            when(questionServiceClass.getRandomQuestion())
                    .thenReturn(JAVA_QUESTIONS.get(0))
                    .thenReturn(JAVA_QUESTIONS.get(1));


            var actual = examinerService.getQuestions(2);

            assertThat(actual).containsExactlyInAnyOrder(
                    JAVA_QUESTIONS.get(0),
                    JAVA_QUESTIONS.get(1));
        }
    }



