package com.wossha.pictures.infrastructure.dao.picture;

import java.util.List;

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
    @SqlUpdate("Insert into TWSS_CLOTHES (UUID,USERNAME,NAME,DESCRIPTION,TYPE,CATEGORY,PURCHASE_DATE,HOW_LIKE,BRAND,COLOR_CODE,BASE_COLOR,PICTURE) values (:clothe.uuid, :clothe.username, :clothe.name, :clothe.description, :clothe.type, :clothe.category, :clothe.purchaseDate, :clothe.howLike, :clothe.brand, :clothe.colorCode, :clothe.baseColor, :clothe.picture)")
    public abstract void add(@BindBean("picture") PictureFileDTO picture);
}