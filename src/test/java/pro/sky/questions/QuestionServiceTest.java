package pro.sky.questions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.questions.exceptions.NotFoundQuestionsException;
import pro.sky.questions.model.Question;
import pro.sky.questions.service.QuestionServiceClass;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class QuestionServiceTest {
    QuestionServiceClass questionServiceClass = new QuestionServiceClass();

    @Test
    void testAdd() {
        questionServiceClass.add("pen", "write");
        questionServiceClass.add("book", "read");
        questionServiceClass.add("weather", "cold");
        assertEquals(3, questionServiceClass.getAll().size());
        assertThat(questionServiceClass.getAll()).containsExactlyInAnyOrder(
                new Question("summer", "sun"),
                new Question("winter", "snow"),
                new Question("rain", "water")
        );
    }


    @Test
    void testRemove() {
        questionServiceClass.add("What is your name?", "Jack");
        questionServiceClass.add("How are you?", "Fine");
        var removed = questionServiceClass.remove(new Question("What is your name?", "Jack"));
        assertThat(removed).isEqualTo(new Question("What is your name?", "Jack"));
        var empty = questionServiceClass.remove(new Question("Go", "Do"));
        assertThat(empty).isNull();
        assertThat(questionServiceClass.getAll()).containsExactlyInAnyOrder(
                new Question("How are you?", "Fine")
        );
    }

    static List<Question> QUESTIONS = List.of(
            new Question("What is your name?", "Jack"),
            new Question("How are you?", "Fine"),
            new Question("How long you sleep?", "8 hours"));

    @Test
    public void testGetRandomQuestion() {

        questionServiceClass.add("What is your name?", "Jack");
        questionServiceClass.add("How are you?", "Fine");
        questionServiceClass.add("How long you sleep?", "8 hours");
        for (int i = 0; i < 1000; i++) {
            assertTrue(QUESTIONS.contains(questionServiceClass.getRandomQuestion()));
        }

    }

    @Test
    void testEmptyQuestions() {
        Assertions.assertNotNull(questionServiceClass.getAll());
        assertThrows(NotFoundQuestionsException.class, () -> questionServiceClass.getRandomQuestion());
    }
}



