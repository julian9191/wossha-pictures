package com.wossha.pictures.infrastructure.dao.picture;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.springframework.stereotype.Repository;
import com.wossha.pictures.dto.PictureFileDTO;

@Repository
public abstract  class PictureDao {
	
	//SELECTS--------------------------------------------------------------------------------------------------------------------------------------
	
	@RegisterMapper(PictureMapperJdbi.class)
	@SqlQuery("SELECT * FROM TWSS_PICTURES WHERE UUID =:uuid")
    public abstract PictureFileDTO getPictureByUuID(@Bind("uuid") String uuid);
	
	//INSERTS--------------------------------------------------------------------------------------------------------------------------------------
    
	@RegisterMapper(PictureMapperJdbi.class)
    @SqlUpdate("Insert into TWSS_PICTURES (UUID,USERNAME,NAME,FILE_TYPE,TYPE,FILE_SIZE,VALUE) values (:picture.uuid, :picture.username, :picture.name, :picture.fileType, :picture.type, :picture.fileSize, :picture.value)")
    public abstract void add(@BindBean("picture") PictureFileDTO picture);
	
	//REMOVES--------------------------------------------------------------------------------------------------------------------------------------
	@SqlUpdate("DELETE TWSS_PICTURES WHERE UUID =:uuid")
    public abstract void removeByUuid(@Bind("uuid") String uuid);
}