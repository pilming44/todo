package todoapp.web;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import todoapp.core.shared.identifier.TodoId;
import todoapp.core.todo.application.AddTodo;
import todoapp.core.todo.application.FindTodos;
import todoapp.core.todo.application.ModifyTodo;
import todoapp.core.todo.application.RemoveTodo;
import todoapp.core.todo.domain.Todo;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/todos")
public class TodoRestController {
    private final FindTodos findTodos;
    private final AddTodo addTodo;
    private final ModifyTodo modifyTodo;
    private final RemoveTodo removeTodo;

    @GetMapping
    public List<Todo> todos() {
        return findTodos.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid TodoRecord todoRecord) {
        log.debug("todoRecord.text >>>>>>>>>>>>>>> {}", todoRecord.text);
        addTodo.add(todoRecord.text);
    }

    @PutMapping("/{id}")
    public void modify(@PathVariable("id") String id, @RequestBody @Valid TodoRecord todoRecord) {
        log.debug("id >>>>>>>>>>>>>>> {}", id);
        log.debug("todoRecord >>>>>>>>>>>>>>> text : {}, completed : {}", todoRecord.text, todoRecord.completed);
        modifyTodo.modify(TodoId.of(id), todoRecord.text, todoRecord.completed);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        log.debug("id >>>>>>>>>>>>>>> {}", id);
        removeTodo.remove(TodoId.of(id));
    }

    public record TodoRecord(@NotBlank @Size(min = 4, max = 50) String text, boolean completed) {
        public TodoRecord(String text, boolean completed) {
            this.text = text.trim();
            this.completed = completed;
        }
    }
}
