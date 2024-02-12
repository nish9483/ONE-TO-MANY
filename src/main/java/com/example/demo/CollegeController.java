package com.example.demo;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CollegeController {
	
	@Autowired
	private CollegeRepo collegeRepo;
	
	
	@PostMapping("/api/college")
	public ResponseEntity<College>saveCollege(@RequestBody College college)
	{
		College c=collegeRepo.save(college);
		return new  ResponseEntity<College>(c,HttpStatus.CREATED);
	}

	@GetMapping("/api/college")
	public ResponseEntity<List<College>>getAllAddresses()
	{
		List<College>ad=collegeRepo.findAll();
		return new ResponseEntity<List<College>>(ad,HttpStatus.OK);
	}
	
	@GetMapping("/api/college/{id}")
	public ResponseEntity<College>getCollegeById(@PathVariable int id)
	{
		Optional<College>clg=collegeRepo.findById(id);
		if(clg.isPresent())
		{
			return new ResponseEntity<College>(clg.get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<College>(clg.get(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("api/college/{id}")
	public ResponseEntity<College>updateCollege(@RequestBody College updatedCollege ,@PathVariable int id)
	{
		Optional<College>clg=collegeRepo.findById(id);
		if(clg.isPresent())
		{
			College c=clg.get();
			c.setCollegeName(updatedCollege.getCollegeName());
			c.setStreet(updatedCollege.getStreet());
			College cl=collegeRepo.save(c);
			return new ResponseEntity<College>(cl,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<College>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/api/college/{id}")
	public ResponseEntity<Void>deleteCollege(@PathVariable int id)
	{
		Optional<College>clg=collegeRepo.findById(id);
		if(clg.isPresent())
		{
			collegeRepo.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		}
	}

