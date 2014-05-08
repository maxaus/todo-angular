package com.baev.todolist.controller.rest;

import com.baev.todolist.controller.rest.dto.NoteDto;
import com.baev.todolist.domain.model.Note;
import com.baev.todolist.domain.model.User;
import com.baev.todolist.service.AuthenticationService;
import com.baev.todolist.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * UI controller to manage to-do list notes.
 *
 * @author Maxim Baev
 */
@Controller
@RequestMapping(value = "/api/note", produces = "application/json; charset=utf-8")
public class NoteController {

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * Note service.
	 */
	@Autowired
	private NoteService noteService;

	/**
	 * Authentication info service.
	 */
	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * Returns note by ID.
	 *
	 * @param noteId note ID
	 * @return Note
	 */
	@RequestMapping(value = "/view/{id:\\d+}", method = RequestMethod.GET)
	@ResponseBody
	public Note getNote(@PathVariable("id") Long noteId) {
		return noteService.findById(noteId);
	}

	/**
	 * Returns list of notes for current user.
	 *
	 * @return List of notes.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getNotesForCurrentUser(@RequestParam(required = false,
			defaultValue = "CREATED_DATE") String sortField, @RequestParam(required = false,
			defaultValue = "DESC") String sortDir) {

		Note.SortField sortFieldEnum = null;
		try {
			sortFieldEnum = Note.SortField.valueOf(sortField);
		} catch (IllegalArgumentException e) {
			sortFieldEnum = Note.SortField.CREATED_DATE;
			e.printStackTrace();
		}

		Map<String, Object> res = new HashMap<>();
		User user = authenticationService.getCurrentUser();

		res.put("list", noteService.findByUser(user.getId(), 0, Integer.MAX_VALUE, sortFieldEnum.getFieldName(), sortDir));
		res.put("isOk", true);

		return res;
	}

	/**
	 * Persists new note.
	 *
	 * @param dto new note DTO
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveNote(@RequestBody NoteDto dto) throws ParseException {
		Map<String, Object> res = new HashMap<>();

		Note note = new Note();
		note.setTitle(dto.getTitle());
		note.setDescription(dto.getDescription());
		note.setPriority(dto.getPriority());
		note.setDueDate(new SimpleDateFormat(DATE_FORMAT).parse(dto.getDueDate()));

		note.setCreatedBy(authenticationService.getCurrentUser());
		noteService.create(note);

		res.put("isOk", true);

		return res;
	}

	/**
	 * Updates existing note.
	 *
	 * @param noteId note ID
	 */
	@RequestMapping(value = "/update/{id:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateNote(@RequestBody NoteDto dto, @PathVariable("id") Long noteId) throws ParseException {
		Map<String, Object> res = new HashMap<>();

		Note note = noteService.findById(noteId);
		note.setDescription(dto.getDescription());
		note.setTitle(dto.getTitle());
		note.setPriority(dto.getPriority());
		note.setCompleted(dto.isCompleted());
		note.setDueDate(new SimpleDateFormat(DATE_FORMAT).parse(dto.getDueDate()));

		noteService.update(note);

		res.put("isOk", true);

		return res;
	}

	/**
	 * Removes note.
	 *
	 * @param noteId note ID
	 */
	@RequestMapping(value = "/delete/{id:\\d+}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteNot(@PathVariable("id") Long noteId) {
		noteService.delete(noteId);
	}

	/**
	 * Copies main properties from one note to another one.
	 *
	 * @param oldNote note
	 * @param newNote note
	 */
	private void copyProperties(Note oldNote, Note newNote) {
		newNote.setTitle(oldNote.getTitle());
		newNote.setDescription(oldNote.getDescription());
		newNote.setPriority(oldNote.getPriority());
	}
}
