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

    public static void authenticate(@Required String userNameLogin, @Required String passwordLogin, String method, String param1, String param2, String param3) {
        Suser user = Suser.findByUsername(userNameLogin);
        if (user == null || !user.checkPassword(passwordLogin)) {
        	flash.error("Tüm alanları doğru doldurunuz");
            flash.put("username", userNameLogin);
            validation.keep();
            params.flash();
            Application.login();
        }
        connect(user);
        if(method != null){
            if(method.equals("addStatement")){
                Application.addStatement(Long.parseLong(param1),Integer.parseInt(param2),param3);
            }
            if(method.equals("upvoteStatement")){
                Application.upvoteStatement(Long.parseLong(param1));
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
			String randomID,
            String method,
            String param1,
            String param2,
            String param3)
	{
		
	    validation.equals(code, Cache.get(randomID)).message(Messages.get("Yanlış giriş"));	
        if (validation.hasErrors()) {
        	validation.keep();
            params.flash();
            flash.error("Tüm alanları doğru doldurunuz");
            Application.login();
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
        if(method != null){
            if(method.equals("addStatement")){
                Application.addStatement(Long.parseLong(param1),Integer.parseInt(param2),param3);
            }
            if(method.equals("upvoteStatement")){
                Application.upvoteStatement(Long.parseLong(param1));
            }
        }
        Application.index(null,0);
    }

}
