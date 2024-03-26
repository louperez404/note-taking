package com.example.notetaking.utils.validation;

import com.example.notetaking.note.NoteRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoteCheckRecordExistValidator implements ConstraintValidator<NoteCheckRecordExist, Long> {

    private final NoteRepository noteRepository;

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return noteRepository.findById(aLong).isPresent();
    }

}
