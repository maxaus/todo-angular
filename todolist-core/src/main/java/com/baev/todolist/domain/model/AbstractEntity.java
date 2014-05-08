package com.baev.todolist.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Base entity class.
 *
 * @author Maxim Baev
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = -2237503133198046207L;

	/**
	 * Entity identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Created date
	 */
	@Column(name = "created_date")
	private Date createdDate;

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Gets creation date
	 *
	 * @return the creation date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets created date.
	 *
	 * @param createdDate the creation user date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
