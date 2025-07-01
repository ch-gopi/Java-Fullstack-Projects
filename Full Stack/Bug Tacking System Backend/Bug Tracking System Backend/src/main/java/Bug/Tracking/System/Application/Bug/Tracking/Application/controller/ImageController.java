package Bug.Tracking.System.Application.Bug.Tracking.Application.controller;

import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Bug;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.Bugrepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.nio.file.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/images")
public class ImageController {
    private final Bugrepository bugRepository;
    private static final String UPLOAD_DIR = "uploads/images/";

    @PostMapping("/upload/{id}")
    public ResponseEntity<String> uploadImages(@PathVariable("id") Long bugId, @RequestParam("files") MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No files provided");
        }

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            List<String> storedImagePaths = new ArrayList<>();

            for (MultipartFile file : files) {
                try{
                String fileName = "bug_" + bugId + "_" + file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.write(filePath, file.getBytes());
                storedImagePaths.add(fileName);
            }catch(IOException e){
                    System.err.println("Error saving file: " + file.getOriginalFilename()); //  Log failures

                }
            }
            //  Store image paths in DB
            Bug bug = bugRepository.findById(bugId).orElseThrow(() -> new RuntimeException("Bug not found"));
            bug.getImagePaths().addAll(storedImagePaths);
            bugRepository.save(bug);
            System.out.println("Stored images for bug " + bugId + ": " + storedImagePaths);
            return ResponseEntity.ok("Images uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<String>> getBugImages(@PathVariable("id") Long bugId) {
        Optional<Bug> bug = bugRepository.findById(bugId);

        if (bug.isPresent() && bug.get().getImagePaths() != null && !bug.get().getImagePaths().isEmpty()) {
            List<String> fullImagePaths = bug.get().getImagePaths().stream()
                    .map(fileName -> "http://localhost:8082/api/images/uploads/images/" + fileName) //  Convert to full URL
                    .toList();
          
            return ResponseEntity.ok(fullImagePaths);
        }

        return ResponseEntity.ok(Collections.emptyList()); //  Return empty list if no images found
    }

    @GetMapping("/uploads/images/{fileName:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get("uploads/images/").resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                System.out.println("Image not found: " + filePath.toString()); // Debugging log
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG) //  Adjust based on file type
                    .body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
