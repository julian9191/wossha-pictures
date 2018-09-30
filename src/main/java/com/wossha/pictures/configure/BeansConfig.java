package com.wossha.pictures.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wossha.pictures.commands.CommandSerializers;
import com.wossha.pictures.commands.savePicture.SavePictureCommand;
import com.wossha.pictures.commands.savePicture.SavePictureSerializer;
import com.wossha.pictures.infrastructure.repositories.FileRepository;

@Configuration
public class BeansConfig {
	
	@Bean
	public FileRepository userRpository() {
			return new FileRepository();
	}


	@Bean
	public SavePictureCommand savePictureCommand() {
		return new SavePictureCommand();
	}
	
	@Bean
	public SavePictureSerializer savePictureSerializer() {
		return new SavePictureSerializer();
	}
	
	@Bean
	public CommandSerializers commandSerializers() {
		CommandSerializers cs = new CommandSerializers();
		cs.setModifyUserSerializer(savePictureSerializer());
		cs.initMapper();
		return cs;
	}
	
}
