package com.baev.todolist.domain.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import sun.misc.Sort;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity represents TO-DO list item.
 *
 * @author Maxim Baev
 */
@Entity
@Table(name = "note")
public class Note extends AbstractEntity {

	public static enum SortField {
		DUE_DATE("dueDate"), PRIORITY("priority"), CREATED_DATE("createdDate") ;

		private final String fieldName;

		public String getFieldName() {
			return fieldName;
		}

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}
	}

	private static final long serialVersionUID = -4959209410186671959L;

	/**
	 * Title
	 */
	@Column(name = "title", nullable = false)
	private String title;

	/**
	 * Description
	 */
	@Column(name = "description", columnDefinition = "text")
	private String description;

	/**
	 * Is completed.
	 */
	@Column(name = "completed")
	private boolean completed;

	/**
	 * Updated date
	 */
	@Column(name = "due_date")
	private Date dueDate;

	/**
	 * Updated date
	 */
	@Column(name = "updated_date")
	private Date updatedDate;

	/**
	 * User created note
	 */
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User createdBy;

	/**
	 * Note priority.
	 */
	@Column(name = "priority")
	private long priority = 1;

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
	 * Gets dueDate.
	 *
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * Sets dueDate.
	 *
	 * @param dueDate the dueDate
	 */
	public void setDueDate(Date dueDate) {
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

	/**
	 * Gets updated date.
	 *
	 * @return the updated date
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * Sets updated date.
	 *
	 * @param updatedDate the updated date
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Gets created by.
	 *
	 * @return the created by
	 */
	public User getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets created by.
	 *
	 * @param createdBy the created by
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
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
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
