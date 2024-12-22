package mybookhandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class MyBookController {
	
	@GetMapping("/")
    public String Load(Model model) throws FileNotFoundException {
		ArrayList<String> listOfFiles = new ArrayList<String>();
		try {
			
			String myDirectoryPath = "C:\\Software Testing Projects\\mybookhandler\\mybookhandler\\src\\main\\resources\\static\\books";
			File dir = new File(myDirectoryPath);
			  File[] directoryListing = dir.listFiles();
			 if (directoryListing != null) {
			    for (File child : directoryListing) {
			 	  listOfFiles.add(child.getName().toString());
				 
			    }
			  } else {
				  System.out.println("Cant find any books in the directory path, this is a local application only");
				 
			  }
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		 
			
	    model.addAttribute("bookNames", listOfFiles);
		return "index";
    }
    
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttrs) throws IllegalStateException, IOException {
		String fileLocation = "C:\\Software Testing Projects\\mybookhandler\\mybookhandler\\src\\main\\resources\\static\\books\\";
		String filename = file.getOriginalFilename();

		//Check if file is empty
		if(file.isEmpty()) {
			redirectAttrs.addFlashAttribute("message", "You have not selected a file, please select a file through 'Choose file' on the right.");
			redirectAttrs.addFlashAttribute("alertClass", "alert-danger");
			return "redirect:/";
		}

		//Check for duplicate file names
		String myDirectoryPath = "C:\\Software Testing Projects\\mybookhandler\\mybookhandler\\src\\main\\resources\\static\\books";
		File dir = new File(myDirectoryPath);
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		 	  if(child.getName().toString().contains(file.getOriginalFilename())){
		    	  redirectAttrs.addFlashAttribute("message", "You already have a file with this name uploaded, please delete the current file or Edit this file.");
					redirectAttrs.addFlashAttribute("alertClass", "alert-danger");
				  return "redirect:/";
		      }
		    }
		  } else {
			  throw new FileNotFoundException();
			 
		  }

		 //Transfer file
		file.transferTo(new File(fileLocation + file.getOriginalFilename()));

		redirectAttrs.addFlashAttribute("message", "Your book has been uploaded! Please restart the application for changes to take effect.");
		redirectAttrs.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/";
		
	}



	@PostMapping("/delete")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteFile(@RequestParam("ourFile") String ourFile, RedirectAttributes redirectAttrs) throws IllegalStateException, IOException {
	
		String fileName = ourFile.toString();
		String myDirectoryPath = "C:\\Software Testing Projects\\mybookhandler\\mybookhandler\\src\\main\\resources\\static\\books";
		File dir = new File(myDirectoryPath);
		File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		    	
		    	  if(child.getName().toString().equals(fileName.trim())) {
					  child.delete();
						redirectAttrs.addFlashAttribute("message", "" + "Your book '" + fileName.toString() + " ' has been deleted.");
						redirectAttrs.addFlashAttribute("alertClass", "alert-success");
						     }  
				  
		    }
		  } else {
			  throw new FileNotFoundException();
			 
		  }
		
		
		return "redirect:/";
		
	}


	@PostMapping("/saveEdit")
	public String saveEdit(@RequestParam("ourFileHidden") String ourFileHidden, @RequestParam("ourFile") String ourFile, RedirectAttributes redirectAttrs) throws IOException {
		// Fix the directory path
		String fileDirectory = "C:\\Software Testing Projects\\mybookhandler\\mybookhandler\\src\\main\\resources\\static\\books\\";
		String filePath = fileDirectory + ourFile.trim();

		// Parse the content to replace <br/> with newline
		String content = ourFileHidden.replaceAll("<br/>", "\n");

		// Write the content to the file
		Files.write(Paths.get(filePath), content.getBytes());

		if (!content.isEmpty()) {
			redirectAttrs.addFlashAttribute("message", "Your book was successfully updated! Please restart the application for changes to take effect. ");
			redirectAttrs.addFlashAttribute("alertClass", "alert-success");

			return "redirect:/";
		} else {
			redirectAttrs.addFlashAttribute("message", "Failed to save the file. The content is empty.");
			redirectAttrs.addFlashAttribute("alertClass", "alert-danger");
			return "redirect:/";
		}
	}


	@GetMapping("/books/list")
	public ResponseEntity<List<String>> getBooksList() {
		File dir = new File("C:\\Software Testing Projects\\mybookhandler\\mybookhandler\\src\\main\\resources\\static\\books\\");
		File[] files = dir.listFiles();
		List<String> bookNames = new ArrayList<>();

		if (files != null) {
			for (File file : files) {
				bookNames.add(file.getName());
			}
		}
		return ResponseEntity.ok(bookNames);
	}


}
