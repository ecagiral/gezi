package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: ermancagiral
 * Date: 10.06.2013
 * Time: 14:46
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Suser extends Model {

    public String username;
    public String password;
}
