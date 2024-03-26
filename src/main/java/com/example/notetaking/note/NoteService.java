package com.example.notetaking.note;

import com.example.notetaking.exception.NoteException;
import com.example.notetaking.exception.code.NoteExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public void save(Note note) {
        noteRepository.save(note);
    }

    public void remove(Long id) {
        var note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteException(NoteExceptionCode.EX_NOTE_NOT_FOUND));
        noteRepository.delete(note);
    }

    public Page<Note> getNotes(Integer index, Integer limit) {
        return noteRepository.findAll(PageRequest.of(index, limit));
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteException(NoteExceptionCode.EX_NOTE_NOT_FOUND));
    }
}
