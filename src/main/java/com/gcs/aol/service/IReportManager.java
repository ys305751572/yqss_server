package com.gcs.aol.service;

import org.springframework.data.domain.Page;

import com.gcs.aol.entity.Report;
import com.gcs.sysmgr.service.GenericManager;

/**
 * 
 * @ClassName: IReportService
 * @Description: 举报业务
 * @author yesong
 * @date 2016年1月16日
     *
 */
public interface IReportManager extends GenericManager<Report>{

	public Page<Report> findAll(Report report,Integer currnetPage,Integer pageSize);
}
