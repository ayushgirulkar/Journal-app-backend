package com.example.springpractice.service;

import com.example.springpractice.entity.JournalEntry;
import com.example.springpractice.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    JournalEntryRepository journalEntryRepository;

    public boolean saveEntry(JournalEntry journalEntry)
    {
        journalEntryRepository.save(journalEntry);
        return  true;
    }
    public List<JournalEntry> getAll()
    {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id)
    {
        return journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id)
    {
        journalEntryRepository.deleteById(id);
    }

}
