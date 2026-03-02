package com.example.springpractice.controller;


import com.example.springpractice.entity.JournalEntry;
import com.example.springpractice.entity.User;
import com.example.springpractice.service.JournalEntryService;
import com.example.springpractice.service.UserService;
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
     @Autowired
    UserService userService;
    @GetMapping("{userName}")
    public ResponseEntity<?> getAll(@PathVariable String userName)
    {
        User user = userService.findByUserName(userName);
       List<JournalEntry>all=user.getJournalEntries();
       if(all!=null && !all.isEmpty())
       {
           return  new ResponseEntity<>(all,HttpStatus.FOUND);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName)
    {
        try {

            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry,userName);
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
    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<JournalEntry> deleteById(@PathVariable ObjectId myid,@PathVariable String userName)
    {
        journalEntryService.deleteById(myid,userName);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("id/{userName}/{myid}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry,@PathVariable String userName)
    {
        JournalEntry old=journalEntryService.findById(myid).orElse (null);
        if(old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
}
