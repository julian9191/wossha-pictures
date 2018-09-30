package com.wossha.pictures.commands.savePicture.model;

import com.wossha.msbase.controllers.commands.CommandModel;
import com.wossha.pictures.dto.PictureFileDTO;

public class SavePicture extends CommandModel{
    private PictureFileDTO picture;
    
	public PictureFileDTO getPicture() {
		return picture;
	}
	public void setPicture(PictureFileDTO clothe) {
		this.picture = clothe;
	}

}
