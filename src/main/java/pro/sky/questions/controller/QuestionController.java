package pro.sky.questions.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pro.sky.questions.model.Question;
import pro.sky.questions.service.QuestionService;

import java.util.Collection;

public class QuestionController {
    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }


    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return service.add(question, answer);


    }

    @GetMapping("/remove")
    public Question remove(@RequestParam String question, @RequestParam String answer) {
        return service.remove(new Question(question, answer));
    }
    @GetMapping
    public Collection<Question> getAll() {
        return service.getAll();
    }
}
