package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index(Statement parent,Boolean positive) {
        render();
    }

    public static void getStatements(Statement parent, boolean positive){
        index(parent,positive);
    }

    public static void addStatement(Statement parent, boolean positive, String text){
        index(parent,positive);
    }

    public void upvoteStatement(Statement state){
        index(state.parent,state.positive);
    }

}