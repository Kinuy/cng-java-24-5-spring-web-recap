package springweb.cngjava245springwebrecap;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    IdService idService = mock(IdService.class);
    ToDoRepository todoRepository = mock(ToDoRepository.class);
    ToDoService todoService = new ToDoService(todoRepository,idService);


    @Test
    void getToDo() {
        //GIVEN
        List<ToDo> expected = List.of();
        when(todoRepository.findAll()).thenReturn(List.of());
        //WHEN
        List<ToDo> actual = todoService.getToDo();
        //THEN
        assertEquals(expected, actual);
        verify(todoRepository).findAll();

    }

    @Test
    void postToDo() {
        //GIVEN
        ToDoDto dto = new ToDoDto("1", "Test", "Test");
        ToDo expected = new ToDo("1","Test", "Test");
        when(todoRepository.save(expected)).thenReturn(new ToDo("1", "Test", "Test"));
        //WHEN
        ToDo actual = todoService.postToDo(dto);
        //THEN
        verify(todoRepository).save(expected);
        assertEquals(expected, actual);
    }

    @Test
    void getToDoById() {
        //GIVEN
        ToDoDto dto = new ToDoDto("1", "Test", "Test");
        ToDo expected = new ToDo("1","Test", "Test");
        when(todoRepository.findById("1")).thenReturn(Optional.of(new ToDo("1", "Test", "Test")));
        //WHEN
        ToDo actual = todoService.getToDoById("1");
        //THEN
        verify(todoRepository).findById("1");
        assertEquals(expected, actual);
    }

    @Test
    void putDoToById() {
        //GIVEN
        ToDoDto dto = new ToDoDto("1", "Test", "Test");
        ToDo expected = new ToDo("1","Test", "Test");
        when(todoRepository.findById("1")).thenReturn(Optional.of(new ToDo("1", "Test", "Test")));
        when(todoRepository.save(expected)).thenReturn(new ToDo("1", "Test", "Test"));
        //WHEN
        ToDo actual = todoService.putDoToById("1", dto);
        //THEN
        verify(todoRepository).findById("1");
        verify(todoRepository).save(expected);
        assertEquals(expected, actual);
    }

    @Test
    void deleteToDoById() {
        //GIVEN
        when(todoRepository.existsById("1")).thenReturn(true);

        //WHEN
        boolean actual = todoService.deleteToDoById("1");
        //THEN
        assertTrue(actual);
        verify(todoRepository).deleteById("1");
        verify(todoRepository).existsById("1");
    }
}