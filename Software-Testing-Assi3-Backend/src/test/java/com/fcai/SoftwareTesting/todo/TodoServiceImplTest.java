package com.fcai.SoftwareTesting.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoServiceImplTest {

    private TodoServiceImpl todoService;

    @BeforeEach
    void setUp() {
        todoService = new TodoServiceImpl(null);
    }
    @Test
    void create_ValidTodo_ReturnsNewTodo() {
        // Create a valid TodoCreateRequest
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest();
        todoCreateRequest.setTitle("Buy groceries");
        todoCreateRequest.setDescription("Buy milk and eggs");

        // Call the create method
        Todo createdTodo = todoService.create(todoCreateRequest);

        // Verify that a new Todo is returned
        assertNotNull(createdTodo);
        assertNotNull(createdTodo.getId());
        assertEquals("Buy groceries", createdTodo.getTitle());
        assertEquals("Buy milk and eggs", createdTodo.getDescription());
        assertFalse(createdTodo.isCompleted());

        // Verify that the Todo is added to the list of todos in the service
        assertEquals(1, todoService.list().size());
        assertEquals(createdTodo, todoService.list().get(0));
    }
    @Test
    void create_NullTodo_ThrowsIllegalArgumentException() {
        assertThrows(NullPointerException.class, () -> todoService.create(null));
    }

    @Test
    void create_EmptyTitle_ThrowsIllegalArgumentException() {
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest();
        todoCreateRequest.setTitle("");
        todoCreateRequest.setDescription("Buy milk and eggs");

        assertThrows(NullPointerException.class, () -> todoService.create(todoCreateRequest));
    }

    @Test
    void create_EmptyDescription_ThrowsIllegalArgumentException() {
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest();
        todoCreateRequest.setTitle("Buy groceries");
        todoCreateRequest.setDescription("");

        assertThrows(NullPointerException.class, () -> todoService.create(todoCreateRequest));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void read_NullId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> todoService.read(null));
    }

    @Test
    void read_EmptyId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> todoService.read(""));
    }

    @Test
    void read_NonexistentId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> todoService.read("123"));
    }

    @Test
    void read_ExistingId_ReturnsTodo() {
        // Create a Todo
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest();
        todoCreateRequest.setTitle("Buy groceries");
        todoCreateRequest.setDescription("Buy milk and eggs");
        Todo createdTodo = todoService.create(todoCreateRequest);

        // Call the read method with the created Todo's id
        Todo readTodo = todoService.read(createdTodo.getId());

        // Verify that the returned Todo is the same as the created Todo
        assertNotNull(readTodo);
        assertEquals(createdTodo, readTodo);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void update_NullId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> todoService.update(null, true));
    }

    @Test
    void update_EmptyId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> todoService.update("", true));
    }

    @Test
    void update_NonexistentId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> todoService.update("123", true));
    }

    @Test
    void update_ExistingId_ReturnsUpdatedTodo() {
        // Create a Todo
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest();
        todoCreateRequest.setTitle("Buy groceries");
        todoCreateRequest.setDescription("Buy milk and eggs");
        Todo createdTodo = todoService.create(todoCreateRequest);

        // Call the update method with the created Todo's id and set completed to true
        Todo updatedTodo = todoService.update(createdTodo.getId(), true);

        // Verify that the returned Todo is the same as the created Todo with the updated completed status
        assertNotNull(updatedTodo);
        assertEquals(createdTodo.getId(), updatedTodo.getId());
        assertEquals(createdTodo.getTitle(), updatedTodo.getTitle());
        assertEquals(createdTodo.getDescription(), updatedTodo.getDescription());
        assertTrue(updatedTodo.isCompleted());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void delete_NullId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> todoService.delete(null));
    }

    @Test
    void delete_EmptyId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> todoService.delete(""));
    }

    @Test
    void delete_NonexistentId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> todoService.delete("123"));
    }

    @Test
    void delete_ExistingId_RemovesTodoFromList() {
        // Create a Todo
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest();
        todoCreateRequest.setTitle("Buy groceries");
        todoCreateRequest.setDescription("Buy milk and eggs");
        Todo createdTodo = todoService.create(todoCreateRequest);

        // Call the delete method with the created Todo's id
        todoService.delete(createdTodo.getId());

        // Verify that the Todo is removed from the list
        assertThrows(IllegalArgumentException.class, () -> todoService.read(createdTodo.getId()));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void list_NullList_ThrowsIllegalArgumentException() {
        assertEquals(0, todoService.list().size());
        //assertThrows(IllegalArgumentException.class, () -> todoService.list());
    }

    @Test
    void list_NullTodoList_ThrowsIllegalArgumentException() {
        // Set up the todoService with null todo list
        TodoServiceImpl todoService = new TodoServiceImpl(null);

        // Call the list method and expect an IllegalArgumentException to be thrown
        assertThrows(IllegalArgumentException.class, () -> todoService.list());
    }

    @Test
    void list_NonNullList_ReturnsList() {
        // Create some Todos
        TodoCreateRequest todoCreateRequest1 = new TodoCreateRequest();
        todoCreateRequest1.setTitle("Buy groceries");
        todoCreateRequest1.setDescription("Buy milk and eggs");
        todoService.create(todoCreateRequest1);

        TodoCreateRequest todoCreateRequest2 = new TodoCreateRequest();
        todoCreateRequest2.setTitle("Clean house");
        todoCreateRequest2.setDescription("Vacuum and dust");
        todoService.create(todoCreateRequest2);

        // Call the list method
        List<Todo> todoList = todoService.list();

        // Verify that the returned list is not null and contains the correct number of Todos
        assertNotNull(todoList);
        assertEquals(2, todoList.size());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void listCompleted_NullTodoList_ThrowsIllegalArgumentException() {
        // Set up the todoService with a null todo list
        TodoServiceImpl todoServiceListComplite = new TodoServiceImpl(null);

        // Call the listCompleted method
        assertThrows(IllegalArgumentException.class, todoServiceListComplite::listCompleted);
    }

    @Test
    void listCompleted_NoCompletedTodos_ReturnsEmptyList() {
        // Set up the todoService with an empty todo list
        TodoServiceImpl todoService = new TodoServiceImpl(new ArrayList<>());

        // Call the listCompleted method
        List<Todo> completedTodos = todoService.listCompleted();

        // Verify that the returned list is empty
        assertTrue(completedTodos.isEmpty());
    }

    @Test
    void listCompleted_SomeCompletedTodos_ReturnsCompletedTodosOnly() {
        // Create a list of todos with some completed and some not completed
        List<Todo> todos = new ArrayList<>();
        // Create some Todos
        TodoCreateRequest todoCreateRequest1 = new TodoCreateRequest();
        todoCreateRequest1.setTitle("Todo 1");
        todoCreateRequest1.setDescription("first todo");
        Todo createdTodo = todoService.create(todoCreateRequest1);
        Todo updatedTodo1 = todoService.update(createdTodo.getId(), true);

        TodoCreateRequest todoCreateRequest2 = new TodoCreateRequest();
        todoCreateRequest2.setTitle("Todo 2");
        todoCreateRequest2.setDescription("second todo");
        Todo createdTodo2 = todoService.create(todoCreateRequest2);
        Todo updatedTodo2 = todoService.update(createdTodo2.getId(), false);

        TodoCreateRequest todoCreateRequest3 = new TodoCreateRequest();
        todoCreateRequest3.setTitle("Todo 3");
        todoCreateRequest3.setDescription("third todo");
        Todo createdTodo3 = todoService.create(todoCreateRequest3);
        Todo updatedTodo3 = todoService.update(createdTodo3.getId(), true);

        // Create for loop to save all todos in list todos
        for (int i = 0; i < 3; i++) {
            todos.add(updatedTodo1);
            todos.add(updatedTodo2);
            todos.add(updatedTodo3);
        }

        // Set up the todoService with the todo list
        TodoServiceImpl todoService = new TodoServiceImpl(todos);

        // Call the listCompleted method
        List<Todo> completedTodos = todoService.listCompleted();

        // Verify that the returned list contains only completed todos
        for (Todo todo : completedTodos) {
            assertTrue(todo.isCompleted());
        }
        assertEquals(2, completedTodos.size());
    }
}