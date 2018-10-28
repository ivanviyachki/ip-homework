package org.elsys.netprog.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;


@Path("/game")
public class GameController {
	static List<Game> gameList = new ArrayList<Game>();
	@POST
	@Path("/startGame")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response startGame() throws URISyntaxException{
		Game game = new Game();
		gameList.add(game);
		return Response.created(new URI("/games")).entity(game.getId()).build();
	}
	
	@PUT
	@Path("/guess/{id}/{guess}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response guess(@PathParam("id") String gameId, @PathParam("guess") String guess) throws Exception{
		for(int i = 0; i < gameList.size(); i++) {
			if(gameList.get(i).getId().equals(gameId)) {
				if(guessCheck(guess)) {
					 Integer j = gameList.get(i).getSecret();
					 gameList.get(i).setTurnsCounters(gameList.get(i).getTurnsCounters() + 1);
					 if(j.toString().equals(guess) ) {
						 gameList.get(i).setBulls(gameList.get(i).getBulls() + 4);
						 gameList.get(i).setSucces(true);
					 }
					 else {
						 for(int c = 0; c < 4; c++) {
							 if(String.valueOf(gameList.get(i).getSecret()).contains("" + guess.toCharArray()[c])){
								 if(String.valueOf(gameList.get(i).getSecret()).indexOf(guess.toCharArray()[c]) == i ){
									 gameList.get(i).setBulls(gameList.get(i).getBulls() + 1);
								 }else {
									 gameList.get(i).setCows(gameList.get(i).getCows() + 1); 
								 }
							 }
						 }
					 }
					 JSONObject jsonob = gameList.get(i).jsonformat();
					 jsonob.remove("secret");
	                    return Response.status(200).entity(jsonob.toString()).build();
				}
				return Response.status(400).build();
			}
		}
		return Response.status(404).build();
	}
	
	@GET
	@Path("/games")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getGames() {
		
		JSONArray jsonarr = new JSONArray();
		
		for(int i = 0; i < gameList.size(); i++) {
			jsonarr.put(gameList.get(i).jsonformat());
		}
		return Response.status(200).entity(jsonarr.toString()).build();


	}
	
	private boolean guessCheck(String arg) {
		Set<Integer> n = new HashSet<Integer>();
		Integer k; 
		try {
			k = new Integer(arg);
		}catch(Exception e){
			return false;
		}
		
		while(k != 0) {
			n.add(k%10);
			k /= 10;
		}
		
		if(n.size() != 4) {
			return false;
		}
		
		return true;
	}
}
