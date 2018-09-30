package com.wossha.pictures.resources;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.wossha.msbase.controllers.ControllerWrapper;
import com.wossha.pictures.dto.PictureFileDTO;
import com.wossha.pictures.infrastructure.repositories.FileRepository;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping(value = "/pictures")
public class PictureController extends ControllerWrapper {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private FileRepository repo;
	
	@RequestMapping(value = "/static-picture/{uuid}", method = RequestMethod.GET)
	public void getPictureByUuID(HttpServletResponse response,
	    HttpServletRequest request, @PathVariable String uuid) throws IOException{
		
		PictureFileDTO c = repo.getPictureByUuID(uuid);
	    byte[] img = c.getValue();

	    response.setContentType(c.getFileType());
	    response.getOutputStream().write(img);
	}

}
