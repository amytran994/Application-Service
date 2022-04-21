package com.example.ApplicationService.dao;

import com.example.ApplicationService.domain.DigitalDocument;


import java.util.List;

public interface DigitalDocumentDAO {

    DigitalDocument getById(Integer id);
    void add(DigitalDocument digitalDocument);
    List<DigitalDocument> getAll();

}
