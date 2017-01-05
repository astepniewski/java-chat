package pl.stepniewski.java_chat_contracts;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class SerializeService {
	ObjectMapper mapper;

	public SerializeService() {
		mapper = new ObjectMapper();
	}

	public HashMap<Integer, String> StringToHashMap(String string1) throws IOException {

		TypeReference<HashMap<Integer, String>> typeRef = new TypeReference<HashMap<Integer, String>>() {
		};

		return mapper.readValue(new ByteArrayInputStream(string1.getBytes("UTF-8")), typeRef);
	}

	public String ObjectToString(String[] object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public String[] StringToObject(String stringObj) {

		try {
			return mapper.readValue(new String(stringObj), String[].class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
