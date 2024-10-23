package springweb.cngjava245springwebrecap;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;
    public ToDoService(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getToDo() {

        return toDoRepository.findAll();
    }

    public ToDo postDoTo(ToDoDto toDoDto) {
        ToDo todo = new ToDo(toDoDto.id(),toDoDto.description(),toDoDto.status());
        return toDoRepository.save(todo);
    }

    public ToDo getToDoById(String id) {
        return toDoRepository.findById(id).get();
    }


    public ToDo putDoToById(String id,ToDoDto toDoDto) {
        ToDo todo = toDoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ToDo not found"));
        ToDo toDoToUpdate = todo
                .withDescription(toDoDto.description())
                .withStatus(toDoDto.status())
                .withId(toDoDto.id());
        return toDoRepository.save(toDoToUpdate);
    }

    public void deleteToDoById(String id) {
        toDoRepository.delete(toDoRepository.findById(id).get());
    }
}
