package springweb.cngjava245springwebrecap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ToDoRepository repo;

    @Test
    void getToDos()  throws Exception {
        //GIVEN
        repo.deleteAll();
        //WHEN & THEN
        mvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getToDoById() throws Exception {
        //GIVEN
        ToDo newTodo = new ToDo("1", "Test", "Test");
        repo.save(newTodo);

        //WHEN & THEN
        mvc.perform(get("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "description": "Test",
                            "status": "Test"
                        }
                        """));
    }

    @Test
    void postToDo()   throws Exception {
        //GIVEN
        //WHEN & THEN
        mvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "description": "Test",
                            "status": "Test"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                           "description": "Test",
                           "status": "Test"
                        }
                        """
                ))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void putToDoById() throws Exception{
        //GIVEN
        ToDo newTodo = new ToDo("1", "Test", "Test");
        repo.save(newTodo);
        //WHEN & THEN
        mvc.perform(put("/api/todo/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                "description": "Test",
                "status": "Fail"
                }
                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                  {
                   "description": "Test",
                   "status": "Fail"
                   }
                                 """));
    }

    @Test
    void deleteToDoById()  throws Exception{
        //GIVEN
        ToDo newTodo = new ToDo("1", "Test", "Test");
        repo.save(newTodo);
        //WHEN & THEN
        mvc.perform(delete("/api/todo/1"))
                .andExpect(status().isOk());

        mvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}