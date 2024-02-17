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
import com.Springboot.BookMyShowApp.Dto.TheatreAdminDto;
import com.Springboot.BookMyShowApp.Entity.TheatreAdmin;
import com.Springboot.BookMyShowApp.Service.TheatreAdminService;
import com.Springboot.BookMyShowApp.Util.ResponseStructure;
@RestController
@RequestMapping("theatreAdmin")
public class TheatreAdminController 
{
	@Autowired
	private TheatreAdminService theatreAdminService ;
	@PostMapping
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> saveTheatreAdmin(@RequestBody TheatreAdmin theatreAdmin)
	{
		return theatreAdminService.saveTheatreAdmin(theatreAdmin);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> findTheatreAdmin(@RequestParam int theatreAdminId)
	{
		return theatreAdminService.findTheatreAdmin(theatreAdminId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> deleteTheatreAdmin(@RequestParam int theatreAdminId)
	{
		return theatreAdminService.deleteTheatreAdmin(theatreAdminId);
	}
	
	@GetMapping("AllTheatreAdmin")
	public ResponseEntity<ResponseStructure<List<TheatreAdminDto>>> findAllTheatreAdmin()
	{
		return theatreAdminService.findAllTheatreAdmin();
	}
	
	
	@PutMapping("assignTheatretoThAdmin")
	ResponseEntity<ResponseStructure<TheatreAdminDto>> assignTheatreToTheatreAdmin(@RequestParam int  theatreAdminId,@RequestParam int  theatreId){
		return theatreAdminService.assignTheatreToTheatreAdmin(theatreAdminId, theatreId);
	}
}
