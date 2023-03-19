package server.api.services;

import commons.Task;
import commons.TaskList;
import commons.models.CreateTaskModel;
import org.springframework.stereotype.Service;
import server.database.ListRepository;
import server.database.TaskRepository;
import server.exceptions.CannotCreateTask;
import server.exceptions.ListDoesNotExist;
import server.exceptions.TaskDoesNotExist;

import java.util.List;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
	private final ListRepository listRepository;

	public TaskService(TaskRepository taskRepository, ListRepository listRepository) {
		this.taskRepository = taskRepository;
		this.listRepository = listRepository;
	}

	/**
	 * Gets all tasks from the database
	 * @return list of all tasks
	 */
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	/**
	 * Finds a task by a given id
	 * @param id - the id of the task that we want to get
	 * @return the task with the given id
	 * @throws TaskDoesNotExist - when there is no task with the given id in the db
	 */
	public Task getTaskById(long id) throws TaskDoesNotExist {
		if (!taskRepository.existsById(id))
			throw new TaskDoesNotExist("There exists no task with the provided id.");
		return taskRepository.findById(id).get();
	}

	/**
	 * Creates a task from a given model
	 * @param model - model containing name and the id of the list for the task
	 * @return The newly created task
	 * @throws ListDoesNotExist - when there is no list with the given id
	 */
	public Task createTask(CreateTaskModel model) throws ListDoesNotExist, CannotCreateTask {
		if (!listRepository.existsById(model.taskListId)) {
			throw new ListDoesNotExist("There is no list with the provided id.");
		}
		TaskList taskList = listRepository.getById(model.taskListId);
		Task task = taskList.createTask(model.name);
		return taskRepository.save(task);
	}

	/**
	 * Deletes a task from the database
	 * @param id - the id of the task that we want to delete
	 * @throws TaskDoesNotExist - when there is no task with the given id
	 */
	public void deleteTaskById(long id) throws TaskDoesNotExist {
		if (!taskRepository.existsById(id))
			throw new TaskDoesNotExist("There is no task with the provided id.");
		taskRepository.deleteById(id);
	}

}
