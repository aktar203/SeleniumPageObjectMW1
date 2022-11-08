package edu.visiontestlabs.base;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ResourceFactory {
	
	 private static ResourceFactory instance = null;
	    private Properties appProperties;

	    private ResourceFactory(){
	        appProperties = new Properties();
	        try {
	            appProperties.load(new FileInputStream(new File("src/test/resources/config/config.properties")));
	        }
	        catch (Exception ex){
	            ex.printStackTrace();
	        }
	    }

	    public static ResourceFactory getInstance(){
	        if(instance == null){
	            instance = new ResourceFactory();
	        }
	        return instance;
	    }


	    public <T> T getProperty(String key){
	        Object value = appProperties.getProperty(key);
	        return (T) value;
	    }



}
