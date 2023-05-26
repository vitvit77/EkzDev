package com.exam.vit.taskManager;

import com.exam.vit.taskManager.model.SuccessDTOResponse;
import com.exam.vit.taskManager.model.TaskManagerDTO;
import com.exam.vit.taskManager.model.TaskManagerEntity;
import com.exam.vit.taskManager.service.TaskManagerService;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TaskManagerApplication.class, TaskManagerTestConfiguration.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskManagerApplicationTests {

	public static final String TEST_TITLE = "Test Title";
	public static final String TEST_DESC = "Test description";
	public static final String DATE_FROM = "2021-01-01 21:00:00";
	public static final String DATE_TO = "2021-01-02 21:00:00";

	public static final String DATE_FROM_GET = "2023-01-01 21:00:00";
	public static final String DATE_TO_GET = "2023-01-02 21:00:00";
	public static final Integer SUCCESS_CODE = 200;
	private String url;

	@Autowired
	private TaskManagerService taskManagerService;

	@BeforeAll
	private void init(@LocalServerPort int port) {
		this.url = Strings.concat("http://localhost:", String.valueOf(port));
	}

	@Autowired
	private RestTemplate restTemplate;

	@Test
	void contextLoads() {
		assertEquals(1, 1);
	}

	@Test
	void checkCreateTask() {
		TaskManagerDTO requestDTO = new TaskManagerDTO();
		requestDTO.setTitle(TEST_TITLE);
		requestDTO.setDescription(TEST_DESC);
		requestDTO.setDate_from(Timestamp.valueOf(DATE_FROM));
		requestDTO.setDate_to(Timestamp.valueOf(DATE_TO));
		TaskManagerEntity responseDTO = restTemplate.postForObject(
				Strings.concat(url, "/api/task-manager"), new HttpEntity<>(requestDTO), TaskManagerEntity.class
		);
		assert responseDTO != null;
		assertEquals(TEST_TITLE, responseDTO.getTitle());
		assertEquals(TEST_DESC, responseDTO.getDescription());
		assertEquals(Timestamp.valueOf(DATE_FROM), responseDTO.getDate_from());
		assertEquals(Timestamp.valueOf(DATE_TO), responseDTO.getDate_to());
		taskManagerService.deleteTask(responseDTO.getId());
	}

	@Test
	void checkDeleteTask() {
		TaskManagerDTO requestDTO = new TaskManagerDTO();
		requestDTO.setTitle(TEST_TITLE);
		requestDTO.setDescription(TEST_DESC);
		requestDTO.setDate_from(Timestamp.valueOf(DATE_FROM_GET));
		requestDTO.setDate_to(Timestamp.valueOf(DATE_TO_GET));
		TaskManagerEntity taskManagerEntity = taskManagerService.createTask(requestDTO);
		String fullUrl = Strings.concat(url, Strings.concat("/api/task-manager/", String.valueOf(taskManagerEntity.getId())));
		HttpStatus statusCode = HttpStatus.valueOf(SUCCESS_CODE);
		try {
			restTemplate.delete(fullUrl, SuccessDTOResponse.class);
		} catch (HttpClientErrorException e) {
			statusCode = e.getStatusCode();
		}
		assertEquals(SUCCESS_CODE, statusCode.value());
	}

}
