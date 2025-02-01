package todoapp.core.todo.domain.support;

import todoapp.core.foundation.util.StreamUtils;
import todoapp.core.shared.util.Spreadsheet;
import todoapp.core.todo.domain.Todo;

/**
 * 할일 목록을 {@link Spreadsheet} 모델로 변환하는 변환기이다.
 *
 * @author springrunner.kr@gmail.com
 */
public class TodoSpreadsheetConverter {

    public static Spreadsheet convert(Iterable<Todo> todos) {
        var header = Spreadsheet.Row.of("id", "text", "completed");

        var todoStream = StreamUtils.createStreamFromIterator(todos.iterator());
        var rows = todoStream.map(TodoSpreadsheetConverter::mapRow).toList();

        return new Spreadsheet("todos", header, rows);
    }

    private static Spreadsheet.Row mapRow(Todo todo) {
        return Spreadsheet.Row.of(
                todo.getId(),
                todo.getText(),
                todo.isCompleted() ? "Completed" : "To Do"
        );
    }

    // 외부에서 생성을 막기 위해 비공개 기본 생성자를 선언했다
    private TodoSpreadsheetConverter() {

    }

}
