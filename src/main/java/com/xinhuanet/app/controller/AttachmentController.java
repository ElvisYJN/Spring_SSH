package com.xinhuanet.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xinhuanet.app.entity.Attachment;
import com.xinhuanet.app.service.AttachmentService;

@Controller
public class AttachmentController {

	private static Logger log = Logger.getLogger(AttachmentController.class);

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping(value = "/getAttachment")
	@ResponseBody
	public Attachment get(Long id) {
		return attachmentService.get(id);
	}

}
