package controllers;

import models.Statement;
import models.Suser;
import models.Upvote;
import play.Logger;
import play.cache.Cache;
import play.libs.Codec;
import play.libs.Images;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index(Long id,int positive) {
        Statement parent;
    	boolean isFirst = false;
    	if(id == null ){
    		parent = Statement.find("order by id asc").first();
    		isFirst = true;
    	}else{
    		parent = Statement.findById(id);
    	}
    	Suser connected = Auth.connectedUser();
        render(parent,positive,isFirst,connected);
    }

    public static void addStatement(Long parent, int positive, String text){
    	Suser suser = Auth.connectedUser();
        if(suser == null){
            flash.put("action","addStatement");
            flash.put("parent",parent);
            flash.put("positive",positive);
            flash.put("text",text);
            login();
        }
        if(text.equals("")){
    		index(parent,positive);
    	}
    	Statement newStatement = new Statement();
    	Statement orig = Statement.findById(parent);
    	newStatement.parent = orig;
    	newStatement.positive = positive == 1 ? Boolean.TRUE : Boolean.FALSE;
    	newStatement.st_text = text;
    	newStatement.owner = Suser.findById("admin");
    	newStatement.save();
        index(parent,positive);
    }

    public static void upvoteStatement(Long id){
    	Statement s = Statement.findById(id);
    	Suser user = Suser.findById("admin");
    	Upvote upvote = Upvote.find("suser = ? and statement = ?",user,s ).first();
    	if(upvote == null){
    		new Upvote(user,s).save();    		
    	}
    	long count = upvote.count("statement=?",s);
    	s.point =(int)count;
    	s.save();
        index(s.parent.id,s.positive ? 1 : -1);
    }
    
    public static void captcha(String id) {
        Images.Captcha captcha = Images.captcha();
        String code = captcha.getText("#424449");
        Cache.set(id, code, "10mn");
        renderBinary(captcha);
    }
    
    public static void login(){
        flash.keep();
    	render();
    }
    
    public static void signup(){
    	String randomID = Codec.UUID();
    	render(randomID);
    }
    
    public static void logout() {              
        session.clear();
        index(null,0);
    }

}