package controllers;

import models.Suser;
import play.Logger;
import play.cache.Cache;
import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.i18n.Messages;
import play.mvc.Before;
import play.mvc.Controller;


public class Auth extends Controller{

	@Before
	static void globals() {
		renderArgs.put("connected", connectedUser());
	}

	public static Suser connectedUser() {
		String userId = session.get("logged");
		return (userId == null) ? null : (Suser) Suser.findById(userId);
	}
    
    public static void authenticate(@Required String userName, @Required String password) {
        Suser user = Suser.findByUsername(userName);
        if (user == null || !user.checkPassword(password)) {
        	flash.error("Tüm alanları doğru doldurunuz");
            flash.put("username", userName);
            validation.keep();
            params.flash();
            Application.login();
        }
        connect(user);
        String action = flash.get("action");
        if(action != null){
            if(action.equals("addStatement")){
                Application.addStatement(Long.parseLong(flash.get("parent")),Integer.parseInt(flash.get("positive")),flash.get("text"));
            }
        }
        Application.index(null,0);
    }
    
    private static void connect(Suser user) {
        session.put("logged", user.username);
    }   
    
	public static void register(
			@Required @MinSize(5) String password, 
			@Required @MinSize(3) @MaxSize(15) String username,					
			@Required String code,
			String randomID) 
	{
		
	    validation.equals(code, Cache.get(randomID)).message(Messages.get("Yanlış giriş"));	
        if (validation.hasErrors()) {
        	validation.keep();
            params.flash();
            flash.error("Tüm alanları doğru doldurunuz");
            Application.signup();
        }
       
        Suser user = new Suser(username, password);
        try {
        	user.save();
        } catch (Exception e) {
        	Cache.delete(randomID);
        	error();
        }
                
        Cache.delete(randomID);
        connect(user);
        Application.index(null,0);
    }

}
