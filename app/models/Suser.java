package models;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: ermancagiral
 * Date: 10.06.2013
 * Time: 14:46
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Suser extends GenericModel {

	@Id
    public String username;
	
    public String password;
	public Suser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
    
    
    
}
