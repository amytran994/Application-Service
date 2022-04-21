package com.example.ApplicationService.service;


import com.example.ApplicationService.dao.DigitalDocumentDAO;
import com.example.ApplicationService.domain.DigitalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DigitalDocumentService {

	private DigitalDocumentDAO digitalDocumentDAO;

	@Autowired
	// @Qualifier("digitalDocumentHibernateDao")
	public void setDigitalDocumentDAO(DigitalDocumentDAO digitalDocumentDAO) {
		this.digitalDocumentDAO = digitalDocumentDAO;
	}

	@Transactional
	public DigitalDocument getById(Integer id) {
		return digitalDocumentDAO.getById(id);
	}
	@Transactional
	public List<DigitalDocument> getAll() {
		return digitalDocumentDAO.getAll();
	}

	@Transactional
	public void add(DigitalDocument... digitalDocumentList) {
		for (DigitalDocument digitalDocument : digitalDocumentList) {
			digitalDocumentDAO.add(digitalDocument);
		}
	}


}
