package com.xinhuanet.app.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.xinhuanet.app.entity.Attachment;

public interface AttachmentService {

	void save(Attachment login);

	Attachment get(Long id);

	void update(Attachment login);

	void delete(Long id) throws ServiceException;
	
	List<Attachment> findAll();
	
	String findAttachmentByContentid(Long contentid);
	
	List<Attachment> findAttachmentByContentidNoTopic(Long contentid);
	
	List<Attachment> findAttachmentByContentidAndType(Long contentid);
}
