//package net.guides.springboot2.springboot2jpacrudexample;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//import net.guides.springboot2.springboot2jpacrudexample.model.Party;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.client.HttpClientErrorException;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PartyControllerIntegrationTest {
//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@LocalServerPort
//	private int port;
//
//	private String getRootUrl() {
//		return "http://localhost:" + port;
//	}
//
//	@Test
//	public void contextLoads() {
//
//	}
//
//	@Test
//	public void testGetAllParties() {
//		HttpHeaders headers = new HttpHeaders();
//		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//
//		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/parties",
//				HttpMethod.GET, entity, String.class);
//
//		assertNotNull(response.getBody());
//	}
//
//	@Test
//	public void testGetPartyById() {
//		Party party = restTemplate.getForObject(getRootUrl() + "/parties/1", Party.class);
//		System.out.println(party.getFirstName());
//		assertNotNull(party);
//	}
//
//	@Test
//	public void testCreateParty() {
//		Party party = new Party();
//		party.setEmailId("admin@gmail.com");
//		party.setFirstName("admin");
//		party.setLastName("admin");
//
//		ResponseEntity<Party> postResponse = restTemplate.postForEntity(getRootUrl() + "/parties", party, Party.class);
//		assertNotNull(postResponse);
//		assertNotNull(postResponse.getBody());
//	}
//
//	@Test
//	public void testUpdateParty() {
//		int id = 1;
//		Party party = restTemplate.getForObject(getRootUrl() + "/parties/" + id, Party.class);
//		party.setFirstName("admin1");
//		party.setLastName("admin2");
//
//		restTemplate.put(getRootUrl() + "/parties/" + id, party);
//
//		Party updatedParty = restTemplate.getForObject(getRootUrl() + "/parties/" + id, Party.class);
//		assertNotNull(updatedParty);
//	}
//
//	@Test
//	public void testDeleteParty() {
//		int id = 2;
//		Party party = restTemplate.getForObject(getRootUrl() + "/parties/" + id, Party.class);
//		assertNotNull(party);
//
//		restTemplate.delete(getRootUrl() + "/parties/" + id);
//
//		try {
//			party = restTemplate.getForObject(getRootUrl() + "/parties/" + id, Party.class);
//		} catch (final HttpClientErrorException e) {
//			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
//		}
//	}
//}
