package com.gcs.aol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.VideoDAO;
import com.gcs.aol.entity.Video;
import com.gcs.aol.service.IVideoManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class VideoManagerImpl extends GenericManagerImpl<Video, VideoDAO> implements IVideoManager{

	@SuppressWarnings("unused")
	@Autowired
	private VideoDAO dao;
}
