package com.wossha.pictures.commands.savePicture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wossha.msbase.controllers.commands.ICommand;
import com.wossha.msbase.exceptions.BusinessException;
import com.wossha.msbase.exceptions.TechnicalException;
import com.wossha.pictures.commands.savePicture.model.SavePicture;
import com.wossha.pictures.infrastructure.repositories.FileRepository;

@Component
public class SavePictureCommand implements ICommand<SavePicture>{
	
	private SavePicture data;
	private String user;
	
	@Autowired
	private FileRepository repo;
	
	@Override
	public String commandName() {
		return "CreateClothe";
	}
	
	@Override
	public SavePicture data() {
		return this.data;
	}

	@Override
	public void setData(SavePicture data) {
		this.data = data;
		
	}

	@Override
	public String execute() throws BusinessException, TechnicalException {
		try {
			
			
			//repo.addClothe(clothe);
			return "La imagen se ha guardado correctamente";
		}catch (Exception e) {
			e.printStackTrace();
			throw new TechnicalException("Ha ocurrido un error al intentar guardar la imagen");
		}
	}

	@Override
	public void setUser(String user) {
		this.user = user;
	}

}
