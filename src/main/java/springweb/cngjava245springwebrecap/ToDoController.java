package springweb.cngjava245springwebrecap;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService){
        this.toDoService = toDoService;
    }

    @GetMapping("/todo")
    public List<ToDo> getToDos(){
        return toDoService.getToDo();
    }

    @GetMapping("/todo/{id}")
    public ToDo getToDoById(@PathVariable String id){
        return toDoService.getToDoById(id);
    }

    @PostMapping("/todo")
    public ToDo postToDo(@RequestBody ToDoDto toDoDto){
        return toDoService.postDoTo(toDoDto);
    }

    @PutMapping("/todo/{id}")
    public ToDo putToDoById(@PathVariable String id, @RequestBody ToDoDto toDoDto){
        return toDoService.putDoToById(id,toDoDto);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteToDoById(@PathVariable String id){
        toDoService.deleteToDoById(id);
    }


}
