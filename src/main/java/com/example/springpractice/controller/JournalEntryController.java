package com.example.springpractice.controller;


import com.example.springpractice.entity.JournalEntry;
import com.example.springpractice.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll()
    {
        return journalEntryService.getAll();
    }
    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry)
    {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
            }
        catch (Exception e)
        {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        }
    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> findById(@PathVariable ObjectId myid)
    {
        Optional<JournalEntry>journalEntry=journalEntryService.findById(myid);
        if(journalEntry.isPresent())
        {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("id/{myid}")
    public ResponseEntity<JournalEntry> deleteById(@PathVariable ObjectId myid)
    {
        journalEntryService.deleteById(myid);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("id/{myid}")
    public JournalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry)
    {
        JournalEntry old=journalEntryService.findById(id).orElse (null);
        if(old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }
}
