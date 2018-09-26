package com.xinhuanet.app.service.impl;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinhuanet.app.dao.AttachmentDAO;
import com.xinhuanet.app.entity.Attachment;
import com.xinhuanet.app.service.AttachmentService;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentDAO attachmentdao;
	
	@Override
	public void save(Attachment attachment) {
		attachmentdao.save(attachment);
	}

	@Override
	public Attachment get(Long id) {
		return attachmentdao.findOne(id);
	}

	@Override
	public void update(Attachment attachment) {
		attachmentdao.save(attachment);
	}

	@Override
	public void delete(Long id) throws ServiceException {
		Attachment attachment = this.get(id);
		attachmentdao.delete(attachment);
	}

	@Override
	public List<Attachment> findAll() {
		return attachmentdao.findAll();
	}

	@Override
	public String findAttachmentByContentid(Long contentid) {
		// TODO Auto-generated method stub
		return attachmentdao.findAttachmentByContentid(contentid);
	}

	@Override
	public List<Attachment> findAttachmentByContentidNoTopic(Long contentid) {
		// TODO Auto-generated method stub
		return attachmentdao.findAttachmentByContentidNoTopic(contentid);
	}

	@Override
	public List<Attachment> findAttachmentByContentidAndType(Long contentid) {
		// TODO Auto-generated method stub
		return attachmentdao.findAttachmentByContentidAndType(contentid);
	}


}
