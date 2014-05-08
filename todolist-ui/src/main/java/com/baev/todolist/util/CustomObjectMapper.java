package com.baev.todolist.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.text.SimpleDateFormat;

/**
 * Custom object mapper.
 *
 * @author Maxim Baev
 */
public class CustomObjectMapper extends ObjectMapper {

	/**
	 * Default constructor.
	 */
	public CustomObjectMapper() {
		super();
		configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
		setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
	}
}

