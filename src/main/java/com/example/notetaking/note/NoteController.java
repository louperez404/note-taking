package com.example.notetaking.note;

import com.example.notetaking.utils.validation.NoteCheckRecordExist;
import com.example.notetaking.utils.validation.group.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public void create(@Validated(PostValidation.class) @RequestBody Note note) {
        noteService.save(note);
    }

    @PutMapping
    public void update(@Validated(PutValidation.class) @RequestBody Note note) {
        noteService.save(note);
    }

    @DeleteMapping("/{id}")
    public void delete(@NoteCheckRecordExist @PathVariable Long id) {
        noteService.remove(id);
    }


    @GetMapping
    public Page<Note> getNotes(@RequestParam Integer index, @RequestParam Integer limit) {
        return noteService.getNotes(index, limit);
    }

    @GetMapping("/{id}")
    public Note getNoteById(@NoteCheckRecordExist @PathVariable Long id) {
        return noteService.getNoteById(id);
    }
}
