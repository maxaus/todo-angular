package com.baev.todolist.controller.rest.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.util.Date;

/**
 * To-Do list note DTO.
 *
 * @author Maxim Baev
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteDto {
	private String title;
	private String description;
	private long priority = 1;

	private boolean completed;


	private String dueDate;

	/**
	 * Gets title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets title.
	 *
	 * @param title the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets priority.
	 *
	 * @return the priority
	 */
	public long getPriority() {
		return priority;
	}

	/**
	 * Sets priority.
	 *
	 * @param priority the priority
	 */
	public void setPriority(long priority) {
		this.priority = priority;
	}

	/**
	 * Gets dueDate.
	 *
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * Sets dueDate.
	 *
	 * @param dueDate the dueDate
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * Gets completed.
	 *
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * Sets completed.
	 *
	 * @param completed the completed
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
