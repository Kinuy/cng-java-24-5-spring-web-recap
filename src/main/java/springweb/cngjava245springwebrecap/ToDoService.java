package springweb.cngjava245springwebrecap;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final IdService idService;

    public ToDoService(ToDoRepository toDoRepository,IdService idService){
        this.toDoRepository = toDoRepository;
        this.idService = idService;
    }

    public List<ToDo> getToDo() {

        return toDoRepository.findAll();
    }

    public ToDo postToDo(ToDoDto toDoDto) {
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

    public boolean deleteToDoById(String id) {
        //toDoRepository.delete(toDoRepository.findById(id).get());

        if (toDoRepository.existsById(id)){
            toDoRepository.deleteById(id);
            return true;
        }else {
            throw new NoSuchElementException("No ToDo found with Id:" + id);
        }
    }
}
