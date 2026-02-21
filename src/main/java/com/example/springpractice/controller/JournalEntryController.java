package com.example.springpractice.controller;


import com.example.springpractice.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry>journalEntries=new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll()
    {
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry)
    {
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }
    @GetMapping("id/{myid}")
    public JournalEntry findById(@PathVariable long myid)
    {
        return journalEntries.get(myid);
    }
    @DeleteMapping("id/{myid")
    public boolean deleteById(@PathVariable long myid)
    {
        journalEntries.remove(myid);
        return  true;
    }
    @PutMapping("id/{myid}")
    public boolean deleteById(@PathVariable long myid,@RequestBody JournalEntry MyEntry)
    {
        journalEntries.put(myid,MyEntry);
        return  true;
    }
}
