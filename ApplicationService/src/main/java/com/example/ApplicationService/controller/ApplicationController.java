package com.example.ApplicationService.controller;

import com.example.ApplicationService.dao.ApplicationWorkFlowDAO;
import com.example.ApplicationService.domain.ApplicationWorkFlow;
import com.example.ApplicationService.domain.DigitalDocument;
import com.example.ApplicationService.service.DigitalDocumentService;
import com.example.ApplicationService.service.S3BucketStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("ApplicationService")
public class ApplicationController {

    @Autowired
    S3BucketStorageService s3BucketStorageService;

    @Autowired
    DigitalDocumentService digitalDocumentService;


    ApplicationWorkFlowDAO applicationWorkFlowDAO;
    @Autowired
    public void setApplicationWorkFlowDAO(ApplicationWorkFlowDAO applicationWorkFlowDAO) {
        this.applicationWorkFlowDAO = applicationWorkFlowDAO;
    }
    // Returns a list of strings containing all filenames in Amazon S3.
    // Note that the files in Amazon S3 do not necessarily match the list of files in the database.
//    @GetMapping("/all")
//    public ResponseEntity<List<String>> getListOfFiles() {
//        return new ResponseEntity<>(s3BucketStorageService.listFiles(), HttpStatus.OK);
//    }

    // Upload a file to Amazon S3 Storage.
    // Update the database at the same time.
    // The parameter fileName is a string, including the extension, eg "test.txt".
    // fileName represents the filename of the file in S3 Storage.
    // The parameter file is a file, in postman, select Body -> from-data -> KEY, and change the type to "file"

    /* For Employee */

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("fileName") String fileName,
                                             @RequestParam("file") MultipartFile file) {
        String[] fileArrSplit = fileName.split("\\.");
        String type = fileArrSplit[fileArrSplit.length - 1];
        String path = fileName;
        String description = "";
        String title = "";

        DigitalDocument digitalDocument = new DigitalDocument();
        digitalDocument.setType(type);
        digitalDocument.setPath(path);
        digitalDocument.setDescription(description);
        digitalDocument.setTitle(title);

        digitalDocumentService.add(digitalDocument);
        return new ResponseEntity<>(s3BucketStorageService.uploadFile(fileName, file), HttpStatus.OK);
    }


    @GetMapping("download/{id}")
    public ResponseEntity<byte[]> downloadById(@PathVariable Integer id) {
        DigitalDocument digitalDocument = digitalDocumentService.getById(id);
        String filename = digitalDocument.getPath();
        ByteArrayOutputStream downloadInputStream = s3BucketStorageService.downloadFile(filename);

        return ResponseEntity.ok()
                .contentType(contentType(filename))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(downloadInputStream.toByteArray());
    }

    @PostMapping("/create")
    public ApplicationWorkFlow createApplication(@RequestBody ApplicationWorkFlow app) {
        return applicationWorkFlowDAO.createApplication(app.getEmployeeID(), app.getStatus(), app.getComment());
    }

    /* For HR */

    // Get All Applications by Status
    @GetMapping("/all/status")
    public List<ApplicationWorkFlow> getApplicationByStatus(@RequestParam("status") String status) {
        return applicationWorkFlowDAO.getApplicationByStatus(status);
    }

    // Update status or comment
    @PutMapping("/update/{id}")
    public ApplicationWorkFlow update(@PathVariable Integer id, @RequestBody ApplicationWorkFlow app) {

        return applicationWorkFlowDAO.update(id, app.getStatus(), app.getComment());
    }

    /* For all */

    // Get App By Id
    @GetMapping("/application/{id}")
    public ApplicationWorkFlow getById(@PathVariable Integer id) {
        return applicationWorkFlowDAO.getApplicationById(id);
    }

    // Get App By employeeId
    @GetMapping("/application/employee")
    public ApplicationWorkFlow getById(@RequestParam("employeeId") String id) {
        return applicationWorkFlowDAO.getApplicationByEmployeeId(id);
    }


    private MediaType contentType(String filename) {
        String[] fileArrSplit = filename.split("\\.");
        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
        switch (fileExtension) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

}
