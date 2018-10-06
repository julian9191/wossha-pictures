package com.wossha.pictures.infrastructure.repositories;

import org.skife.jdbi.v2.IDBI;
import org.springframework.beans.factory.annotation.Autowired;
import com.wossha.pictures.dto.PictureFileDTO;
import com.wossha.pictures.infrastructure.dao.picture.PictureDao;

public class FileRepository implements Repository<PictureFileDTO> {

	@Autowired
	private IDBI dbi;
	
	private PictureDao pictureDao;
	
	@Override
	public void add(PictureFileDTO pic) {
		pictureDao = dbi.onDemand(PictureDao.class);
		pictureDao.add(pic);
	}
	
	public PictureFileDTO getPictureByUuID(String uuid) {
		pictureDao = dbi.onDemand(PictureDao.class);
		PictureFileDTO result = pictureDao.getPictureByUuID(uuid);
		return result;
	}
	
	
	
    @Override
    public void update(PictureFileDTO clothe) {

    }

    @Override
    public void remove(PictureFileDTO clothe) {
    	
    }

	
}
