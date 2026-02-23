package com.example.springpractice.controller;


import com.example.springpractice.entity.JournalEntry;
import com.example.springpractice.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public boolean createEntry(@RequestBody JournalEntry myEntry)
    {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return true;
    }
    @GetMapping("id/{myid}")
    public JournalEntry findById(@PathVariable ObjectId myid)
    {
        return journalEntryService.findById(myid).orElse(null);
    }
    @DeleteMapping("id/{myid")
    public boolean deleteById(@PathVariable ObjectId myid)
    {
        journalEntryService.deleteById(myid);
        return  true;
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
