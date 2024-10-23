package springweb.cngjava245springwebrecap;

import org.springframework.stereotype.Service;

@Service
public class ToDoService {
    ToDoRepository toDoRepository;
    public ToDoService(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }
}
