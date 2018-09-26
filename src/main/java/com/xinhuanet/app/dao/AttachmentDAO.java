package com.xinhuanet.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.xinhuanet.app.entity.Attachment;

public interface AttachmentDAO extends JpaRepository<Attachment, Long> ,JpaSpecificationExecutor<Attachment>{

	@Query(value = "SELECT ATTPATH FROM `tb_evt_attachment` where CONTENTID = ?1 and ATTTYPE = 'titlepic'",nativeQuery=true)
	public String findAttachmentByContentid(Long contentid);
	
	
	@Query(value = "SELECT * FROM `tb_evt_attachment` where CONTENTID = ?1 ",nativeQuery=true)
	public List<Attachment> findAttachmentByContentidNoTopic(Long contentid);
	
	
	@Query(value = "SELECT * FROM `tb_evt_attachment` where TYPE = ?1 AND TYPE = 0",nativeQuery=true)
	public List<Attachment> findAttachmentByContentidAndType(Long contentid);
}
