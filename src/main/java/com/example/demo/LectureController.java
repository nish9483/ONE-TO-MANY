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

public class LectureController {
	@Autowired
	private LectureRepo lectureRepo;
	
	
	@PostMapping("/api/lecture")
	public ResponseEntity<Lecture>saveLecture(@RequestBody Lecture lecture)
	{
		Lecture l=lectureRepo.save(lecture);
		return new  ResponseEntity<Lecture>(l,HttpStatus.CREATED);
	}

	@GetMapping("/api/lecture")
	public ResponseEntity<List<Lecture>>getAllAddresses()
	{
		List<Lecture>le=lectureRepo.findAll();
		return new ResponseEntity<List<Lecture>>(le,HttpStatus.OK);
	}
	
	@GetMapping("/api/lecture/{id}")
	public ResponseEntity<Lecture>getLectureById(@PathVariable int id)
	{
		Optional<Lecture>cl=lectureRepo.findById(id);
		if(cl.isPresent())
		{
			return new ResponseEntity<Lecture>(cl.get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Lecture>(cl.get(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("api/lecture/{id}")
	public ResponseEntity<Lecture>updateLecture(@RequestBody Lecture updatedLecture ,@PathVariable int id)
	{
		Optional<Lecture>lec=lectureRepo.findById(id);
		if(lec.isPresent())
		{
		Lecture lc=lec.get();
			lc.setName(updatedLecture.getName());
			lc.setDepartment(updatedLecture.getDepartment());
			Lecture cl=lectureRepo.save(lc);
			return new ResponseEntity<Lecture>(cl,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Lecture>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/api/lecture/{id}")
	public ResponseEntity<Void>deleteCollege(@PathVariable int id)
	{
		Optional<Lecture>clg=lectureRepo.findById(id);
		if(clg.isPresent())
		{
			lectureRepo.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		}
	}



