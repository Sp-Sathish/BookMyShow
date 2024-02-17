package com.Springboot.BookMyShowApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Springboot.BookMyShowApp.Entity.Theatre;
import com.Springboot.BookMyShowApp.Service.TheatreService;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;

@RestController
@RequestMapping("theatre")
public class TheatreController {

	@Autowired
	private TheatreService theatreService ;
	@PostMapping
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(@RequestBody Theatre theatre)
	{
		return theatreService.saveATheatre(theatre);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(@RequestParam int theatreId)
	{
		return theatreService.findTheatre(theatreId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(@RequestParam int theatreId)
	{
		return theatreService.deleteTheatre(theatreId);
	}
	@GetMapping("AllTheatre")
	public ResponseEntity<ResponseStructure<List<Theatre>>> findAllTheatre()
	{
		return theatreService.findAllTheatre();
	}
	
	@PutMapping("assignMoviesToTheatre")
	public ResponseEntity<ResponseStructure<Theatre>> assignMoviesToTheatre(@RequestParam int theatreId,@RequestBody List<Integer> movieIds) {
		return theatreService.assignMoviesToTheatre(theatreId, movieIds);
	}
}
