package todoapp.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import todoapp.core.todo.application.FindTodos;
import todoapp.core.todo.domain.support.TodoSpreadsheetConverter;

@Controller
@RequiredArgsConstructor
public class TodoController {
    private final FindTodos findTodos;

    @GetMapping("/todos")
    public void todo(Model model) {
    }

    @GetMapping(value = "/todos", produces = "text/csv")
    public void todoCSV(Model model) {
        model.addAttribute(TodoSpreadsheetConverter.convert(findTodos.all()));
    }

}
